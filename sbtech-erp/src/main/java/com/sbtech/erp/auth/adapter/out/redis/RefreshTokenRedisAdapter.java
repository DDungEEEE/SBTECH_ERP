package com.sbtech.erp.auth.adapter.out.redis;

import com.sbtech.erp.auth.application.port.out.RefreshTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRedisAdapter implements RefreshTokenPort {
    private final StringRedisTemplate redisTemplate;

    private static final String REFRESH_TOKEN_KEY_PREFIX = "refreshToken:";
    private static final String BLACKLIST_KEY_PREFIX = "blacklist:";

    @Override
    public void save(String loginId, String refreshToken, Duration ttl) {
        String key = REFRESH_TOKEN_KEY_PREFIX + loginId;
        redisTemplate.opsForValue().set(key, refreshToken, ttl);
    }

    @Override
    public String get(String loginId) {
        String key = REFRESH_TOKEN_KEY_PREFIX + loginId;
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void addToBlacklist(String refreshToken, long remainingValidity) {
        if(remainingValidity > 0){
            String key = BLACKLIST_KEY_PREFIX + refreshToken;
            redisTemplate.opsForValue().set(key, "blacklisted", Duration.ofMillis(remainingValidity));
        }

    }

    @Override
    public boolean isBlacklisted(String refreshToken) {
        String key = BLACKLIST_KEY_PREFIX + refreshToken;
        return redisTemplate.hasKey(key);
    }

    @Override
    public void delete(String userId) {

    }
}
