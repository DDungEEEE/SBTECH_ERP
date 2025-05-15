package com.sbtech.erp.security.controller;

import com.sbtech.erp.security.JwtToken;
import com.sbtech.erp.security.user.UserLoginDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 실제로 사용하진 않지만 Swagger 명세화를 위한 Controller
 */
@RequestMapping("/api/v1/auth")
@RestController
public class LoginController {
    @PostMapping("/login")
    public JwtToken login(@RequestBody UserLoginDto loginDto){
        return JwtToken.builder().accessToken("asd").build();
    }
}
