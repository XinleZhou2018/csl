package com.csl.config;

import com.csl.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //定义一个拦截器
    @Bean
    public LoginCheckInterceptor loginCheckInterceptor(){
        return new LoginCheckInterceptor();
    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor())
                .addPathPatterns("/matchComment/comment")
                .addPathPatterns("/matchComment/replyComment");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    //映射静态资源文件
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/META-INF/resources/")  // 映射swagger2
                .addResourceLocations("file:/Users/zhou/Documents/images/");  // 映射本地静态资源
    }
}
