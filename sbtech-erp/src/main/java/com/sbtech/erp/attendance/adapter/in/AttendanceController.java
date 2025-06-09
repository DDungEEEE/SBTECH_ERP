package com.sbtech.erp.attendance.adapter.in;

import com.sbtech.erp.attendance.application.port.in.AttendanceUseCase;
import com.sbtech.erp.attendance.domain.model.Attendance;
import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceUseCase attendanceUseCase;

    public ResponseEntity<SuccessResponse<List<Attendance>>> getAllAttendances() {
        List<Attendance> attendances = attendanceUseCase.getAllAttendances();
        return ResponseEntity.ok(new SuccessCode<>(attendances));
    }
}
