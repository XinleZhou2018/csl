package com.csl.exception;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//读取异常code文件
//关联类和配置文件
@ConfigurationProperties(prefix = "csl")
//解决spring boot 读取properties文件乱码问题 https://blog.csdn.net/javahighness/article/details/92674878
@PropertySource(value = "classpath:config/exception-codes.properties",encoding = "utf-8")
@Component
public class ExceptionConfiguration {
    private Map<Integer, String> codes = new HashMap<>();

    public Map<Integer, String> getCodes() {
        return codes;
    }

    public void setCodes(Map<Integer, String> codes) {
        this.codes = codes;
    }

    //根据状态码返回message
    public String getMessage(int code){
        return codes.get(code);
    }
}
