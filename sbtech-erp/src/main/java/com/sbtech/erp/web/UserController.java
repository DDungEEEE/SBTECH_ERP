package com.sbtech.erp.web;

import org.springframework.web.bind.annotation.GetMapping;

public class UserController {
    @GetMapping("/user")
    public String getUser() {
        return "user/user";
    }
}
