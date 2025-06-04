package com.sbtech.erp.attendance.domain.mapper;

import com.sbtech.erp.attendance.adapter.out.persistence.entity.AttendanceEntity;
import com.sbtech.erp.attendance.domain.model.Attendance;

public class AttendanceMapper {

    public static Attendance toDomain(AttendanceEntity entity) {
        return Attendance.create(
                entity.getId(),
                entity.getDate(),
                entity.getCheckIn(),
                entity.getCheckOut(),
                entity.getEmployee().getId()
        );
    }

    public static AttendanceEntity toEntity(Attendance domain, com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity employee) {
        return AttendanceEntity.builder()
                .id(domain.getId())
                .date(domain.getDate())
                .checkIn(domain.getCheckIn())
                .checkOut(domain.getCheckOut())
                .employee(employee)
                .build();
    }
}