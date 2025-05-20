package com.sbtech.erp.permission.domain.role;

import com.sbtech.erp.common.BaseTimeEntity;
import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.organization.domain.Position;
import com.sbtech.erp.permission.domain.group.PermissionGroup;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "role_permission_group",
        uniqueConstraints = @UniqueConstraint(columnNames = {"position_id", "employee_rank", "permission_group_id"}))
public class RolePermissionGroup extends BaseTimeEntity {

    @Id
    @Column(name = "role_permission_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_rank", nullable = false)
    private Rank rank;

    @ManyToOne
    @JoinColumn(name = "permission_group_id", nullable = false)
    private PermissionGroup permissionGroup;

    @Builder
    public RolePermissionGroup(Position position, Rank rank, PermissionGroup permissionGroup){
        this.position = position;
        this.rank = rank;
        this.permissionGroup = permissionGroup;
    }
}
