package com.sbtech.erp.permission.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "permission_group_item")
public class PermissionGroupItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_group_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_group_id", nullable = false)
    private PermissionGroupEntity permissionGroup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_id", nullable = false)
    private PermissionEntity permission;

    @Builder
    public PermissionGroupItemEntity(PermissionGroupEntity permissionGroupEntity, PermissionEntity permissionEntity) {
        this.permissionGroup = permissionGroupEntity;
        this.permission = permissionEntity;
    }
}