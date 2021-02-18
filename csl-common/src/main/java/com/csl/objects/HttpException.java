package com.csl.objects;

//已知异常，程序员主动捕获并抛出的异常，
// 运行时异常RuntimeException，交给spring统一异常处理一起处理，throw的地方，方法结尾无需添加Throws Exception
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
