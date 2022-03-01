package hu.bdavid.ems.app.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import hu.bdavid.ems.app.dto.BaseErrorRespone;
import hu.bdavid.ems.app.model.redis.RedisUser;
import hu.bdavid.ems.app.service.UserService;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info(request.getRequestURI());

        RedisUser redisUser = userService.findByUserNameFromRedis(request.getHeader("userName"));

        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, redisUser.getTokenCreationDate().plusHours(24));

        if (Objects.isNull(redisUser.getUser()) || duration.isNegative() || Strings.isBlank(redisUser.getToken()) || Strings.isEmpty(redisUser.getToken()) ||
                !request.getHeader("token").equals(redisUser.getToken())) {
            BaseErrorRespone baseErrorRespone = new BaseErrorRespone();
            baseErrorRespone.setStatusCode(401);
            baseErrorRespone.setErrorMessage("Unauthorized");

            response.getOutputStream()
                    .print(new ObjectMapper()
                            .writeValueAsString(baseErrorRespone));

            response.setStatus(401);
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if (!StringUtils.hasText(request.getRequestURI())) {
            return true;
        }
        return request.getRequestURI().startsWith("/auth");
    }
}
