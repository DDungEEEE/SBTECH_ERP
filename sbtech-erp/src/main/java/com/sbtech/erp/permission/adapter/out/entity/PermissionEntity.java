package com.sbtech.erp.permission.adapter.out.entity;

import com.sbtech.erp.common.BaseTimeEntity;
import com.sbtech.erp.permission.model.Action;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(
        name = "permission",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"resource", "action"})
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PermissionEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long id;

    @Column(name = "resource", nullable = false)
    private String resource; // e.g., "PRODUCT"

    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false)
    private Action action; // e.g., Action.READ

    @Column(name = "permission_description")
    private String description;

    @Builder
    public PermissionEntity(String resource, Action action, String description) {
        this.resource = resource;
        this.action = action;
        this.description = description;
    }
}