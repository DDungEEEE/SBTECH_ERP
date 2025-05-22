package com.sbtech.erp.util;

import com.sbtech.erp.department.adapter.in.dto.DepartmentCreateDto;
import com.sbtech.erp.department.application.port.DepartmentUseCase;
import com.sbtech.erp.department.domain.Department;
import com.sbtech.erp.employee.adapter.in.dto.EmployeeCreateReq;
import com.sbtech.erp.employee.application.port.EmployeeUseCase;
import com.sbtech.erp.employee.domain.Employee;
import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.organization.application.port.PositionUseCase;
import com.sbtech.erp.organization.domain.Position;
import com.sbtech.erp.permission.application.port.PermissionUseCase;
import com.sbtech.erp.permission.application.service.PermissionGroupService;
import com.sbtech.erp.permission.application.service.RolePermissionGroupService;
import com.sbtech.erp.permission.model.Action;
import com.sbtech.erp.permission.adapter.out.entity.PermissionEntity;
import com.sbtech.erp.permission.adapter.out.entity.PermissionGroupEntity;
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
    private final PermissionGroupService permissionGroupSvc;
    private final RolePermissionGroupService rolePermissionGroupService;

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

        /* 1) Position */
        POSITION_NAMES.forEach(name -> positionUC.createPosition(name, true));

        Position adminPosition = positionUC.createPosition("시스템 관리자", true); // 관리자 전용 직무

        /* 2) Department */
        Department devDept = departmentUC.create(new DepartmentCreateDto("개발팀", null));

        /* 3) 일반 직원 생성 */
        Position backendPos = positionUC.findByName("백엔드 개발자");
        Position frontendPos = positionUC.findByName("프론트엔드 개발자");

        Employee backendEmp = employeeUC.register(new EmployeeCreateReq("백엔드직원", "backend1", "1234"));
        backendEmp.approveRegistration(devDept, backendPos, Rank.STAFF);

        Employee frontendEmp = employeeUC.register(new EmployeeCreateReq("프론트직원", "frontend1", "1234"));
        frontendEmp.approveRegistration(devDept, frontendPos, Rank.DIRECTOR);

        /* 4) 관리자 계정 생성 */
        Employee adminEmp = employeeUC.register(new EmployeeCreateReq("관리자", "admin", "admin"));
        adminEmp.approveRegistration(devDept, adminPosition, Rank.EXECUTIVE);

        /* 5) Permission 생성 */
        RESOURCES.forEach(resource ->
                Arrays.stream(Action.values()).forEach(action -> {
                    String desc = resource + " " + action.getDescription();
                    permissionUC.createPermission(resource, action, desc);
                })
        );

        /* 6) SYSTEM_ADMIN 그룹 생성 + 전체 권한 부여 */
        List<Long> allPermissionIds = permissionUC.findAll().stream()
                .map(PermissionEntity::getId)
                .toList();
        PermissionGroupEntity permissionGroupEntity = permissionGroupSvc.createPermissionGroup("SYSTEM_ADMIN", allPermissionIds);

        /* ✅ 7) Position + Rank → SYSTEM_ADMIN 매핑 */

        rolePermissionGroupService.createRolePermissionGroup(adminPosition.getId(), Rank.EXECUTIVE, permissionGroupEntity.getId());
        /* 8) EMPLOYEE_MANAGEMENT 그룹도 생성 */
        List<Long> employeePermIds = permissionUC.findByResource("EMPLOYEE").stream()
                .map(PermissionEntity::getId)
                .toList();
        permissionGroupSvc.createPermissionGroup("EMPLOYEE_MANAGEMENT", employeePermIds);
    }

}
