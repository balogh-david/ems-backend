package hu.bdavid.ems.app.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import hu.bdavid.ems.app.model.base.AbstractIdentifiedAuditEntity;

/**
 * A kérelem entitás.
 *
 * @author balogh.david
 */
@Data
@Entity
@Table(name = "REQUEST")
@EqualsAndHashCode(callSuper = true)
public class Request extends AbstractIdentifiedAuditEntity {

    /**
     * Kérés létrehozója.
     */
    @NotBlank(message = "creator should be not blank")
    @Column(name = "CREATOR", nullable = false, length = 254)
    private String creator;

    /**
     * Kérés tárgya.
     */
    @NotBlank(message = "subject should be not blank")
    @Column(name = "SUBJECT", nullable = false, length = 254)
    private String subject;

    /**
     * Kérés leírása.
     */
    @NotBlank(message = "description should be not blank")
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    /**
     * Kérés státusza.
     */
    @NotBlank(message = "status should be not blank")
    @Column(name = "STATUS", nullable = false)
    private String status;

}
