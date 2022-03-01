package hu.bdavid.ems.app.rest;

import lombok.RequiredArgsConstructor;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.bdavid.ems.app.action.UserAction;
import hu.bdavid.ems.app.dto.DashboardResponse;
import hu.bdavid.ems.app.dto.UserRequest;
import hu.bdavid.ems.app.dto.UserResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/rest/users")
public class UserRestController {

    private final UserAction userAction;

    @GetMapping(
            path = "/statements",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<DashboardResponse> getStatements() {
        return userAction.countPermission();
    }

    @GetMapping(
            path = "/{from}/{numberOfItems}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<UserResponse> getUsers(@PathVariable int from, @PathVariable int numberOfItems) {
        return userAction.getUsers(from, numberOfItems);
    }

    @GetMapping(
            path = "/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UserResponse getUser(@PathVariable String userId) {
        return userAction.getUser(userId);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UserResponse getUserByUserName(@RequestParam String userName) {
        return userAction.getUserByUserName(userName);
    }

    @DeleteMapping(
            path = "/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public boolean deleteUser(@PathVariable String userId) {
        return userAction.deleteUser(userId);
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public UserResponse postUser(@Valid @RequestBody UserRequest userRequest) {
        return userAction.postUser(userRequest);
    }

    @PutMapping(
            path = "/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public UserResponse putUser(@Valid @RequestBody UserRequest userRequest, @PathVariable String userId) {
        return userAction.putUser(userRequest, userId);
    }

}

