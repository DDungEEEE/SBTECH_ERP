package com.sbtech.erp.auth.adapter.out.redis;

import com.sbtech.erp.auth.application.port.out.RefreshTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRedisAdapter implements RefreshTokenPort {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String PREFIX = "refresh:";

    @Override
    public void save(String userId, String token, Duration ttl) {
        redisTemplate.opsForValue().set(PREFIX + userId, token, ttl);
    }

    @Override
    public String get(String userId) {
        return Optional.of(redisTemplate.opsForValue().get(PREFIX + userId))
                .map(Object::toString)
                .orElse(null);
    }

    @Override
    public void delete(String userId) {
        redisTemplate.delete(PREFIX + userId);
    }
}
