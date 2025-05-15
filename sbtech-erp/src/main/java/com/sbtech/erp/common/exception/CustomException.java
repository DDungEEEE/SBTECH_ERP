package com.sbtech.erp.common.exception;

import com.sbtech.erp.common.code.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.errorCode = errorCode;
    }
    public CustomException(ErrorCode errorCode, String data){
        super(errorCode.getReason() + data);
        this.errorCode = errorCode;
    }
}
