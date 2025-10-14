package com.sbtech.erp.permission.adapter.out.persistence.entity;

import com.sbtech.erp.common.BaseTimeEntity;
import com.sbtech.erp.employee.domain.model.Rank;
import com.sbtech.erp.organization.adapter.out.persistence.entity.PositionEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "role_permission_group",
        uniqueConstraints = @UniqueConstraint(columnNames = {"position_id", "employee_rank", "permission_group_id"}))
public class RolePermissionGroupEntity extends BaseTimeEntity {

    @Id
    @Column(name = "role_permission_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", nullable = false)
    private PositionEntity position;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_rank", nullable = false)
    private Rank rank;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_group_id", nullable = false)
    private PermissionGroupEntity permissionGroupEntity;


    public static RolePermissionGroupEntity create(Long id, PositionEntity position, Rank rank, PermissionGroupEntity permissionGroupEntity){
        return new RolePermissionGroupEntity(id, position, rank, permissionGroupEntity);
    }

    private RolePermissionGroupEntity(Long id, PositionEntity position, Rank rank, PermissionGroupEntity permissionGroupEntity){
        this.id = id;
        this.position = position;
        this.rank = rank;
        this.permissionGroupEntity = permissionGroupEntity;
    }
}
