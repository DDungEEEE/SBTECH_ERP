package com.sbtech.erp.security.aspect;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.employee.domain.Employee;
import com.sbtech.erp.permission.domain.core.Action;
import com.sbtech.erp.security.user.EmployeeUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class PermissionCheckAspect {
    private final PermissionChecker permissionChecker;

    @Around("@annotation(checkPermission)")
    public Object checkPermission(ProceedingJoinPoint joinPoint, CheckPermission checkPermission) throws Throwable{
        String resource = checkPermission.resource();
        Action action = checkPermission.action();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = ((EmployeeUserDetails) authentication.getPrincipal()).getEmployee();

        if (!permissionChecker.hasPermission(employee.getPosition().getId(), employee.getRank(), resource, action)) {
            log.error(ErrorCode.NO_PERMISSION_ERROR.getReason() +  " : " + resource + "_" + action);
            throw new CustomException(ErrorCode.NO_PERMISSION_ERROR);
        }

        return joinPoint.proceed();
    }
}
