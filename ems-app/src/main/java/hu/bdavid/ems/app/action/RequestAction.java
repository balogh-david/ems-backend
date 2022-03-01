package hu.bdavid.ems.app.action;

import lombok.RequiredArgsConstructor;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import hu.bdavid.ems.app.dto.RequestRequest;
import hu.bdavid.ems.app.dto.RequestResponse;
import hu.bdavid.ems.app.model.Request;
import hu.bdavid.ems.app.model.User;
import hu.bdavid.ems.app.repository.RequestRepository;
import hu.bdavid.ems.app.repository.UserRepository;
import hu.bdavid.ems.app.rest.header.BaseHeader;
import hu.bdavid.ems.app.service.RequestService;

/**
 * Alkalmazottak által benyújtott kérelmek kezelése.
 *
 * @author balogh.david
 */
@Service
@RequiredArgsConstructor
public class RequestAction {

    private final RequestService requestService;
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final BaseHeader baseHeader;

    /**
     * Paraméterben átadott stászuthoz tartozó kérelmek.
     *
     * @param from          megjelenített sorok kezdő értéke.
     * @param numberOfItems megjelenített sorok száma.
     */
    public List<Request> getRequests(int from, int numberOfItems, String status) {
        return getRequestsByUserPermission(from, numberOfItems, status);
    }

    /**
     * Adott kérelem azonosító alapján történő lekérése.
     *
     * @param requestId a kérés azonosítója.
     */
    public RequestResponse getRequest(String requestId) {
        Assert.notNull(requestId, "requestId should be not null");

        Request request = requestService.findById(requestId);

        RequestResponse requestResponse = new RequestResponse();
        requestResponse.setId(request.getId());
        requestResponse.setCreationDate(request.getCreationDate().toString());
        requestResponse.setCreator(request.getCreator());
        requestResponse.setDescription(request.getDescription());
        requestResponse.setStatus(request.getStatus());
        requestResponse.setSubject(request.getSubject());

        return requestResponse;
    }

    /**
     * Adott kérelem azonosító alapján történő törlés.
     *
     * @param requestId a kérés azonosítója.
     */
    public boolean deleteRequest(String requestId) {
        Assert.notNull(requestId, "requestId should be not null");
        requestService.deleteById(requestId);
        return true;
    }

    /**
     * Kérelem felvétele.
     *
     * @param requestRequest a {@link RequestRequest} kérő objektum.
     * @return {@link RequestResponse}
     */
    public RequestResponse postRequest(RequestRequest requestRequest) {
        Assert.notNull(requestRequest, "requestRequest should be not null");

        Request request = new Request();
        request.setCreator(requestRequest.getCreator());
        request.setDescription(requestRequest.getDescription());
        request.setStatus(requestRequest.getStatus());
        request.setSubject(requestRequest.getSubject());

        request = requestService.save(request);

        Assert.notNull(request, "request should be not null");

        RequestResponse requestResponse = new RequestResponse();
        requestResponse.setSubject(request.getSubject());
        requestResponse.setId(request.getId());
        requestResponse.setStatus(request.getStatus());
        requestResponse.setDescription(request.getDescription());
        requestResponse.setCreationDate(request.getCreationDate().toString());
        requestResponse.setCreator(request.getCreator());

        return requestResponse;
    }

    /**
     * Kérelem módosítása.
     *
     * @param requestRequest a {@link RequestRequest} kérő objektum.
     * @return {@link RequestResponse}
     */
    public RequestResponse putRequest(RequestRequest requestRequest, String requestId) {
        Assert.notNull(requestRequest, "requestRequest should be not null");

        Request request = requestService.findById(requestId);

        Assert.notNull(request, "request should be not null");

        boolean dayOff = true;
        if (request.getStatus().equals("Accepted") && requestRequest.getStatus().equals("Remaining")) {
            System.out.println(requestRequest.getCreator());
            User user = userRepository.findUserByUserName(requestRequest.getCreator());
            user.setDaysOff(user.getDaysOff() + 1);
            userRepository.save(user);
            dayOff = false;
        }

        request.setSubject(requestRequest.getSubject());
        request.setStatus(requestRequest.getStatus());
        request.setCreator(requestRequest.getCreator());
        request.setDescription(requestRequest.getDescription());

        request = requestService.save(request);

        Assert.notNull(request, "user should be not null");

        RequestResponse requestResponse = new RequestResponse();
        requestResponse.setId(request.getId());
        requestResponse.setCreationDate(request.getCreationDate().toString());
        requestResponse.setCreator(request.getCreator());
        requestResponse.setDescription(request.getDescription());
        requestResponse.setStatus(request.getStatus());
        requestResponse.setSubject(request.getSubject());

        if (requestResponse.getSubject().endsWith("DAYOFF") && dayOff) {
            User user = userRepository.findUserByUserName(requestRequest.getCreator());
            if (user.getDaysOff() > 0) {
                user.setDaysOff(user.getDaysOff() - 1);
                userRepository.save(user);
            }
        }
        return requestResponse;
    }

    private List<Request> getRequestsByUserPermission(int from, int numberOfItems, String status) {
        User user = userRepository.findUserByUserName(baseHeader.getUserName());
        Assert.notNull(user, "user should be not null");

        return switch (user.getPermission()) {
            case COMPANY -> requestRepository.findCompanyRequests(from, numberOfItems, status, user.getId());
            case ADMIN -> requestRepository.findRequests(from, numberOfItems, status);
            default -> requestRepository.findUserRequests(from, numberOfItems, status, user.getUserName());
        };
    }

}
