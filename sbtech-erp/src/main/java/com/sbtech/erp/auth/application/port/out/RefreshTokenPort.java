package com.sbtech.erp.auth.application.port.out;

import java.time.Duration;

public interface RefreshTokenPort {
    void save(String userId, String token, Duration ttl);
    String get(String userId);
    void delete(String userId);
}
