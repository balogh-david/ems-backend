package hu.bdavid.ems.app.dto;

import lombok.Getter;
import lombok.Setter;

import hu.bdavid.ems.app.enums.Permission;

/**
 * A user response.
 *
 * @author balogh.david
 */
@Getter
@Setter
public class UserResponse {

    /**
     * Az entitás azonosítója.
     */
    private String id;

    /**
     * Az entitás felhasználóneve.
     */
    private String userName;

    /**
     * Az entitás email címe.
     */
    private String email;

    /**
     * Az entitás megnevezése.
     */
    private String name;

    /**
     * Az entitás telefonszáma.
     */
    private String phoneNumber;

    /**
     * Az entitás jogosultsága.
     */
    private Permission permission;

    /**
     * Az entitás pozíciója.
     */
    private String position;

    /**
     * Az entitás szabadnapjainak száma.
     */
    private int daysOff;

}
