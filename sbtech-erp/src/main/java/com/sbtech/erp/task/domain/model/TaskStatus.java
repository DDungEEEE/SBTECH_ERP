package com.sbtech.erp.task.domain.model;

public enum TaskStatus {
    TODO, IN_PROGRESS, DONE, CANCELED;

    public boolean canTransitionTo(TaskStatus target){
        return switch (this){
            case TODO -> target == IN_PROGRESS || target == CANCELED;
            case IN_PROGRESS -> target == DONE || target == CANCELED;
            case DONE, CANCELED -> false;
        };
    }
}
