package hu.bdavid.ems.app.action;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import hu.bdavid.ems.app.dto.DashboardResponse;
import hu.bdavid.ems.app.dto.UserRequest;
import hu.bdavid.ems.app.dto.UserResponse;
import hu.bdavid.ems.app.model.User;
import hu.bdavid.ems.app.repository.UserRepository;
import hu.bdavid.ems.app.rest.header.BaseHeader;
import hu.bdavid.ems.app.service.UserService;

/**
 * A felhasználók kezelését megvalósító osztály.
 *
 * @author balogh.david
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserAction {

    private final UserService userService;
    private final UserRepository userRepository;
    private final BaseHeader baseHeader;

    /**
     * Felhasználók lekérése.
     *
     * @return a felhasználók.
     */
    public List<UserResponse> getUsers(int from, int numberOfItems) {
        List<UserResponse> usersList = new ArrayList<>();
        List<User> users = getUsersByUserPermission(from, numberOfItems);

        users.forEach(user -> {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setUserName(user.getUserName());
            userResponse.setEmail(user.getEmail());
            userResponse.setName(user.getName());
            userResponse.setDaysOff(user.getDaysOff());
            userResponse.setPermission(user.getPermission());
            userResponse.setPosition(user.getPosition());
            userResponse.setPhoneNumber(user.getPhoneNumber());

            usersList.add(userResponse);
        });

        return usersList;
    }

    /**
     * Adott felhasználó azonosító alapján történő lekérése.
     *
     * @param userId a felhasználó azonosítója.
     * @return {@link UserResponse}
     */
    public UserResponse getUser(String userId) {
        Assert.notNull(userId, "userId should be not null");

        User user = userService.findById(userId);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUserName(user.getUserName());
        userResponse.setPosition(user.getPosition());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setDaysOff(user.getDaysOff());
        userResponse.setPermission(user.getPermission());

        return userResponse;
    }

    /**
     * Adott felhasználó felhasználónév alapján történő lekérése.
     *
     * @param userName a felhasználónév.
     * @return {@link UserResponse}
     */
    public UserResponse getUserByUserName(String userName) {
        Assert.notNull(userName, "userId should be not null");

        User user = userRepository.findUserByUserName(userName);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUserName(user.getUserName());
        userResponse.setPosition(user.getPosition());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setDaysOff(user.getDaysOff());
        userResponse.setPermission(user.getPermission());

        return userResponse;
    }

    /**
     * Adott felhasználó azonosító alapján történő törlése.
     *
     * @param userId felhasználó azonosítója.
     * @return a törlés állapota.
     */
    public boolean deleteUser(String userId) {
        Assert.notNull(userId, "userId should be not null");
        userService.deleteById(userId);
        return true;
    }

    /**
     * Felhasználó felvétele.
     *
     * @param userRequest a {@link UserRequest} kérő objektum.
     * @return {@link UserResponse}
     */
    public UserResponse postUser(UserRequest userRequest) {
        Assert.notNull(userRequest, "userRequest should be not null");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setDaysOff(userRequest.getDaysOff());
        user.setName(userRequest.getName());
        user.setPermission(userRequest.getPermission());
        user.setPosition(userRequest.getPosition());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        if (!Objects.isNull(userRequest.getId())) {
            user.setCompanyId(userRequest.getId());
        }

        user = userService.save(user);

        Assert.notNull(user, "user should be not null");

        UserResponse userResponse = new UserResponse();
        userResponse.setPermission(user.getPermission());
        userResponse.setUserName(user.getUserName());
        userResponse.setEmail(user.getEmail());
        userResponse.setName(user.getName());
        userResponse.setPosition(user.getPosition());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setDaysOff(user.getDaysOff());
        userResponse.setId(user.getId());

        return userResponse;
    }

    /**
     * Felhasználó módosítása.
     *
     * @param userRequest a {@link UserRequest} kérő objektum.
     * @return {@link UserResponse}
     */
    public UserResponse putUser(UserRequest userRequest, String userId) {
        Assert.notNull(userRequest, "userRequest should be not null");

        User user = userService.findById(userId);

        Assert.notNull(user, "user should be not null");


        if (!Objects.isNull(userRequest.getPassword())) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }

        user.setUserName(userRequest.getUserName());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPosition(userRequest.getPosition());

        user = userService.save(user);

        Assert.notNull(user, "user should be not null");

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUserName(user.getUserName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setPosition(user.getPosition());
        userResponse.setName(user.getName());
        userResponse.setDaysOff(user.getDaysOff());
        userResponse.setPermission(user.getPermission());

        return userResponse;
    }

    /**
     * Jogosultság alapján csoportosított felhasználók száma.
     *
     * @return a különböző jogosultságok és a hozzá tartozó darabszámok.
     */
    public List<DashboardResponse> countPermission() {
        return userRepository.countPermisison();
    }

    private List<User> getUsersByUserPermission(int from, int to) {
        User user = userRepository.findUserByUserName(baseHeader.getUserName());
        Assert.notNull(user, "user should be not null");

        return switch (user.getPermission()) {
            case ADMIN -> userRepository.findUsers(from, to, user.getId());
            case COMPANY -> userRepository.findCompanyUsers(from, to, user.getId());
            default -> userRepository.findCompanyUsers(from, to, user.getCompanyId());
        };
    }

}
