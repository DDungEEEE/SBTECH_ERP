package com.sbtech.erp.security.aspect;

import com.sbtech.erp.employee.domain.Employee;
import com.sbtech.erp.security.user.EmployeeUserDetails;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;

@Aspect
@Component
@RequiredArgsConstructor
public class PermissionCheckAspect {
    private final PermissionChecker permissionChecker;

    @Around("@annotation(checkPermission)")
    public Object checkPermission(ProceedingJoinPoint joinPoint, CheckPermission checkPermission) throws Throwable{
        String requiredPermissionCode = checkPermission.value();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Employee employee = ((EmployeeUserDetails) authentication.getPrincipal()).getEmployee();

        if (!permissionChecker.hasPermission(employee.getId(), requiredPermissionCode)) {
            throw new ("권한이 없습니다: " + requiredPermissionCode);
        }

        return joinPoint.proceed();
    }
}
