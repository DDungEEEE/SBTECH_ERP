package com.sbtech.erp.task.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaskStatus {
    TODO("진행 중"), IN_PROGRESS("시작 전"), DONE("완료"), CANCELED("취소");
    private final String description;

    public boolean canTransitionTo(TaskStatus target){
        return switch (this){
            case TODO -> target == IN_PROGRESS || target == CANCELED;
            case IN_PROGRESS -> target == DONE || target == CANCELED;
            case DONE, CANCELED -> false;
        };
    }
}
