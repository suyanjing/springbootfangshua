package com.example.bootdemo.exception;

import lombok.Data;

@Data
public class BussinessException extends RuntimeException {

    private static final long serialVersionUID = 1240015972592825169L;
    private boolean logged;
    private String errorCode = "0"; // 错误代码 默认为0
    private String message;

    public BussinessException(boolean logged,String errorCode,String message){
        super(message);
        this.errorCode = errorCode;
        this.logged = logged;
        this.message = message;
    }

}
