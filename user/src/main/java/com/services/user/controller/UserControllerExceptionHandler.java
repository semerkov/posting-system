package com.services.user.controller;

import com.services.user.exception.ErrorCode;
import com.services.user.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UserControllerExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseEntity<String> handleUserFoundException(ServiceException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(errorCode.getMessage());
    }
}
