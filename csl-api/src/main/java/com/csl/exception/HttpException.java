package com.csl.exception;

//已知异常，程序员主动捕获并抛出的异常
public class HttpException extends RuntimeException{
    protected Integer code;
    protected Integer httpStatusCode = 500;

    public HttpException(Integer code, Integer httpStatusCode){
        this.code = code;
        this.httpStatusCode = httpStatusCode;
    }

    // protected属性，需要提供getter()方法，否则外部无法访问
    public Integer getCode() {
        return code;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }
}
