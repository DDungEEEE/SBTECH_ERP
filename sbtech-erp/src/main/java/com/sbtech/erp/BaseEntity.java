package com.sbtech.erp;

import com.sbtech.erp.employee.domain.Employee;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

import java.sql.Timestamp;

@MappedSuperclass
public abstract class BaseEntity {
    @ManyToOne
    @JoinColumn(name = "create_by")
    protected Employee createdBy;

    @ManyToOne
    @JoinColumn(name = "approved_by")
    protected Employee approvedBy;

    protected Timestamp createdAt;

    protected  Timestamp updatedAt;
}
