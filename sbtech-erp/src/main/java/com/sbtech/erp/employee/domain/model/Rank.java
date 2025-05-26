package com.sbtech.erp.employee.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Rank {
    INTERN("인턴"),
    STAFF("사원"),
    ASSISTANT("주임"),
    SENIOR("대리"),
    MANAGER("과장"),
    DIRECTOR("차장"),
    LEAD("부장"),
    EXECUTIVE("이사");

    private final String label;

    public static Rank from(String description){
        return Arrays.stream(Rank.values()).filter(rank -> rank.getLabel().equals(description)).findFirst().get();
    }
}
