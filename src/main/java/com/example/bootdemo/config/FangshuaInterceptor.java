package com.example.bootdemo.config;

import com.example.bootdemo.exception.BussinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Component
public class FangshuaInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断请求是否属于方法的请求
        if(handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod) handler;

            //获取方法中的注解，看是否有该注解
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if (accessLimit == null){
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean login = accessLimit.needLogin();
            String key = request.getRequestURI();
            if (login){
                key += ""+"1";//这里假设用户是1，项目中是动态获取的userId
            }
            ValueOperations<Serializable,Integer> operations = redisTemplate.opsForValue();
            if(redisTemplate.opsForValue().get(key) == null){//如果没有key，就是首次请求
                operations.set(key, 1);
                redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
            }else if (operations.get(key) < maxCount ){//加1
                operations.increment(key);
            }else{
                throw new BussinessException(true, "101", "重复提交");
            }

        }
        return true;
    }
}
