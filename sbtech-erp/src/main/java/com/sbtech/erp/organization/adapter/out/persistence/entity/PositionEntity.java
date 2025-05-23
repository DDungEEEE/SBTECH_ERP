package com.sbtech.erp.organization.adapter.out.persistence.entity;

import com.sbtech.erp.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "position")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PositionEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long id;

    @Column(name = "position_name", nullable = false)
    private String name;

    @Column(name = "is_active")
    private boolean isActive;

    public PositionEntity create(Long id, String name, boolean isActive){
        return new PositionEntity(id, name, isActive);
    }
}
