package com.sbtech.erp.organization.domain;

import com.sbtech.erp.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Position  extends BaseTimeEntity {

    @Id @Column(name = "position_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "position_name", unique = true)
    private String name; // 직급명: "과장", "부장" 등

    @Column(name = "is_active")
    private boolean isActive; // 사용 여부

    // 테스트 더미 데이터를 만들기 위한 생성자
    @Builder
    public Position(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }
}