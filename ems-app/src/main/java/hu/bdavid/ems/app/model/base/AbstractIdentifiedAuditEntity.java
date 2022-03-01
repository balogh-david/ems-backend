package hu.bdavid.ems.app.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

/**
 * Abstract entitás a base adatokhoz.
 *
 * @author balogh.david
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractIdentifiedAuditEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String EMS_ID_GENERATOR = "PMT_ID_GENERATOR";

    /**
     * Az Entitás azonosítója.
     */
    @Id
    @Column(name = "ID", unique = true, nullable = false, updatable = false)
    @GenericGenerator(name = EMS_ID_GENERATOR, strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = EMS_ID_GENERATOR)
    protected String id;

    /**
     * Az Entitás létrehozásának ideje.
     */
    @CreationTimestamp
    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate;

}
