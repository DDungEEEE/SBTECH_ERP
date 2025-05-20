package com.sbtech.erp.permission.domain.group;

import com.sbtech.erp.common.BaseTimeEntity;
import com.sbtech.erp.permission.domain.core.Permission;
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
public class PermissionGroup extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_group_id")
    private Long id;

    @Column(name = "group_name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "permissionGroup", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PermissionGroupItem> permissions = new ArrayList<>();


    public void addPermission(Permission permission) {
        this.permissions.add(new PermissionGroupItem(this, permission));
    }

    @Builder
    public PermissionGroup(String name, List<PermissionGroupItem> permissions) {
        this.name = name;
        this.permissions = permissions != null ? permissions : new ArrayList<>();
    }
}