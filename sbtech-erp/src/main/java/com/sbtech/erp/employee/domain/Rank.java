package com.sbtech.erp.employee.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Rank {
    INTERN("인턴", 0),
    STAFF("사원", 1),
    ASSISTANT("주임", 2),
    SENIOR("대리", 3),
    MANAGER("과장", 4),
    DIRECTOR("차장", 5),
    LEAD("부장", 6),
    EXECUTIVE("이사", 7);

    private final String label;

    private final int level;

    public boolean isAtLeast(Rank other){
        return this.level >= other.level;
    }

    public static Rank from(String rank){
        return Rank.valueOf(rank.toUpperCase());
    }
}
