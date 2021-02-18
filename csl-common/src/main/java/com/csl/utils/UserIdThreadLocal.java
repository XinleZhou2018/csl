package com.csl.utils;

//https://blog.csdn.net/xiaozh620/article/details/77621961?utm_source=blogxgwz2
public class UserIdThreadLocal {
    //把构造函数私有，外面不能new，只能通过下面两个方法操作
    private UserIdThreadLocal(){}

    private static final ThreadLocal<String> USERIDLOCAL = new ThreadLocal<String>();

    public static void set(String userid){
        USERIDLOCAL.set(userid);
    }

    public static String get(){
        return USERIDLOCAL.get();
    }

    /**tomcat底层 每一个请求都是一个线程，如果每一个请求都启动一个线程，性能就会降低，
        1. 于是就有了线程池，而线程池中的线程并不是真正销毁或真正启动的。
        2. 也就是说这个请求的线程是个可复用的线程，第二次请求可能还会拿到刚刚的线程，
        3. 若不清空，里面本身就有user对象，数据会错乱
        以下可以放在拦截器的afterCompletion方法中
        UserThreadLocal.set(null); //清空本地线程中的user对象数据
    */
}
