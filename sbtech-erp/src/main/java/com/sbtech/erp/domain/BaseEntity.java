package com.sbtech.erp.domain;

import jakarta.persistence.MappedSuperclass;

import java.sql.Timestamp;

@MappedSuperclass
public abstract class BaseEntity {
    protected Timestamp createdAt;
    protected  Timestamp updatedAt;
}
