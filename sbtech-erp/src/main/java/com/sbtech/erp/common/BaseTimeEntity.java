package com.sbtech.erp.common;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class BaseTimeEntity implements Serializable {

    @CreationTimestamp
    @Column(name = "created_at")
    protected Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    protected Timestamp updatedAt;

}
