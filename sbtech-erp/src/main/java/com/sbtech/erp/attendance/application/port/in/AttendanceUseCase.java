package com.sbtech.erp.attendance.application.port.in;

import com.sbtech.erp.attendance.domain.model.Attendance;

public interface AttendanceUseCase {
    Attendance employeeCheckIn(Long attendanceId);
    Attendance employeeCheckOut(Long attendanceId);

}
