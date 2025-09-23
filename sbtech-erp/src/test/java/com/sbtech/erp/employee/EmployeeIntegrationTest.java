package com.sbtech.erp.employee;

import com.sbtech.erp.department.adapter.out.persistence.entity.DepartmentEntity;
import com.sbtech.erp.department.adapter.out.persistence.repository.DepartmentJpaRepository;
import com.sbtech.erp.department.domain.model.Department;
import com.sbtech.erp.employee.application.service.EmployeeService;
import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.employee.domain.model.EmployeeStatus;
import com.sbtech.erp.employee.domain.model.Rank;
import com.sbtech.erp.organization.adapter.out.persistence.entity.PositionEntity;
import com.sbtech.erp.organization.adapter.out.persistence.repository.PositionJpaRepository;
import com.sbtech.erp.organization.domain.model.Position;
import com.sbtech.erp.permission.application.facade.EmployeeApprovalFacade;
import com.sbtech.erp.permission.domain.role.model.SystemRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@SpringBootTest
@Transactional // 각 테스트 후 롤백
class EmployeeIntegrationTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeApprovalFacade employeeApprovalFacade;

    @Autowired
    private PositionJpaRepository positionJpaRepository;

    @Autowired
    private DepartmentJpaRepository departmentJpaRepository;

    @Test
    @DisplayName("신규 직원 등록 시 DB에 저장되고 상태는 PENDING_APPROVAL 이어야 한다")
    void registerEmployee_integration() {
        Employee emp = employeeService.register("홍길동", "hong123", "password123");

        assertThat(emp.getId()).isNotNull(); // PK 발급 확인
        assertThat(emp.getStatus()).isEqualTo(EmployeeStatus.PENDING_APPROVAL);
    }

    @Test
    @DisplayName("직원 등록 승인 시 ACTIVE로 변경되고 DB 반영된다")
    void approveEmployee_integration() {
        // given: 직원 등록
        Employee emp = employeeService.register("홍길동", "hong123", "password123");

        // Position, Department 엔티티를 DB에 저장
        PositionEntity posEntity = positionJpaRepository.save(
                PositionEntity.create(null, "개발자", true)
        );
        DepartmentEntity deptEntity = departmentJpaRepository.save(
                DepartmentEntity.create(null, "개발팀", null)
        );

        // when: 등록 승인
        Employee approved = employeeApprovalFacade.approveEmployeeRegistration(
                posEntity.getId(),
                deptEntity.getId(),
                emp.getId(),
                Rank.STAFF,
                SystemRole.ADMIN
        );

        // then
        assertThat(approved.getStatus()).isEqualTo(EmployeeStatus.ACTIVE);
        assertThat(approved.getDepartment().getName()).isEqualTo("개발팀");
        assertThat(approved.getPosition().getName()).isEqualTo("개발자");
    }

    @Test
    @DisplayName("직원이 휴직 요청하면 ON_LEAVE_PENDING, 승인하면 ON_LEAVE")
    void leaveFlow_integration() {
        Employee emp = employeeService.register("홍길동", "hong123", "password123");

        emp = employeeService.requestLeave(emp.getId());
        assertThat(emp.getStatus()).isEqualTo(EmployeeStatus.ON_LEAVE_PENDING);

        emp = employeeService.approveLeave(emp.getId());
        assertThat(emp.getStatus()).isEqualTo(EmployeeStatus.ON_LEAVE);
    }

    @Test
    @DisplayName("직원이 퇴사 요청하면 RETIRED_PENDING, 승인하면 RETIRED")
    void retireFlow_integration() {
        Employee emp = employeeService.register("홍길동", "hong123", "password123");

        emp = employeeService.requestRetire(emp.getId());
        assertThat(emp.getStatus()).isEqualTo(EmployeeStatus.RETIRED_PENDING);

        emp = employeeService.approveRetire(emp.getId());
        assertThat(emp.getStatus()).isEqualTo(EmployeeStatus.RETIRED);
    }
}
