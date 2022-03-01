package hu.bdavid.ems.app.action;

import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import hu.bdavid.ems.app.dto.LoginRequest;
import hu.bdavid.ems.app.dto.LoginResponse;
import hu.bdavid.ems.app.model.User;
import hu.bdavid.ems.app.model.redis.RedisUser;
import hu.bdavid.ems.app.repository.UserRepository;
import hu.bdavid.ems.app.rest.header.BaseHeader;
import hu.bdavid.ems.app.service.UserService;

/**
 * A bejelentkezést megvalósító osztály.
 *
 * @author balogh.david
 */
@Service
@RequiredArgsConstructor
public class LoginAction {

    private final UserRepository userRepository;
    private final UserService userService;

    /**
     * Bejelentkezést megvalósító függvény.
     *
     * @param loginRequest a {@link LoginRequest} objetum.
     * @return a {@link LoginResponse} objektum.
     * @throws Exception Helytelen bejelentkezési adatok esetén történő exception dobás.
     */
    public LoginResponse login(LoginRequest loginRequest) throws Exception {
        Assert.notNull(loginRequest, "loginRequest should be not null");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        BaseHeader baseHeader = new BaseHeader();
        System.out.println("username: " + baseHeader.getUserName());

        User user = userRepository.findUserByUserName(loginRequest.getUserName());

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()) || !loginRequest.getUserName().equals(user.getUserName())) {
            throw new Exception("Username or password invalid");
        }

        Assert.notNull(user, "user should be not null");

        RedisUser redisUser = new RedisUser();
        redisUser.setUser(user);
        redisUser.setTokenCreationDate(LocalDateTime.now());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserName(user.getUserName());
        loginResponse.setPermission(user.getPermission());
        loginResponse.setToken(generateToken());
        loginResponse.setId(user.getId());
        loginResponse.setTokenCreationDate(redisUser.getTokenCreationDate());

        redisUser.setToken(loginResponse.getToken());

        userService.saveToRedis(redisUser);

        return loginResponse;
    }

    private static String generateToken() {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(254);
        Random rnd = new Random();

        for (int i = 0; i < 254; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString().strip();
    }
}
