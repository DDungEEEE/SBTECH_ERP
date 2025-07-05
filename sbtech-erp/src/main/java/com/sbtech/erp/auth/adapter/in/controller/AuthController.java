package com.sbtech.erp.auth.adapter.in.controller;

import com.sbtech.erp.auth.adapter.in.dto.TokenReissueReq;
import com.sbtech.erp.auth.adapter.in.dto.UserLoginDto;
import com.sbtech.erp.auth.adapter.in.dto.JwtToken;
import com.sbtech.erp.auth.application.port.out.RefreshTokenPort;
import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.security.jwt.JwtProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "사욪아 인증 컨트롤러", description = "실제 로그인 요청은 Filter 딴에서 처리하지만, Swagger 명세용 Controller")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {
    private final JwtProvider jwtProvider;
    private final RefreshTokenPort refreshTokenPort;

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

    @GetMapping("/reissue")
    public ResponseEntity<JwtToken> reissueToken(@RequestBody TokenReissueReq req){
        String refreshToken = req.refreshToken();

        if(!jwtProvider.validToken(refreshToken)){
            throw new CustomException(ErrorCode.INVALID_TOKEN_ERROR);
        }

        String loginId = jwtProvider.getLoginIdFromToken(refreshToken);
        String findRefreshToken = refreshTokenPort.get(loginId);

        if(findRefreshToken.isEmpty()){
            log.error("리프레시 토큰이 존재하지 않습니다.");
            throw new CustomException(ErrorCode.INVALID_TOKEN_ERROR);
        }

        if(!findRefreshToken.equals(refreshToken)){
            log.error("리프레시 토큰이 일치하지 않습니다.");
            throw new CustomException(ErrorCode.INVALID_TOKEN_ERROR);
        }

        String accessToken = jwtProvider.generateAccessToken(loginId);

        return ResponseEntity.ok()
                .body(JwtToken.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build());


    }
}
