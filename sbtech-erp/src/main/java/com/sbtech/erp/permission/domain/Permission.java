package com.sbtech.erp.permission.domain;

import com.sbtech.erp.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity @ToString
@Table(name = "permission")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Permission extends BaseTimeEntity {

    @Id
    @Column(name = "permission_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String domain;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Action action;

    @Column(name = "permission_description")
    private String description;

    @Column(name = "permission_code", nullable = false, unique = true)
    private String code;

    @Builder
    private Permission(String domain, String action, String description){
        this.domain = domain;
        this.description = description;
        this.action = Action.from(action);
    }

    @PrePersist
    @PreUpdate
    protected void setPermissionCode() {
        if (this.code == null && this.domain != null && this.action != null) {
            this.code = this.domain.toUpperCase() + "_" + this.action.name();
        }
    }
}
