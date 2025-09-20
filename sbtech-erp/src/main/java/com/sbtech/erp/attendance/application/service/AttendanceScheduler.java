//package com.sbtech.erp.attendance.application.service;
//
//import com.sbtech.erp.attendance.application.port.in.AttendanceUseCase;
//import com.sbtech.erp.attendance.application.port.out.AttendanceRepository;
//import com.sbtech.erp.attendance.domain.model.Attendance;
//import com.sbtech.erp.employee.application.port.in.EmployeeUseCase;
//import com.sbtech.erp.employee.application.port.out.EmployeeRepository;
//import com.sbtech.erp.employee.domain.model.Employee;
//import lombok.RequiredArgsConstructor;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class AttendanceScheduler {
//    private final AttendanceRepository attendanceRepository;
//    private final EmployeeRepository employeeRepository;
//
//    @Scheduled(cron = "0 0 0 * * *")
//    public void createDailyAttendances(){
//        List<Employee> employees = employeeRepository.findAll();
//        List<Attendance> attendances = employees.stream()
//                .map(Attendance::createForToday).toList();
//        attendanceRepository.saveAll(attendances);
//    }
//}
