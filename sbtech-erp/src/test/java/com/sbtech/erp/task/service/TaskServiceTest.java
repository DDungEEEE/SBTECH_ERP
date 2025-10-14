package com.sbtech.erp.task.service;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.employee.application.port.in.EmployeeUseCase;
import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.employee.domain.model.EmployeeStatus;
import com.sbtech.erp.employee.domain.model.Password;
import com.sbtech.erp.task.adapter.in.dto.TaskCreateRequest;
import com.sbtech.erp.task.application.port.out.TaskRepository;
import com.sbtech.erp.task.application.service.TaskService;
import com.sbtech.erp.task.domain.model.Task;
import com.sbtech.erp.task.domain.model.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    private TaskRepository taskRepository;
    private EmployeeUseCase employeeUseCase;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        employeeUseCase = mock(EmployeeUseCase.class);
        taskService = new TaskService(taskRepository, employeeUseCase);
    }

    @Test
    void 업무_생성_성공() {
        // given
        Employee assignee = Employee.createFull(
                1L, "홍길동", "hong",
                Password.encoded("enc"),
                null, null, null, null,
                EmployeeStatus.ACTIVE
        );
        Employee creator = Employee.createFull(
                2L, "관리자", "admin",
                Password.encoded("enc"),
                null, null, null, null,
                EmployeeStatus.ACTIVE
        );
        when(employeeUseCase.findById(1L)).thenReturn(assignee);
        when(employeeUseCase.findById(2L)).thenReturn(creator);

        TaskCreateRequest dto = new TaskCreateRequest(
                "업무 제목",
                "업무 설명",
                1L,
                2L,
                LocalDate.now(),
                LocalDate.now().plusDays(1)
        );

        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        Task result = taskService.createTask(dto);

        // then
        verify(taskRepository).save(captor.capture());
        assertThat(result.getTitle()).isEqualTo("업무 제목");
        assertThat(result.getStatus()).isEqualTo(TaskStatus.TODO);
        assertThat(captor.getValue().getAssignee()).isEqualTo(assignee);
    }

    @Test
    void 본인_업무가_아닌데_상태변경시_예외발생() {
        // given
        Employee assignee = Employee.createFull(
                1L, "홍길동", "hong",
                Password.encoded("enc"),
                null, null, null, null,
                EmployeeStatus.ACTIVE
        );
        Task task = Task.createNew("제목", "설명", assignee, assignee, LocalDate.now(), LocalDate.now().plusDays(1));

        when(taskRepository.findById(99L)).thenReturn(Optional.of(task));

        // when & then
        assertThatThrownBy(() -> taskService.changeMyTaskStatus(99L, TaskStatus.DONE, 999L))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.NO_PERMISSION_ERROR.getReason());
    }

    @Test
    void 업무_승인_성공() {
        // given
        Employee admin = Employee.createFull(
                10L, "관리자", "admin",
                Password.encoded("enc"),
                null, null, null, null,
                EmployeeStatus.ACTIVE
        );
        Employee assignee = Employee.createFull(
                1L, "홍길동", "hong",
                Password.encoded("enc"),
                null, null, null, null,
                EmployeeStatus.ACTIVE
        );

        Task task = Task.createNew("제목", "설명", assignee, admin, LocalDate.now(), LocalDate.now().plusDays(1))
                .changeStatus(TaskStatus.IN_PROGRESS)
                .changeStatus(TaskStatus.DONE);

        when(employeeUseCase.findById(10L)).thenReturn(admin);
        when(taskRepository.findById(123L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        Task approved = taskService.approveTaskCompletion(123L, 10L);

        // then
        assertThat(approved.getCompletedAt()).isNotNull();
    }

    @Test
    void 업무_조회_없으면_예외() {
        when(taskRepository.findById(777L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> taskService.findById(777L))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.NOT_FOUND_TASK_ERROR.getReason());
    }
}