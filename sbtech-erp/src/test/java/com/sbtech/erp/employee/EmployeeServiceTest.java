package com.sbtech.erp.employee;

import com.sbtech.erp.department.application.port.out.DepartmentRepository;
import com.sbtech.erp.department.domain.model.Department;
import com.sbtech.erp.employee.application.port.in.EmployeeUseCase;
import com.sbtech.erp.employee.application.port.out.EmployeeRepository;
import com.sbtech.erp.employee.application.service.EmployeeService;
import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.employee.domain.model.EmployeeStatus;
import com.sbtech.erp.employee.domain.model.Password;
import com.sbtech.erp.employee.domain.model.Rank;
import com.sbtech.erp.organization.application.port.out.PositionRepository;
import com.sbtech.erp.organization.domain.model.Position;
import com.sbtech.erp.permission.application.facade.EmployeeApprovalFacade;
import com.sbtech.erp.permission.domain.role.model.SystemRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PositionRepository positionRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmployeeUseCase employeeUseCase;

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private EmployeeApprovalFacade employeeApprovalFacade;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Employee newPendingEmployee() {
        return Employee.createForSignUp(
                1L,
                "홍길동",
                "hong123",
                Password.encoded("password123")
        );
    }


    @Test
    @DisplayName("신규 직원 등록 시 상태는 PENDING_APPROVAL 이어야 한다")
    void registerEmployee_shouldBePendingApproval() {
        // given
        Employee pendingEmployee = newPendingEmployee();
        when(employeeRepository.save(any(Employee.class))).thenReturn(pendingEmployee);

        // when
        Employee result = employeeService.register("홍길동", "hong123", "password123");

        // then
        assertThat(result.getStatus()).isEqualTo(EmployeeStatus.PENDING_APPROVAL);
        assertThat(result.getName()).isEqualTo("홍길동");
        assertThat(result.getLoginId()).isEqualTo("hong123");
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    @DisplayName("관리자가 직원 등록을 승인하면 상태는 ACTIVE 로 변경된다")
    void approveEmployee_shouldBeActive() {
        // given
        Employee pendingEmployee = newPendingEmployee();
        Position position = Position.create(1L, "개발자", true);
        Department department = Department.create(1L, "개발팀", null);

        when(employeeUseCase.findById(1L)).thenReturn(pendingEmployee);
        when(positionRepository.findById(1L)).thenReturn(position);
        when(departmentRepository.findById(1L)).thenReturn(department);
        when(employeeRepository.save(any(Employee.class))).thenAnswer(inv -> inv.getArgument(0));

        // when
        Employee approved = employeeApprovalFacade.approveEmployeeRegistration(
                1L,   // positionId
                1L,   // departmentId
                1L,   // targetId (employeeId)
                Rank.STAFF,
                SystemRole.ADMIN
        );

        // then
        assertThat(approved.getStatus()).isEqualTo(EmployeeStatus.ACTIVE);
        assertThat(approved.getDepartment().getName()).isEqualTo("개발팀");
        assertThat(approved.getPosition().getName()).isEqualTo("개발자");
        verify(employeeUseCase).findById(1L);
    }
    @Test
    @DisplayName("직원이 휴직 요청 시 상태는 ON_LEAVE_PENDING 으로 변경된다")
    void requestLeave_shouldBeOnLeavePending() {
        // given
        Employee pendingEmployee = newPendingEmployee();
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(pendingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenAnswer(inv -> inv.getArgument(0));

        // when
        Employee leaveRequested = employeeService.requestLeave(1L);

        // then
        assertThat(leaveRequested.getStatus()).isEqualTo(EmployeeStatus.ON_LEAVE_PENDING);
        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    @DisplayName("관리자가 휴직 요청을 승인하면 상태는 ON_LEAVE 로 변경된다")
    void approveLeave_shouldBeOnLeave() {
        // given
        Employee employee = newPendingEmployee()
                .approveRegistration(
                        Position.create(1L, "개발자", true),
                        Rank.STAFF,
                        Department.create(1L, "개발팀", null),
                        SystemRole.USER
                )
                .requestLeave();

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenAnswer(inv -> inv.getArgument(0));

        // when
        Employee leaveApproved = employeeService.approveLeave(1L);

        // then
        assertThat(leaveApproved.getStatus()).isEqualTo(EmployeeStatus.ON_LEAVE);
        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    @DisplayName("직원이 퇴사 요청 시 상태는 RETIRED_PENDING 으로 변경된다")
    void requestRetire_shouldBeRetiredPending() {
        // given
        Employee pendingEmployee = newPendingEmployee();
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(pendingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenAnswer(inv -> inv.getArgument(0));

        // when
        Employee retireRequested = employeeService.requestRetire(1L);

        // then
        assertThat(retireRequested.getStatus()).isEqualTo(EmployeeStatus.RETIRED_PENDING);
        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    @DisplayName("관리자가 퇴사 요청을 승인하면 상태는 RETIRED 로 변경된다")
    void approveRetire_shouldBeRetired() {
        // given
        Employee pendingEmployee = newPendingEmployee();
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(pendingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenAnswer(inv -> inv.getArgument(0));

        // when
        Employee retired = employeeService.approveRetire(1L);

        // then
        assertThat(retired.getStatus()).isEqualTo(EmployeeStatus.RETIRED);
        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(any(Employee.class));
    }
}