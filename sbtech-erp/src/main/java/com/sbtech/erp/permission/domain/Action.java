package com.sbtech.erp.permission.domain;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Action{
    CREATE("생성"), EDIT("수정"), DELETE("삭제"), VIEW("조회");
    private final String description;

    public static Action from(String action){
        return Action.valueOf(action);
    }
    Action(String description) {
        this.description = description;
    }
}
