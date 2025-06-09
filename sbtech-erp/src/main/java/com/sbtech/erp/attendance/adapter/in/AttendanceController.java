package com.sbtech.erp.attendance.adapter.in;

import com.sbtech.erp.attendance.application.port.in.AttendanceUseCase;
import com.sbtech.erp.attendance.domain.model.Attendance;
import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.security.user.EmployeeUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/attendances")
@RestController
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceUseCase attendanceUseCase;

    @GetMapping("/list")
    public ResponseEntity<SuccessResponse<List<Attendance>>> getAllAttendances() {
        List<Attendance> attendances = attendanceUseCase.getAttendances();
        return ResponseEntity.ok(SuccessResponse.<List<Attendance>>builder()
                .data(attendances)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build());
    }

    @PatchMapping("/check-in")
    public ResponseEntity<SuccessResponse<Attendance>> employeeCheckIn(@AuthenticationPrincipal EmployeeUserDetails employeeUserDetails) {

        Attendance attendance = attendanceUseCase.employeeCheckIn(employeeUserDetails.getEmployeeEntity().getId());

        return ResponseEntity.ok(SuccessResponse.<Attendance>builder()
                .data(attendance)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build());
    }
    @PatchMapping("/check-out")
    public ResponseEntity<SuccessResponse<Attendance>> employeeCheckOut(@AuthenticationPrincipal EmployeeUserDetails employeeUserDetails) {

        Attendance attendance = attendanceUseCase.employeeCheckOut(employeeUserDetails.getEmployeeEntity().getId());

        return ResponseEntity.ok(SuccessResponse.<Attendance>builder()
                .data(attendance)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build());
    }
}
