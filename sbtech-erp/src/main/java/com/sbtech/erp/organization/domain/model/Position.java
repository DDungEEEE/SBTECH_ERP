package com.sbtech.erp.organization.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Position {
    private Long id;
    private String name;
    private boolean isActive;

    public static Position create(Long id, String name, boolean isActive){
        return new Position(id, name, isActive);
    }
}
