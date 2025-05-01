package com.sbtech.erp.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "COMMON-001", "SERVER ERROR");
    private final int status;
    private final String divisionCode;
    private final String reason;
}
