package com.sbtech.erp.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ServiceErrorHelper {
    public ResponseStatusException notFound(String reason){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, reason);
    }

    public ResponseStatusException badRequest(String reason){
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, reason);
    }
    public ResponseStatusException unAuthorized(String reason){
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, reason);
    }
    public ResponseStatusException forBidden(String reason){
        return new ResponseStatusException(HttpStatus.FORBIDDEN, reason);
    }
}
