package com.sbtech.erp.attendance.application.port.in;

import com.sbtech.erp.attendance.domain.model.Attendance;

import java.util.List;

public interface AttendanceUseCase {
    Attendance employeeCheckIn(Long attendanceId);
    Attendance employeeCheckOut(Long attendanceId);
    List<Attendance> getAttendances();

}
