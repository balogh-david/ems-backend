package hu.bdavid.ems.app.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * A kérelem response.
 *
 * @author balogh.david
 */
@Getter
@Setter
public class RequestResponse {

    /**
     * Kérés azonosítója.
     */
    private String id;

    /**
     * Kérés létrehozója.
     */
    private String creator;

    /**
     * Kérés leírása.
     */
    private String description;

    /**
     * Kérés státusza.
     */
    private String status;

    /**
     * Kérés tárgya.
     */
    private String subject;

    /**
     * Kérés létrehozási dátuma.
     */
    private String creationDate;

}
