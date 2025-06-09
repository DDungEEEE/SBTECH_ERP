package com.sbtech.erp.attendance.application.service;

import com.sbtech.erp.attendance.application.port.in.AttendanceUseCase;
import com.sbtech.erp.attendance.application.port.out.AttendanceRepository;
import com.sbtech.erp.attendance.domain.model.Attendance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AttendanceService implements AttendanceUseCase {
    private final AttendanceRepository attendanceRepository;

    @Override
    public Attendance employeeCheckIn(Long attendanceId) {
        Attendance attendance = attendanceRepository.findById(attendanceId).get();
        return attendanceRepository.save(attendance.checkIn());
    }

    @Override
    public Attendance employeeCheckOut(Long attendanceId) {
        Attendance attendance = attendanceRepository.findById(attendanceId).get();
        return attendanceRepository.save(attendance.checkOut());
    }
}
