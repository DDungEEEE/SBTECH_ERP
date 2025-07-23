package com.sbtech.erp.employee.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sbtech.erp.organization.domain.model.Position;
import com.sbtech.erp.department.domain.model.Department;
import com.sbtech.erp.permission.domain.role.model.SystemRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Employee {
    private final Long id;

    private final String name;

    private final String loginId;

    private final Password password;

    private final Position position;

    private final Rank rank;

    private final Department department;

    private final SystemRole systemRole;

    private final EmployeeStatus status;

    /**
     * 회원가입 시에는 사용자의 아이디, password, 실명만 넣어주고
     * 관리자의 승인 시에 직무, 직급 등을 넣어주므로 나머지 data = null 세팅
     */
    private Employee(Long id, String name, String loginId, Password password) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.rank = null;
        this.position = null;
        this.department = null;
        this.systemRole = null;
        this.status = null;
    }

    public Employee approveRegistration(Position position, Rank rank, Department department, SystemRole systemRole){
        return new Employee(
                this.id,
                this.name,
                this.loginId,
                this.password,
                position,
                rank,
                department,
                systemRole,
                EmployeeStatus.ACTIVE
        );
    }

    @JsonIgnore
    public boolean isPendingEmployee(){
        return this.status.equals(EmployeeStatus.PENDING_APPROVAL);
    }

    /**
     * Entity -> Domain 매핑 시 모든 데이터를 담은 Model 필요
     */
    public static Employee createFull(
            Long id, String name, String loginId, Password password,
            Position position, Rank rank, Department department,
            SystemRole systemRole, EmployeeStatus status
    ) {
        return new Employee(id, name, loginId, password, position, rank, department, systemRole, status);
    }
    public static Employee createForSignUp(
            Long id,
            String name,
            String loginId,
            Password password
    ) {
        return new Employee(id, name, loginId, password);
    }
}
