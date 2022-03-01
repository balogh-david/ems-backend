package hu.bdavid.ems.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import hu.bdavid.ems.app.model.redis.RedisUser;

/**
 * Spring Boot starter osztálya.
 *
 * @author balogh.david
 */
@SpringBootApplication
@EnableCaching
public class EMSApplication {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    RedisTemplate<String, RedisUser> redisTemplate() {
        RedisTemplate<String, RedisUser> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(EMSApplication.class, args);
    }

}
