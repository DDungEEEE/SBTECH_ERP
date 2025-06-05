package com.sbtech.erp.security.controller;

import com.sbtech.erp.security.JwtToken;
import com.sbtech.erp.security.user.UserLoginDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 실제로 사용하진 않지만 Swagger 명세화를 위한 Controller
 */
@Tag(name = "로그인 컨트롤러", description = "실제 로그인 요청은 Filter 딴에서 처리하지만, Swagger 명세용 Controller")
@RequestMapping("/api/v1/auth")
@RestController
public class LoginController {

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
        return JwtToken.builder().accessToken("asd").build();
    }
}
