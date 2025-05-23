package com.sbtech.erp.employee.domain;

import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeApprovalHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity targetEmployee; // 승인 대상자

    @ManyToOne
    @JoinColumn(name = "approver_id")
    private EmployeeEntity approvedBy; // 승인자

    @CreationTimestamp
    private LocalDateTime approvedAt;

    private String memo; // 메모 (선택)

    @Builder
    private EmployeeApprovalHistoryEntity(Long id, EmployeeEntity targetEmployeeEntity, EmployeeEntity approvedBy, String memo) {
        this.id = id;
        this.targetEmployee = targetEmployeeEntity;
        this.approvedBy = approvedBy;
        this.memo = memo;
    }

    public static EmployeeApprovalHistoryEntity create(Long id, EmployeeEntity targetEmployee, EmployeeEntity approvedBy, String memo){
        return new EmployeeApprovalHistoryEntity(id, targetEmployee, approvedBy, memo);
    }
}
