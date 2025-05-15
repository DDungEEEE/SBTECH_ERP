package com.sbtech.erp.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "COMMON_001", "SERVER ERROR"),
    DUPLICATED_DEPARTMENT_ERROR(409, "DEPARTMENT_001", "Duplicated Department Name Error"),
    INVALID_TOKEN_ERROR(401, "AUTH_001", "유효하지 않은 토큰입니다."),
    NO_PERMISSION_ERROR(401, "AUTH_002", "해당 리소스에 대한 권한이 없습니다.");

    private final int status;
    private final String divisionCode;
    private final String reason;
}
