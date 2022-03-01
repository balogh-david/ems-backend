package hu.bdavid.ems.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import hu.bdavid.ems.app.enums.Permission;

/**
 * Felhasználó létrehozási kérelem osztálya.
 *
 * @author balogh.david
 */
@Valid
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    /**
     * A Felhasználó neve.
     */
    @NotBlank(message = "userName should be not blank")
    @Length(min = 6, message = "userName should be 6 character")
    private String userName;

    /**
     * A Felhasználó pozíciója.
     */
    private String position;

    /**
     * A Felhasználót létrehozó azonosítója.
     */
    private String id;

    /**
     * A Felhasználó telefonszáma.
     */
    @NotBlank(message = "phoneNumber should be not blank")
    private String phoneNumber;

    /**
     * A Felhasználó jelszava.
     */
    private String password;

    /**
     * A Felhasználó megnevezése.
     */
    @NotBlank(message = "name should be not blank")
    private String name;

    /**
     * A Felhasználó szabadnapjainak száma.
     */
    private int daysOff;

    /**
     * A Felhasználó email címe.
     */
    @Email(message = "Email format is invalid")
    @NotBlank(message = "email should be not blank")
    private String email;

    /**
     * A Felhasználó jogosultsága.
     */
    private Permission permission;
}
