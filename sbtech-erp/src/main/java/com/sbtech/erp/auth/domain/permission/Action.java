package com.sbtech.erp.auth.domain.permission;

import lombok.Getter;

@Getter
public enum Action{
    CREATE("생성"), EDIT("수정"), DELETE("삭제"), VIEW("조회");
    private final String description;

    Action(String description) {
        this.description = description;
    }
}
