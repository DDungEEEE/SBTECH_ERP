package com.sbtech.erp.common.code;

import com.sbtech.erp.common.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "COMMON_001", "SERVER ERROR"),
    NOT_FOUND_RESOURCE_ERROR(404, "COMMON_002", "해당 데이터를 찾을 수 없습니다."),

    NOT_FOUND_EMPLOYEE_ERROR(404, "EMPLOYEE_001", "해당 사원을 찾을 수 없습니다."),
    USER_NOT_ACTIVE_ERROR(401, "EMPLOYEE_002", "재직중인 사원이 아닙니다."),

    NOT_FOUND_TASK_ERROR(404, "TASK_001", "해당 업무(Task)를 찾을 수 없습니다."),

    DUPLICATED_DEPARTMENT_ERROR(409, "DEPARTMENT_001", "이미 존재하는 부서 이름입니다."),
    DUPLICATED_EMPLOYEE_LOGIN_ID_ERROR(401,"EMPLOYEE_001", "이미 존재하는 로그인 ID 입니다."),

    INVALID_TOKEN_ERROR(401, "AUTH_001", "유효하지 않은 토큰입니다."),
    NO_PERMISSION_ERROR(401, "AUTH_002", "해당 리소스에 대한 권한이 없습니다."),

    PRODUCT_NOT_FOUND_ERROR(404, "PRODUCT_001", "상품을 찾을 수 없습니다."),

    INVALID_STATUS_CHANGE(401, null, "잘못된 상태 전환입니다."),
    ALREADY_APPROVED_ERROR(401, null, "이미 승인된 업무입니다."),
    INSUFFICIENT_STOCK_ERROR(401, "STOCK_001", "재고가 부족합니다."),

    JOURNAL_LINE_NOT_FOUND_ERROR(404, "JOURNAL_LINE_001", "분개전표를 찾을 수가 없습니다."),

    LEDGER_ACCOUNT_NOT_FOUND_ERROR(404, "LEDGER_ACCOUNT_001", "계정과목을 찾을 수 없습니다."),
    ACCOUNT_TYPE_MISMATCH_ERROR(401, "LEDGER_ACCOUNT_002", "계정유형과 정상잔액이 일치하지 않습니다."),
    INACTIVE_OR_NON_POSTING_ACCOUNT_ERROR(401, "LEDGER_ACCOUNT_003", "전표에 사용할 수 없는 계정입니다."),

    JOURNAL_ENTRY_NOT_FOUND_ERROR(404, "JOURNAL_ENTRY_001", "분개전표를 찾을 수 없습니다."),

    USER_NOT_FOUND_ERROR(401, "AUTH_003", "사용자의 아이디 혹은 패스워드가 일치하지 않습니다"),
    USER_NOT_APPROVAL_ERROR(401, "AUTH_004", "승인되지 않은 사용자입니다.");

    private final int status;
    private final String divisionCode;
    private final String reason;

}
