package com.sbtech.erp.employee.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
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
        this.status = EmployeeStatus.PENDING_APPROVAL;
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
        return this.status == EmployeeStatus.PENDING_APPROVAL;
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

    /**
     * 회원가입을 위한 생성자
     * name :실명
     * loginId : 로그인 id
     * password : 비밀번호
     */
    public static Employee createForSignUp(
            Long id,
            String name,
            String loginId,
            Password password
    ) {
        return new Employee(id, name, loginId, password);
    }

    public Employee requestLeave()   { return withStatus(EmployeeStatus.ON_LEAVE_PENDING); }
    public Employee approveLeave()   { return withStatus(EmployeeStatus.ON_LEAVE); }
    public Employee requestRetire()  { return withStatus(EmployeeStatus.RETIRED_PENDING); }
    public Employee approveRetire()  { return withStatus(EmployeeStatus.RETIRED); }

    private Employee withStatus(EmployeeStatus newStatus) {
        return new Employee(
                this.id,
                this.name,
                this.loginId,
                this.password,
                this.position,
                this.rank,
                this.department,
                this.systemRole,
                newStatus
        );
    }

    public void validateActive(){
        if(!isActive()){
            throw new CustomException(ErrorCode.USER_NOT_ACTIVE_ERROR);
        }
    }

    public boolean isActive(){
        return this.status == EmployeeStatus.ACTIVE;
    }
}
