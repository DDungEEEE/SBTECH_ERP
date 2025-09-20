//package com.sbtech.erp.permission.application.port.in;
//
//import com.sbtech.erp.permission.domain.permission.model.Action;
//import com.sbtech.erp.permission.domain.permission.model.Permission;
//
//import java.util.List;
//
//public interface PermissionUseCase {
//    List<Permission> getAllPermissions(); // ✅ 도메인 객체 리턴
//
//    Permission createPermission(String resource, Action action, String description);
//
//    List<Action> getActions();
//    List<Permission> findByResource(String resource);
//}
