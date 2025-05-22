package com.sbtech.erp.security.aspect;

import com.sbtech.erp.permission.model.Action;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckPermission {
    String resource();
    Action action();
}
