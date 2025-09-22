package com.sbtech.erp.employee;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional // 테스트 후 롤백
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // H2 사용
class EmployeeIntegrationTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeApprovalFacade employeeApprovalFacade;

    @Test
    @DisplayName("신규 직원 등록 시 DB에 저장되고 상태는 PENDING_APPROVAL 이어야 한다")
    void registerEmployee_integration() {
        Employee emp = employeeService.register("홍길동", "hong123", "password123");

        assertThat(emp.getId()).isNotNull(); // DB에 저장됐으므로 PK 있음
        assertThat(emp.getStatus()).isEqualTo(EmployeeStatus.PENDING_APPROVAL);
    }

    @Test
    @DisplayName("직원 등록 승인 시 ACTIVE로 변경되고 DB 반영된다")
    void approveEmployee_integration() {
        Employee emp = employeeService.register("홍길동", "hong123", "password123");

        Position position = Position.create(null, "개발자", true);
        Department department = Department.create(null, "개발팀", null);

        Employee approved = employeeApprovalFacade.approveEmployeeRegistration(
                1L, // positionId (테스트에서는 직접 persist 필요할 수도 있음)
                1L, // departmentId
                emp.getId(),
                Rank.STAFF,
                SystemRole.ADMIN
        );

        assertThat(approved.getStatus()).isEqualTo(EmployeeStatus.ACTIVE);
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
