package com.example.bootdemo.exception;

import com.example.bootdemo.config.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BussinessException.class)
    public ResponseResult handleBusinessException(BussinessException e) {
        LOGGER.error("业务异常:{}", e);
        return new ResponseResult(e.getErrorCode(), e.getMessage());
    }

    /*@ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e) {
        LOGGER.error("程序异常:{}", e);
        return new ResponseResult(CoreConstants.REQUEST_PROGRAM_ERROR_CODE, e.getMessage(), "");
    }*/
}