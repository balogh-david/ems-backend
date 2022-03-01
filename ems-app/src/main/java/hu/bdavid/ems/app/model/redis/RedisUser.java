package hu.bdavid.ems.app.model.redis;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;

import hu.bdavid.ems.app.model.User;

/**
 * A user entitás a redishez.
 *
 * @author balogh.david
 */
@Getter
@Setter
public class RedisUser implements Serializable {

    /**
     * {@link User}
     */
    private User user;

    /**
     * Token.
     */
    private String token;

    /**
     * Token létrehozási dátuma.
     */
    private LocalDateTime tokenCreationDate;

}
