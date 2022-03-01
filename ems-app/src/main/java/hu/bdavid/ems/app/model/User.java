package hu.bdavid.ems.app.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import hu.bdavid.ems.app.enums.Permission;
import hu.bdavid.ems.app.model.base.AbstractIdentifiedAuditEntity;

/**
 * A user entitás.
 *
 * @author balogh.david
 */
@Data
@Entity
@Table(name = "USER")
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractIdentifiedAuditEntity {

    /**
     * A Felhasználó neve.
     */
    @NotBlank(message = "userName should be not blank")
    @Size(max = 254)
    @Column(name = "USERNAME", unique = true, nullable = false, length = 254)
    private String userName;

    /**
     * A Felhasználó email címe.
     */
    @Email
    @Size(max = 254)
    @Column(name = "EMAIL", unique = true, nullable = false, length = 254)
    private String email;

    /**
     * A Felhasználó telefonszáma.
     */
    @NotBlank(message = "phoneNumber should be not blank")
    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    /**
     * A Felhasználó pozicíója.
     */
    @Column(name = "POSITION")
    private String position;

    /**
     * Az a cég id, ahová a felhasználó kerül.
     */
    @Column(name = "COMPANY_ID")
    private String companyId;

    /**
     * A Felhasználó megnevezése.
     */
    @NotBlank(message = "name should be not blank")
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * A Felhasználó jogosultsága.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "PERMISSION", length = 32, nullable = false)
    private Permission permission;

    /**
     * A Felhasználó szabadnapjainak száma.
     */
    @Column(name = "DAYS_OFF", nullable = false)
    private int daysOff;

    /**
     * A Felhasználó jelszava.
     */
    @NotBlank(message = "password should be not blank")
    @Size(max = 254)
    @Column(name = "PASSWORD", length = 254, nullable = false)
    private String password;

}
