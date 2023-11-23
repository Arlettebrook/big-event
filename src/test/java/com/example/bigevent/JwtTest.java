package com.example.bigevent;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JwtTest {

    @Test
    public void testToken(){
        //生成jwt的代码
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","张三");
        String token = JWT.create()
                .withClaim("user", claims)//添加要求（load）
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))//添加过期时间12h
                .sign(Algorithm.HMAC256("baidu"));//确定算法配置秘钥

        System.out.println("token = " + token);


    }

    @Test
    public void testParseToken(){
        //定义字符串,模拟用户传递过来的token
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                ".eyJleHAiOjE3MDA3OTM4MDMsInVzZXIiOnsiaWQiOjEsInVzZXJuYW1lIjoi5byg5LiJIn19" +
                ".YcO9Pcr9hCRW79M4dGrxXqMErPlexPtfgV8BtuD8CzM";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("baidu")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);//认证token，生成一个解析后的JWT对象
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));

//        如果修改了头部或者载荷部分或者算法部分的数据，那么验证失败
//        如果算法秘钥修改了，那么验证失败
//        token过期认证也会失败

    }
}
