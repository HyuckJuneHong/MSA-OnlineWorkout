package kr.co.owomember.service;

import kr.co.owocommon.error.jwt.JwtTokenExpiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService{

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public String getValue(String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    @Override
    public void createData(String key, String value, long expired) {
        redisTemplate.opsForValue().set(key, value, expired, TimeUnit.MILLISECONDS);
    }

    @Override
    public void deleteData(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void checkValue(String refreshToken, String redisToken) {
        if(!redisToken.equals(refreshToken))
            throw new JwtTokenExpiredException();
    }
}
