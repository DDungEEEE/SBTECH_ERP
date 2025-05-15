package com.sbtech.erp.permission.domain;

import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.organization.domain.Position;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "role_permission",
        uniqueConstraints = @UniqueConstraint(columnNames = {"position_id", "employee_rank", "permission_id"}))
public class RolePermission {

    @Id
    @Column(name = "role_permission_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_rank", nullable = false)
    private Rank rank;

    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    @Builder
    public RolePermission(Position position, Rank rank, Permission permission){
        this.position = position;
        this.rank = rank;
        this.permission = permission;
    }
}
