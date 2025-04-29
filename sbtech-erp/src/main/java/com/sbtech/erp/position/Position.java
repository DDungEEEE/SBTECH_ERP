package com.sbtech.erp.position;

import jakarta.persistence.*;

@Entity
public class Position {

    @Id @Column(name = "position_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "position_name", unique = true)
    private String name; // 직급명: "과장", "부장" 등

    @Column(name = "is_active")
    private boolean isActive; // 사용 여부
}