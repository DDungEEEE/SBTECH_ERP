package com.sbtech.erp.common;

import lombok.Builder;

public record ApiResponse<T>(
        boolean success,
        T data,
        String message,
        int code
) {

    public static <T> ApiResponse<T> success(T data, SuccessCode successCode) {
        return new ApiResponse<>(true, data, successCode.getMessage(), successCode.getStatus());
    }

    public static <T> ApiResponse<T> fail(String message, int code) {
        return new ApiResponse<>(false, null, message, code);
    }
}
