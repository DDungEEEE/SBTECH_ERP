package com.sbtech.erp.employee.application.service;

import com.sbtech.erp.department.Department;
import com.sbtech.erp.employee.adapter.dto.EmployeeCreateReq;
import com.sbtech.erp.employee.domain.Employee;
import com.sbtech.erp.employee.domain.EmployeeRole;
import com.sbtech.erp.position.Position;
import org.springframework.stereotype.Component;

public class EmployeeMapper {

    private EmployeeMapper(){
    }

    public static Employee toEntity(EmployeeCreateReq req, Department department, Position position) {
        // 직접 create 호출
        return Employee.create(
                req.name(),
                req.loginId(),
                req.password(),
                position,
                EmployeeRole.REQUESTER,// 초기 role은 요청자로 설정
                department
        );
    }
}
