package com.csl.interceptor;

import com.csl.objects.HttpException;
import com.csl.utils.JsonUtils;
import com.csl.utils.RedisOperator;
import com.csl.utils.ResultObject;
import com.csl.utils.UserIdThreadLocal;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class LoginCheckInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisOperator redisOperator;
    //请求进入Controller之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("进入了拦截器");
        //操作Redis，看看token是否存在Redis里面
//        throw new HttpException(10001,200);
////        returnErrorResponse(response,ResultObject.success("未登录"));
//        return false;

//        let value = { openid: openid, session_key: session_key, userid: userid };
//        //save in Redis
//        setRedis(key, value, 30 * 24 * 60 * 60);

        String session_id = request.getHeader("session_id");
        if (StringUtils.isNotBlank(session_id)){
          String value = redisOperator.get(session_id);
          if (StringUtils.isNotBlank(value)){
              Map userInfo = JsonUtils.jsonToPojo(value, Map.class);
              if (ObjectUtils.isEmpty(userInfo)){
                  throw new HttpException(10002,200);
              }else{
                  String userId = (String) userInfo.get("userid");
                  if (StringUtils.isNotBlank(userId)){
                      UserIdThreadLocal.set(userId);
                      return true;
                  }else{
                      throw new HttpException(10002,200);
                  }
              }
          }else{
              throw new HttpException(10002,200);
          }

        }else{
            throw new HttpException(10002,200);
        }
    }

    //请求访问Controller之后，渲染视图之前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //请求访问Controller之后，渲染视图之后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /**tomcat底层 每一个请求都是一个线程，如果每一个请求都启动一个线程，性能就会降低，
         1. 于是就有了线程池，而线程池中的线程并不是真正销毁或真正启动的。
         2. 也就是说这个请求的线程是个可复用的线程，第二次请求可能还会拿到刚刚的线程，
         3. 若不清空，里面本身就有user对象，数据会错乱
         以下可以放在拦截器的afterCompletion方法中
         UserThreadLocal.set(null); //清空本地线程中的user对象数据
         */

        UserIdThreadLocal.set(null);
    }

    public void returnErrorResponse(HttpServletResponse response, ResultObject resultObject){
        OutputStream outputStream = null;

        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
//            response.setStatus(500);
            outputStream = response.getOutputStream();
            outputStream.write(JsonUtils.objectToJson(resultObject).getBytes("utf-8"));
            outputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (outputStream != null){
                    outputStream.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
