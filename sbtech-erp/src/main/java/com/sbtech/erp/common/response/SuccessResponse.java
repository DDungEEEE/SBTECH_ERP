package com.sbtech.erp.common.response;

import com.sbtech.erp.common.code.SuccessCode;
import lombok.Builder;

import java.io.Serializable;

public class SuccessResponse<T> implements Serializable {
    // API 응답 결과
    private final T data;

    // API 응답 코드
    private final int status;

    // API 응답 메시지
    private final String successMessage;


    @Builder
    public SuccessResponse(final T data, final SuccessCode successCode){
        this.data = data;
        this.status = successCode.getStatus();
        this.successMessage = successCode.getMessage();
    }
}
