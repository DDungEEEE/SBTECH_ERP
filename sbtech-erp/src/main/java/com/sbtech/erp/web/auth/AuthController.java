package com.sbtech.erp.web.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AuthController {

    @GetMapping("/")
    public String home(){
        return "auth/login";
    }

    @GetMapping("/login")
    public String loginPage(){
        System.out.println("작동은 됨");
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signupPage(){
        return "auth/signup";
    }
}
