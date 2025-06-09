package com.sbtech.erp.attendance.domain.mapper;

import com.sbtech.erp.attendance.adapter.out.persistence.entity.AttendanceEntity;
import com.sbtech.erp.attendance.domain.model.Attendance;
import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.employee.mapper.EmployeeMapper;

import java.util.List;

public class AttendanceMapper {

    public static Attendance toDomain(AttendanceEntity entity) {
        return Attendance.create(
                entity.getId(),
                entity.getDate(),
                entity.getCheckIn(),
                entity.getCheckOut(),
                EmployeeMapper.toDomain(entity.getEmployee())
        );
    }

    public static List<Attendance> toDomain(List<AttendanceEntity> entities) {
        return entities.stream()
                .map(AttendanceMapper::toDomain)
                .toList();
    }


    public static AttendanceEntity toEntity(Attendance domain) {
        return AttendanceEntity.builder()
                .id(domain.getId())
                .date(domain.getDate())
                .checkIn(domain.getCheckIn())
                .checkOut(domain.getCheckOut())
                .employee(EmployeeMapper.toEntity(domain.getEmployee()))
                .build();
    }

    public static List<AttendanceEntity> toEntity(List<Attendance> domains) {
        return domains.stream()
                .map(AttendanceMapper::toEntity)
                .toList();
    }
}