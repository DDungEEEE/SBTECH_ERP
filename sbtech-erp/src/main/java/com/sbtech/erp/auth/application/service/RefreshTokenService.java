package com.sbtech.erp.auth.application.service;

import com.sbtech.erp.auth.adapter.in.dto.JwtToken;
import com.sbtech.erp.auth.application.port.out.RefreshTokenPort;
import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final JwtProvider jwtProvider;
    private final RefreshTokenPort refreshTokenPort;

    public JwtToken reissue(String refreshToken) {
        if (!jwtProvider.validToken(refreshToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN_ERROR);
        }

        String loginId = jwtProvider.getLoginIdFromToken(refreshToken);
        String savedToken = refreshTokenPort.get(loginId);

        if (savedToken == null || !savedToken.equals(refreshToken)) {
            log.warn("리프레시 토큰 불일치: {}", loginId);
            throw new CustomException(ErrorCode.INVALID_TOKEN_ERROR);
        }

        String newAccessToken = jwtProvider.generateAccessToken(loginId);
        String newRefreshToken = jwtProvider.generateRefreshToken(loginId);

        refreshTokenPort.save(loginId, newRefreshToken, jwtProvider.getRefreshTokenTtl());

        return JwtToken.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();}
}
