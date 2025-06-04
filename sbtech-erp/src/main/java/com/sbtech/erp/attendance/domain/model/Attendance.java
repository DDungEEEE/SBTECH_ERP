package com.sbtech.erp.attendance.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Attendance {

    private final Long id;
    private final LocalDate date;
    private final LocalDateTime checkIn;
    private final LocalDateTime checkOut;
    private final Long employeeId; // FK를 Long으로

    public static Attendance create(Long id, LocalDate date, LocalDateTime checkIn, LocalDateTime checkOut, Long employeeId) {
        return Attendance.builder()
                .id(id)
                .date(date)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .employeeId(employeeId)
                .build();
    }
}