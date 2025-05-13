package com.sbtech.erp.employee.application.service;

import com.sbtech.erp.department.domain.Department;
import com.sbtech.erp.employee.adapter.in.dto.EmployeeCreateReq;
import com.sbtech.erp.employee.domain.Employee;
import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.organization.domain.Position;

public class EmployeeMapper {

    private EmployeeMapper(){
    }

    /*
    사용자가 스스로 회원가입 할 때는 position, role, department를 기입하지 않는다.
    그러므로 null이 들어가고, 관리자가 회원가입 시킬 떄는 직접 position, role, department를 기입한다.
     */
    public static Employee toEntity(EmployeeCreateReq req, Department department, Position position) {
        // 직접 create 호출
        return Employee.create(
                req.name(),
                req.loginId(),
                req.password(),
                position,
                department
        );
    }

    public static void applyApprovalFields(Employee employee, Department department, Position position, Rank rank){
        employee.approveRegistration(department, position, rank);
    }
}
