package com.sbtech.erp.util;

import com.sbtech.erp.department.adapter.in.dto.DepartmentCreateDto;
import com.sbtech.erp.department.application.port.DepartmentUseCase;
import com.sbtech.erp.department.domain.Department;
import com.sbtech.erp.employee.adapter.in.dto.EmployeeCreateReq;
import com.sbtech.erp.employee.adapter.out.dto.repository.JpaEmployeeRepository;
import com.sbtech.erp.employee.application.port.EmployeeUseCase;
import com.sbtech.erp.employee.domain.Employee;
import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.organization.application.port.PositionUseCase;
import com.sbtech.erp.organization.domain.Position;
import com.sbtech.erp.permission.application.port.PermissionUseCase;
import com.sbtech.erp.permission.application.service.PermissionGroupService;
import com.sbtech.erp.permission.domain.core.Action;
import com.sbtech.erp.permission.domain.core.Permission;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final PermissionUseCase permissionUseCase;
    private final PositionUseCase positionUseCase;
    private final EmployeeUseCase employeeUseCase;
    private final DepartmentUseCase departmentUseCase;
    private final JpaEmployeeRepository employeeRepository;
    private final PermissionGroupService permissionGroupService;

    @PostConstruct
    public void initAll() {
//        // 1. Permission 생성
//        permissionUseCase.createPermission("employee", Action.CREATE, "직원 목록 조회");
//        permissionUseCase.createPermission("employee", Action.UPDATE, "직원 정보 수정");
//        permissionUseCase.createPermission("department", Action.CREATE, "부서 생성");
//        permissionUseCase.createPermission("department", Action.DELETE, "부서 삭제");
//        permissionUseCase.createPermission("product", Action.READ, "상품 조회");
//        permissionUseCase.createPermission("product", Action.UPDATE, "상품 수정");
//        permissionUseCase.createPermission("product", Action.CREATE, "상품 등록");
//        permissionUseCase.createPermission("product", Action.DELETE, "상품 삭제");
//        permissionUseCase.createPermission("SYSTEM", Action.MANAGE, "시스템 관리");

        // 2. Position 생성
        Position backendDev = positionUseCase.createPosition("백엔드 개발자", true);// ID = 1
        Position frontendDev = positionUseCase.createPosition("프론트엔드 개발자", true);// ID = 2

        positionUseCase.createPosition("iOS 개발자", true);
        positionUseCase.createPosition("Android 개발자", true);
        positionUseCase.createPosition("인사담당자", true);
        positionUseCase.createPosition("회계담당자", true);


        Department devDept = departmentUseCase.create(new DepartmentCreateDto("개발팀", null));

        // 5. Employee 생성

        Employee backendEmp = employeeUseCase.register(new EmployeeCreateReq("백엔드직원",
                "backend1",
                "1234"));

        backendEmp.approveRegistration(devDept, backendDev,Rank.STAFF);


        Employee frontendEmp = employeeUseCase.register(new EmployeeCreateReq("프론트직원",
                "frontend1",
                "1234"));

        frontendEmp.approveRegistration(devDept, frontendDev, Rank.DIRECTOR);

        employeeRepository.save(backendEmp);
        employeeRepository.save(frontendEmp);

        String[] resources = {"EMPLOYEE", "DEPARTMENT", "PRODUCT"};

        List<Permission> savedPermissions = new ArrayList<>();

        for (String resource : resources) {
            for (Action action : Action.values()) {
                String description = resource + " " + action.getDescription();
                Permission permission = permissionUseCase.createPermission(resource, action, description);
                savedPermissions.add(permission);
            }
        }

        List<Long> employeePermissionIds = savedPermissions.stream()
                .filter(p -> p.getResource().equals("EMPLOYEE"))
                .map(Permission::getId)
                .toList();

        permissionGroupService.createPermissionGroup("EMPLOYEE_MANAGEMENT", employeePermissionIds);
    }

}
