//package com.sbtech.erp.attendance.adapter.out.persistence.entity;
//
//
//import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
//@Builder
//@Table(name = "attendance")
//public class AttendanceEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private LocalDate date;
//    private LocalDateTime checkIn;
//    private LocalDateTime checkOut;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "employee_id")
//    private EmployeeEntity employee;
//}