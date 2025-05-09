package com.sbtech.erp.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "COMMON_001", "SERVER ERROR"),
    DUPLICATED_DEPARTMENT_ERROR(409, "DEPARTMENT_001", "Duplicated Department Name Error");
    private final int status;
    private final String divisionCode;
    private final String reason;
}
