package com.sbtech.erp.common.exception.handler;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.common.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLException;
import java.util.List;

@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final HttpStatus HTTP_NOT_FOUND_ERROR = HttpStatus.NOT_FOUND;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){

        BindingResult bindingResult = ex.getBindingResult();
        List<ErrorResponse.ValidationError> fieldErrors = ErrorResponse.ValidationError.of(bindingResult);
        log.error("Validation error: {}", fieldErrors);
        return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        log.error("HttpMessageNotReadableException: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex){
        String message = ex.getMessage().toString();
        log.error("NoResourceFoundException: {}", message);
        return new ResponseEntity<>(message, HTTP_NOT_FOUND_ERROR);
    }


    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(CustomException ex){
        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(errorCode);
        log.warn("비즈니스 예외 발생 : {}", errorCode.getReason(), ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatus()));
    }


}

