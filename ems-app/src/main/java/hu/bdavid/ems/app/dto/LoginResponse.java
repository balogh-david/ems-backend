package hu.bdavid.ems.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import hu.bdavid.ems.app.enums.Permission;

/**
 * A login response.
 *
 * @author balogh.david
 */
@Getter
@Setter
public class LoginResponse {

    /**
     * Felhasználónév.
     */
    private String userName;

    /**
     * Felhsználó jogosultsága.
     */
    private Permission permission;

    /**
     * Token.
     */
    private String token;

    /**
     * Azonosító.
     */
    private String id;

    /**
     * Token létrehozási ideje.
     */
    private LocalDateTime tokenCreationDate;
}
