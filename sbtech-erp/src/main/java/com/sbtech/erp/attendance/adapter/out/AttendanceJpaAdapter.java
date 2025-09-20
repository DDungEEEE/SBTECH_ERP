//package com.sbtech.erp.attendance.adapter.out;
//
//import com.sbtech.erp.attendance.adapter.out.persistence.entity.AttendanceEntity;
//import com.sbtech.erp.attendance.adapter.out.persistence.repository.AttendanceJpaRepository;
//import com.sbtech.erp.attendance.application.port.out.AttendanceRepository;
//import com.sbtech.erp.attendance.domain.mapper.AttendanceMapper;
//import com.sbtech.erp.attendance.domain.model.Attendance;
//import com.sbtech.erp.employee.domain.model.Employee;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Repository
//@RequiredArgsConstructor
//public class AttendanceJpaAdapter implements AttendanceRepository {
//    private final AttendanceJpaRepository attendanceJpaRepository;
//
//    @Override
//    public Attendance save(Attendance attendance) {
//        AttendanceEntity attendanceEntity = AttendanceMapper.toEntity(attendance);
//
//        return AttendanceMapper.toDomain(attendanceJpaRepository.save(attendanceEntity));
//    }
//
//    public List<Attendance> saveAll(List<Attendance> attendances) {
//        List<AttendanceEntity> attendanceEntities = AttendanceMapper.toEntity(attendances);
//
//        List<AttendanceEntity> savedEntities = attendanceJpaRepository.saveAll(attendanceEntities);
//
//        return AttendanceMapper.toDomain(savedEntities);
//    }
//
//    @Override
//    public Optional<Attendance> findById(Long id) {
//        return attendanceJpaRepository.findById(id)
//                .map(AttendanceMapper::toDomain);
//    }
//
//    @Override
//    public List<Attendance> findAll() {
//        return AttendanceMapper.toDomain(attendanceJpaRepository.findAll());
//    }
//}
