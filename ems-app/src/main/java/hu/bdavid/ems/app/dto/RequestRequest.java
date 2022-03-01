package hu.bdavid.ems.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

/**
 * A kérelem request.
 *
 * @author balogh.david
 */
@Valid
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestRequest {

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

}
