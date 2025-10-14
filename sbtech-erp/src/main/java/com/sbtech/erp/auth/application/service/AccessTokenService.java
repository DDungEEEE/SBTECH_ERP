package com.sbtech.erp.auth.application.service;

import com.sbtech.erp.auth.application.port.in.AccessTokenUseCase;
import com.sbtech.erp.auth.application.port.out.TokenRepository;
import com.sbtech.erp.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccessTokenService implements AccessTokenUseCase {
    private final TokenRepository tokenRepository;
    private final JwtProvider jwtProvider;
    private static final String BLACKLIST_PREFIX = "blacklist:";

    @Override
    public void addBlacklist(String accessToken) {
        // 블랙리스트에 저장할 때, accessToken을 key로 사용하고, value는 의미 없는 "1" 등으로 저장
        // TTL은 accessToken 남은 유효 시간만큼 설정
        long remainingMs = getRemainingValidity(accessToken);

        if (remainingMs > 0) {
            tokenRepository.save(
                    BLACKLIST_PREFIX + accessToken,
                    "1",
                    Duration.ofMillis(remainingMs)
            );
        }

    }

    @Override
    public boolean isBlacklisted(String accessToken) {
        Optional<String> blackToken = tokenRepository.get(BLACKLIST_PREFIX + accessToken);
        return blackToken.isPresent();
    }

    // JwtProvider에서 남은 유효시간 계산하도록 유틸로 분리하거나 외부에서 주입하는 방식도 가능
    private long getRemainingValidity(String accessToken) {
        try {
            return jwtProvider.getRemainingValidity(accessToken); // 만약 static 아니라면, 필드로 주입 필요
        } catch (Exception e) {
            return 0; // 파싱 실패나 유효하지 않은 토큰은 남은 시간 없음 처리
        }
    }
}
