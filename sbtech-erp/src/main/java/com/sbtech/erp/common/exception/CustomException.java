package com.sbtech.erp.common.exception;

import com.sbtech.erp.common.code.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String reason;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.errorCode = errorCode;
        this.reason = errorCode.getReason();
    }

    public CustomException(ErrorCode errorCode, String overrideReason) {
        super(overrideReason);
        this.errorCode = errorCode;
        this.reason = overrideReason;
    }
}
