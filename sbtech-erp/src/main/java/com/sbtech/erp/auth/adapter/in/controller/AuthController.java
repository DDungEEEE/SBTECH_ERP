package com.sbtech.erp.auth.adapter.in.controller;

import com.sbtech.erp.auth.adapter.in.dto.TokenReissueReq;
import com.sbtech.erp.auth.adapter.in.dto.UserLoginDto;
import com.sbtech.erp.auth.adapter.in.dto.JwtToken;
import com.sbtech.erp.auth.application.port.in.AccessTokenUseCase;
import com.sbtech.erp.auth.application.port.in.RefreshTokenUseCase;
import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.security.jwt.JwtProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "사욪아 인증 컨트롤러")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {
    private final JwtProvider jwtProvider;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final AccessTokenUseCase accessTokenUseCase;


    /**
     * 실제로 사용하진 않지만 Swagger 명세화를 위한 Controller
     */
    @Operation(
            summary = "로그인",
            description = """
        로그인 요청을 처리하고 JWT 토큰을 발급합니다.
        - 요청 본문: 로그인 아이디와 비밀번호를 포함하는 `UserLoginDto`
        - 응답: JWT 액세스 토큰이 포함된 `JwtToken` 객체
        - 관리자 계정 : id : admin, password : admin
        """
    )
    @PostMapping("/login")
    public JwtToken login(@RequestBody UserLoginDto loginDto){
        log.info("Login request received for user: {}", loginDto.loginId());
        return JwtToken.builder().accessToken("asd").build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest req) {

        String accessToken = jwtProvider.getJwtToken(req);
        accessTokenUseCase.addBlacklist(accessToken);
        refreshTokenUseCase.delete(jwtProvider.getLoginIdFromToken(accessToken));

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reissue")
    public ResponseEntity<JwtToken> reissueToken(@RequestBody TokenReissueReq tokenReq, HttpServletRequest req){
        String refreshToken = tokenReq.refreshToken();

        String expiredAccessToken = jwtProvider.getJwtToken(req);

        if (!jwtProvider.validToken(refreshToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN_ERROR);
        }

        String loginId = jwtProvider.getLoginIdFromToken(refreshToken);

        String newRefreshToken = refreshTokenUseCase.reissue(loginId, refreshToken);

        // 기존의 리프레시 토큰 삭제
        refreshTokenUseCase.delete(loginId);
        // 새로운 리프레시 토큰 저장
        refreshTokenUseCase.save(loginId, newRefreshToken);

        accessTokenUseCase.addBlacklist(expiredAccessToken);
        // 액세스 토큰 재발급
        String newAccessToken = jwtProvider.generateAccessToken(loginId);


        return ResponseEntity.ok()
                .body(JwtToken.builder()
                        .accessToken(newAccessToken)
                        .refreshToken(newRefreshToken)
                        .build());


    }
}
