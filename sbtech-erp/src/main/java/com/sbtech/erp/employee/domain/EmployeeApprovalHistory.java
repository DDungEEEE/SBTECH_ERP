package com.sbtech.erp.employee.domain;

import com.sbtech.erp.employee.domain.Employee;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class EmployeeApprovalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee targetEmployee; // 승인 대상자

    @ManyToOne
    @JoinColumn(name = "approver_id")
    private Employee approvedBy; // 승인자

    @CreationTimestamp
    private LocalDateTime approvedAt;

    private String memo; // 메모 (선택)
}
