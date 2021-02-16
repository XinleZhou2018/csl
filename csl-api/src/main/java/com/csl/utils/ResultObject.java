package com.csl.utils;

public class ResultObject {
    private int code;
    private String message;
    private String request;
    private Object data;

    public ResultObject(int code, String message, String request, Object data){
        this.code = code;
        this.message = message;
        this.request = request;
        this.data = data;
    }

    public static ResultObject success(Object data){
        return new ResultObject(200, "success",null, data);
    }

    //@ResponseBody序列化，必须提供getter()方法
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getRequest() {
        return request;
    }

    public Object getData() {
        return data;
    }
}
