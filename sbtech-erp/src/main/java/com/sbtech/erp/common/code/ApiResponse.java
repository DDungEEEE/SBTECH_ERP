package com.sbtech.erp.common.code;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
        boolean success,
        T data,
        String message,
        int code
) {

    public static <T> ApiResponse<T> success(T data, SuccessCode successCode) {
        return new ApiResponse<>(true, data, null, successCode.getStatus());
    }

    public static <T> ApiResponse<T> fail(String message, int code) {
        return new ApiResponse<>(false, null, message, code);
    }
}
