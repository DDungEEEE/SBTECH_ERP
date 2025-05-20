package com.sbtech.erp.permission.domain.core;

import com.sbtech.erp.common.BaseTimeEntity;
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
public class Permission extends BaseTimeEntity {

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
    public Permission(String resource, Action action, String description) {
        this.resource = resource;
        this.action = action;
        this.description = description;
    }
}