package hu.bdavid.ems.app.dto;

import lombok.Data;

@Data
public class BaseErrorRespone {

    /**
     * A hiba kódja
     */
    int statusCode;

    /**
     * A hiba üzenete.
     */
    String errorMessage;

}
