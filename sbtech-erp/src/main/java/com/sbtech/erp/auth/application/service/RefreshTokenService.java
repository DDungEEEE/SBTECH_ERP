package com.sbtech.erp.auth.application.service;

import com.sbtech.erp.auth.application.port.in.RefreshTokenUseCase;
import com.sbtech.erp.auth.application.port.out.TokenRepository;
import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class RefreshTokenService implements RefreshTokenUseCase {
    private final JwtProvider jwtProvider;
    private final TokenRepository tokenRepository;
    private static final String REFRESH_TOKEN_KEY_PREFIX = "refreshToken:";

    @Override
    public String reissue(String loginId, String refreshToken) {

        if (!jwtProvider.validToken(refreshToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN_ERROR);
        }

        String key = REFRESH_TOKEN_KEY_PREFIX + loginId;
        Optional<String> savedTokenOptional = tokenRepository.get(key);

        if (savedTokenOptional.isEmpty() || !savedTokenOptional.get().equals(refreshToken)) {
            log.warn("리프레시 토큰 불일치: {}", loginId);
            throw new CustomException(ErrorCode.INVALID_TOKEN_ERROR);
        }

        return jwtProvider.generateRefreshToken(loginId);
    }

    @Override
    public void save(String loginId, String refreshToken) {
        String key = REFRESH_TOKEN_KEY_PREFIX + loginId;

        tokenRepository.save(key, refreshToken, jwtProvider.getRefreshTokenTtl());
    }


    @Override
    public void delete(String loginId) {
        String key = REFRESH_TOKEN_KEY_PREFIX + loginId;
        tokenRepository.delete(key);
    }

}
