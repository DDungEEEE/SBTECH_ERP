package com.sbtech.erp.permission.adapter.out.persistence.entity;

import com.sbtech.erp.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "permission_group")
public class PermissionGroupEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_group_id")
    private Long id;

    @Column(name = "group_name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "permissionGroup", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PermissionGroupItemEntity> permissions = new ArrayList<>();


    public void addPermission(PermissionEntity permissionEntity) {
        this.permissions.add(new PermissionGroupItemEntity(this, permissionEntity));
    }

    @Builder
    public PermissionGroupEntity(Long id,String name, List<PermissionGroupItemEntity> permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions != null ? permissions : new ArrayList<>();
    }
}