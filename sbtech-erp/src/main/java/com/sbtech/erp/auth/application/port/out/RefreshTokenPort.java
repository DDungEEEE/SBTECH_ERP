package com.sbtech.erp.auth.application.port.out;

import java.time.Duration;

public interface RefreshTokenPort {
    void save(String userId, String token, Duration ttl);

    String get(String userId);

    void addToBlacklist(String refreshToken, long remainingValidity);

    boolean isBlacklisted(String refreshToken);

    void delete(String userId);
}
