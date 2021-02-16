package com.csl.exception;


import com.csl.utils.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionAdvice {
    @Autowired
    private ExceptionConfiguration exceptionConfiguration;

    //处理未知异常，程序员并未捕获的异常
    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR) //500
    public ResultObject handleException(HttpServletRequest request, Exception e){
        //保存在日志中 TODO
        System.out.println(e);

        //返回给前端
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        ResultObject resultObject = new ResultObject(9999, "服务器内部异常", method + " " + requestUrl, null);
        return resultObject;
    }

    //处理已知异常，程序员主动捕获并抛出的异常
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ResultObject> handleHttpException(HttpServletRequest request, HttpException e){
        //使用ResponseEntity灵活设置HttpStatusCode @ResponseStatus注解方式不灵活

        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        ResultObject resultObject = new ResultObject(e.getCode(), exceptionConfiguration.getMessage(e.getCode()), method + " " + requestUrl, null);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //灵活设置httpstatuscode
        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatusCode());

        //使用了ResponseEntity，就不能使用@ResponseBody注解了
        ResponseEntity<ResultObject> responseEntity = new ResponseEntity<>(resultObject, headers, httpStatus);
        return responseEntity;
    }

    // TODO Spring boot参数校验失败抛出的异常，也需要进行处理
    // 参数校验不通过分两种类型，1：url中的参数 2，post body中的参数
    // 经过验证，1,2参数校验同时存在问题的时候，MethodArgumentNotValidException会优先捕捉到异常，原因？
    // 通常按顺序验证到第一个字段不符合验证要求时，就可以直接拒绝请求了。这就涉及到两种校验模式的配置：
        //普通模式（默认是这个模式）: 会校验完所有的属性，然后返回所有的验证失败信息
        //快速失败模式: 只要有一个验证失败，则返回
        //如果想要配置第二种模式，需要添加如下配置类：
        //参考博客：https://www.cnblogs.com/mooba/p/11276062.html
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST) //400
    public ResultObject handleConstraintException(HttpServletRequest request, ConstraintViolationException e){
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        String errorMessage = e.getMessage();

        return new ResultObject(10001, errorMessage, method + " " + requestUrl, null);

//e.getConstraintViolations().forEach();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST) //400
    public ResultObject handleBeanValidation(HttpServletRequest request, MethodArgumentNotValidException e){
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();

        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String errorMessage = this.formatAllErrorMessages(errors);

        return new ResultObject(10001, errorMessage, method + " " + requestUrl, null);
    }

    private String formatAllErrorMessages(List<ObjectError> errors){
        StringBuffer errorMsg = new StringBuffer();
        errors.forEach(error -> errorMsg.append(error.getDefaultMessage()).append(';'));
        return errorMsg.toString();
    }

    // 参数类型不匹配异常 org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST) //400
    public ResultObject handleBindValidation(HttpServletRequest request, MethodArgumentTypeMismatchException e){
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
//        String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();

        return new ResultObject(10001, e.getName() + "参数类型不匹配", method + " " + requestUrl, null);
    }

    // @RequestParam(required = true)， 没有传参的异常
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    @ResponseBody
//    @ResponseStatus(code = HttpStatus.BAD_REQUEST) //400
//    public ResultObject handleBindValidation(HttpServletRequest request, MissingServletRequestParameterException e){
//        String requestUrl = request.getRequestURI();
//        String method = request.getMethod();
////        String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();
//
////        return new ResultObject(10001, errorMessage, method + " " + requestUrl, null);
//
//        return null;
//    }

    //https://www.cnblogs.com/nightOfStreet/p/12875224.html 这个博文中基本涵盖了所有异常类型


}
