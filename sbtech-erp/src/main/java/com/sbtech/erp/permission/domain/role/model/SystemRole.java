package com.sbtech.erp.permission.domain.role.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum SystemRole {
    ADMIN("관리자", "ROLE_ADMIN"), USER("사용자", "ROLE_USER");

    private final String description;
    private final String authority;

    public static SystemRole from(String description) {
        return Arrays.stream(SystemRole.values())
                .filter(r -> r.description.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 권한입니다: " + description));
    }
}
