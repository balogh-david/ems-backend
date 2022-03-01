package hu.bdavid.ems.app.security;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProjectAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("auth");
        return null;
    }

    @Override
    public boolean supports(Class<?> authenticationClass) {
        log.info("support");
        return authenticationClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
