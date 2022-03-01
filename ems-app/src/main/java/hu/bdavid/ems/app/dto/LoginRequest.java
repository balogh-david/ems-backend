package hu.bdavid.ems.app.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * A login request.
 *
 * @author balogh.david
 */
@Getter
@Setter
public class LoginRequest {

    /**
     * Felhasználónév.
     */
    private String userName;

    /**
     * Jelszó.
     */
    private String password;

}
