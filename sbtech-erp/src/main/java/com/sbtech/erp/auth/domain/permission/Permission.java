package com.sbtech.erp.auth.domain.permission;

import com.sbtech.erp.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "permission")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Permission extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String domain;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Action action;

    @Column(length = 200)
    private String description;

    @Column(nullable = false, unique = true)
    private String code;

    @PrePersist
    @PreUpdate
    protected void setPermissionCode() {
        if (this.domain != null && this.action != null) {
            this.code = this.domain.toUpperCase() + "_" + this.action.name();
        }
    }
}
