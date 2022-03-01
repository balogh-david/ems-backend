package hu.bdavid.ems.app.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import hu.bdavid.ems.app.model.User;
import hu.bdavid.ems.app.model.redis.RedisUser;

@Service
@RequiredArgsConstructor
public class UserService extends BaseService<User, String> {

    private RedisTemplate<String, RedisUser> redisTemplate;

    @Autowired
    public UserService(RedisTemplate<String, RedisUser> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveToRedis(RedisUser redisUser) {
        this.redisTemplate.opsForValue().set(redisUser.getUser().getUserName(), redisUser);
    }

    public RedisUser findByUserNameFromRedis(String userName) {
        return this.redisTemplate.opsForValue().get(userName);
    }

    public void deleteByUserNameFromRedis(String userName) {
        this.redisTemplate.delete(userName);
    }
}
