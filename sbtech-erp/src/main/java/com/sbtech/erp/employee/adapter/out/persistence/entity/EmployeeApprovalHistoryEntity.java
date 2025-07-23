package com.sbtech.erp.employee.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "employee_approval_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeApprovalHistoryEntity {

    @Id
    @Column(name = "employee_approval_history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity targetEmployee; // 승인 대상자

    @ManyToOne
    @JoinColumn(name = "approver_id")
    private EmployeeEntity approvedBy; // 승인자

    @Column(name = "approved_at")
    @CreationTimestamp
    private LocalDateTime approvedAt;

    private String memo; // 메모 (선택)


    private EmployeeApprovalHistoryEntity(Long id, EmployeeEntity targetEmployeeEntity, EmployeeEntity approvedBy, LocalDateTime approvedAt, String memo) {
        this.id = id;
        this.targetEmployee = targetEmployeeEntity;
        this.approvedBy = approvedBy;
        this.memo = memo;
        this.approvedAt = approvedAt;
    }

    public static EmployeeApprovalHistoryEntity create(Long id, EmployeeEntity targetEmployee, EmployeeEntity approvedBy,LocalDateTime approvedAt, String memo){
        return new EmployeeApprovalHistoryEntity(id, targetEmployee, approvedBy,approvedAt,  memo);
    }
}
