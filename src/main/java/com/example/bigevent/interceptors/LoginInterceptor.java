package com.example.bigevent.interceptors;

import com.example.bigevent.pojo.Result;
import com.example.bigevent.utils.JwtUtil;
import com.example.bigevent.utils.ThreadLocalUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证
        String token = request.getHeader("Authorization");
        //解析token
        try {
            // 从redis中获取相同的token
            ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
            String cacheToken = stringValueOperations.get(token);
            if(cacheToken == null){
                // token已经失效
                throw new RuntimeException("token已失效");
            }

            Map<String, Object> claims = JwtUtil.parseToken(token);
//            把业务数据存储到ThreadLocal中，它是线程安全，方便获取业务数据
            ThreadLocalUtil.set(claims);
            //放行
            return true;
        } catch (Exception e) {
//            http响应状态码为401
            response.setStatus(401);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            // 返回用户未登录json
            ObjectMapper objectMapper = new ObjectMapper();

            String note = objectMapper.writeValueAsString(Result.error("用户未登录："+(StringUtils.hasLength(e.getMessage())?e.getMessage():"null")));
            response.getWriter().write(note);
            return false;
        }

    }

    //    视图渲染之后执行，pre请求之前拦截，post请求之后拦截，视图渲染之前
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        清空ThreadLocal中的数据,防止内存泄漏
        ThreadLocalUtil.remove();
    }
}
