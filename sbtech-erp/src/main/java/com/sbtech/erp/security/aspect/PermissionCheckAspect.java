//package com.sbtech.erp.security.aspect;
//
//import com.sbtech.erp.common.code.ErrorCode;
//import com.sbtech.erp.common.exception.CustomException;
//import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
//import com.sbtech.erp.permission.domain.permission.model.Action;
//import com.sbtech.erp.security.user.EmployeeUserDetails;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class PermissionCheckAspect {
//    private final PermissionChecker permissionChecker;
//
//    @Around("@annotation(checkPermission)")
//    public Object checkPermission(ProceedingJoinPoint joinPoint, CheckPermission checkPermission) throws Throwable{
//        String resource = checkPermission.resource();
//        Action action = checkPermission.action();
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        EmployeeEntity employeeEntity = ((EmployeeUserDetails) authentication.getPrincipal()).getEmployeeEntity();
//
//        if (!permissionChecker.hasPermission(employeeEntity.getPosition().getId(), employeeEntity.getRank(), resource, action)) {
//            log.error(ErrorCode.NO_PERMISSION_ERROR.getReason() +  " : " + resource + "_" + action);
//            throw new CustomException(ErrorCode.NO_PERMISSION_ERROR);
//        }
//
//        return joinPoint.proceed();
//    }
//}
