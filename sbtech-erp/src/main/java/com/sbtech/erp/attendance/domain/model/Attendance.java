//package com.sbtech.erp.attendance.domain.model;
//
//import com.sbtech.erp.employee.domain.model.Employee;
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Getter
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
//@Builder(access = AccessLevel.PRIVATE)
//public class Attendance {
//
//    private final Long id;
//    private final LocalDate date;
//    private final LocalDateTime checkIn;
//    private final LocalDateTime checkOut;
//    private final Employee employee; // FK를 Long으로
//
//    public static Attendance create(Long id, LocalDate date, LocalDateTime checkIn, LocalDateTime checkOut, Employee employee) {
//        return Attendance.builder()
//                .id(id)
//                .date(date)
//                .checkIn(checkIn)
//                .checkOut(checkOut)
//                .employee(employee)
//                .build();
//    }
//
//    public static Attendance createForToday(Employee employee) {
//        return Attendance.builder()
//                .date(LocalDate.now())
//                .employee(employee)
//                .build();
//    }
//
//    public Attendance checkIn() {
//        return Attendance.builder()
//                .id(this.id)
//                .date(this.date)
//                .checkIn(LocalDateTime.now()) // 출근 시간 기록!
//                .checkOut(this.checkOut)
//                .employee(this.employee)
//                .build();
//    }
//
//    public Attendance checkOut() {
//        return Attendance.builder()
//                .id(this.id)
//                .date(this.date)
//                .checkIn(this.checkIn)
//                .checkOut(LocalDateTime.now()) // 퇴근 시간 기록!
//                .employee(this.employee)
//                .build();
//
//    }
//}