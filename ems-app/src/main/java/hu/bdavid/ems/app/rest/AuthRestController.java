package hu.bdavid.ems.app.rest;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.bdavid.ems.app.action.LoginAction;
import hu.bdavid.ems.app.action.UserAction;
import hu.bdavid.ems.app.dto.LoginRequest;
import hu.bdavid.ems.app.dto.LoginResponse;
import hu.bdavid.ems.app.dto.UserRequest;
import hu.bdavid.ems.app.dto.UserResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthRestController {

    private final LoginAction loginAction;
    private final UserAction userAction;

    @PostMapping(
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        return loginAction.login(loginRequest);
    }

    @PostMapping(
            path = "/registration",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public UserResponse postUser(@Valid @RequestBody UserRequest userRequest) {
        return userAction.postUser(userRequest);
    }

}
