package com.example.bootdemo.config;


import lombok.Data;

@Data
public class ResponseResult<T>  {

    private T data;

    private String code;

    private String message;


    public ResponseResult(String errorCode, String message) {
        this.code = errorCode;
        this.message = message;
    }

    public ResponseResult(String errorCode, String message,T data) {
        this.data = data;
        this.code = errorCode;
        this.message = message;
    }
}