package com.sbtech.erp.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "COMMON_001", "SERVER ERROR"),
    NOT_FOUND_RESOURCE_ERROR(404, "COMMON_002", "해당 데이터를 찾을 수 없습니다."),

    DUPLICATED_DEPARTMENT_ERROR(409, "DEPARTMENT_001", "Duplicated Department Name Error"),
    DUPLICATED_EMPLOYEE_LOGIN_ID_ERROR(401,"EMPLOYEE_001", "이미 존재하는 로그인 ID 입니다."),
    INVALID_TOKEN_ERROR(401, "AUTH_001", "유효하지 않은 토큰입니다."),
    NO_PERMISSION_ERROR(401, "AUTH_002", "해당 리소스에 대한 권한이 없습니다.");

    private final int status;
    private final String divisionCode;
    private final String reason;
}
