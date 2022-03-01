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
import org.springframework.web.bind.annotation.RestController;

import hu.bdavid.ems.app.action.RequestAction;
import hu.bdavid.ems.app.dto.RequestRequest;
import hu.bdavid.ems.app.dto.RequestResponse;
import hu.bdavid.ems.app.model.Request;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/rest/requests")
public class RequestRestController {

    private final RequestAction requestAction;

    @GetMapping(
            path = "/{from}/{numberOfItems}/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Request> getUsers(@PathVariable int from, @PathVariable int numberOfItems, @PathVariable String status) {
        return requestAction.getRequests(from, numberOfItems, status);
    }

    @GetMapping(
            path = "/{requestId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RequestResponse getUser(@PathVariable String requestId) {
        return requestAction.getRequest(requestId);
    }

    @DeleteMapping(
            path = "/{requestId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public boolean deleteUser(@PathVariable String requestId) {
        return requestAction.deleteRequest(requestId);
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public RequestResponse postUser(@Valid @RequestBody RequestRequest requestRequest) {
        return requestAction.postRequest(requestRequest);
    }

    @PutMapping(
            path = "/{requestId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public RequestResponse putUser(@Valid @RequestBody RequestRequest requestRequest, @PathVariable String requestId) {
        return requestAction.putRequest(requestRequest, requestId);
    }

}
