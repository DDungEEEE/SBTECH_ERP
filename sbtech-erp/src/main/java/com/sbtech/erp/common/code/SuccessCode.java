package com.sbtech.erp.common.code;

import lombok.Getter;

@Getter
public enum SuccessCode {

    SELECT_SUCCESS(200, "SELECT_SUCCESS"),

    DELETE_SUCCESS(200,  "DELETE_SUCCESS"),

    INSERT_SUCCESS(201,  "INSERT_SUCCESS"),

    UPDATE_SUCCESS(200,  "UPDATE_SUCCESS");

    private final int  status;

    private final String message;

    SuccessCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
