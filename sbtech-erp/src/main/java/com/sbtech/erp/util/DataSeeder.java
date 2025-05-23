package com.sbtech.erp.util;

import com.sbtech.erp.department.application.port.in.DepartmentUseCase;
import com.sbtech.erp.department.domain.model.Department;
import com.sbtech.erp.employee.adapter.in.dto.EmployeeCreateReq;
import com.sbtech.erp.employee.application.port.in.EmployeeUseCase;
import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sbtech.erp.employee.domain.model.Rank;
import com.sbtech.erp.organization.application.port.in.PositionUseCase;
import com.sbtech.erp.organization.domain.model.Position;
import com.sbtech.erp.permission.application.port.in.PermissionGroupUseCase;
import com.sbtech.erp.permission.application.port.in.PermissionUseCase;
import com.sbtech.erp.permission.domain.permission.model.Action;
import com.sbtech.erp.permission.domain.permission.model.Permission;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class DataSeeder implements ApplicationRunner {

    private final DepartmentUseCase departmentUC;
    private final PositionUseCase positionUC;
    private final EmployeeUseCase employeeUC;
    private final PermissionUseCase permissionUC;
    private final PermissionGroupUseCase permissionGroupUC;

    private static final List<String> POSITION_NAMES = List.of(
            "백엔드 개발자", "프론트엔드 개발자",
            "iOS 개발자", "Android 개발자",
            "인사담당자", "회계담당자"
    );

    private static final List<String> RESOURCES = List.of(
            "EMPLOYEE", "DEPARTMENT", "PRODUCT", "PERMISSION", "SYSTEM"
    );

    @Override
    public void run(ApplicationArguments args) {

        /* 0) 이미 시드가 끝났다면 전체 스킵 */

        /* 1) Position */
        POSITION_NAMES.forEach(name -> {
                       // ★ 중복 체크
                positionUC.createPosition(name, true);
        });
        Position adminPosition = positionUC.createPosition("시스템 관리자", true);

        /* 2) Department */
        Department devDept =
                departmentUC.create("개발팀", null);

        /* 3) 일반 직원 생성 */
        Position backendPos  = positionUC.findByName("백엔드 개발자");
        Position frontendPos = positionUC.findByName("프론트엔드 개발자");

        EmployeeEntity backendEmp  =
                employeeUC.register(new EmployeeCreateReq("백엔드직원", "backend1", "1234"));
        backendEmp.approveRegistration(devDept, backendPos, Rank.STAFF);

        EmployeeEntity frontendEmp =
                employeeUC.register(new EmployeeCreateReq("프론트직원", "frontend1", "1234"));
        frontendEmp.approveRegistration(devDept, frontendPos, Rank.DIRECTOR);

        /* 4) 관리자 계정 생성 */
        EmployeeEntity adminEmp =
                employeeUC.register(new EmployeeCreateReq("관리자", "admin", "admin"));
        adminEmp.approveRegistration(devDept, adminPosition, Rank.EXECUTIVE);

        /* 5) Permission 생성 (idempotent) */
        RESOURCES.forEach(resource ->
                Arrays.stream(Action.values()).forEach(action -> {
                     // ★ 중복 방지
                        permissionUC.createPermission(
                                resource,
                                action,
                                resource + " " + action.getDescription());

                })
        );

        /* 6) 그룹 생성 예시 (SYSTEM_ADMIN) */
        List<Long> allPermIds = permissionUC.getAllPermissions().stream()
                .map(Permission::getId)
                .toList();
        permissionGroupUC.createPermissionGroup("SYSTEM_ADMIN", allPermIds);

        /* 7) EMPLOYEE_MANAGEMENT 그룹 */
        List<Long> employeePermIds = permissionUC.findByResource("EMPLOYEE").stream()
                .map(Permission::getId)
                .toList();
        permissionGroupUC.createPermissionGroup("EMPLOYEE_MANAGEMENT", employeePermIds);
    }
}
