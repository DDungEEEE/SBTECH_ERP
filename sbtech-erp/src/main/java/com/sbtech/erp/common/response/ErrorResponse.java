package com.sbtech.erp.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sbtech.erp.common.code.ErrorCode;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonInclude(Include.NON_NULL)
public class ErrorResponse {
    private List<ValidationError> errors; // Validation 검증 오류
    private String divisionCode; // ErrorCode 구분 코드
    private int status; // Error 상태 코드
    private String reason; // ErrorCode 상세화 -> 구체적인 이유

    public ErrorResponse(final ErrorCode errorCode) {
        this.reason = errorCode.getReason();
        this.divisionCode = errorCode.getDivisionCode();
        this.status = errorCode.getStatus();
    }
    public static ErrorResponse from(ErrorCode errorCode){
        return new ErrorResponse(errorCode);
    }

    @Builder
    public ErrorResponse(List<ValidationError> errors) {
        this.errors = errors;
    }

    /**
     * MethodArgumentValidationException 발생 시
     * BindingResult를 매개변수로 받아 ErrorResponse로 변환시켜주는 클래스
     */
    @Getter
    @RequiredArgsConstructor
    public static class ValidationError{
        private final String field;
        private final String value;
        private final String reason;


        public static List<ValidationError> of(final BindingResult bindingResult){
            return bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> new ValidationError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }



}