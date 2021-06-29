package com.marlowe.rbac.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * 要有属性的setter, 不然不能从application配置文件中注入自定义的值
 *
 * @auther yincaiTA
 * @date 2021/3/16 11:27
 * @description jwt生成和校验的工具类
 */
@Component
@ConfigurationProperties(prefix = "self.jwt")
public class JWTUtils {

    /**
     * 密钥
     */
    private String secret;
    /**
     * 过期时间(ms)
     */
    private long expire;
    /**
     * 前端请求头部
     */
    private String header;

    /**
     * 校验token是否正确并对应一个用户
     *
     * @param token    令牌
     * @param username 用户名
     * @param secret   加密密钥
     * @return 校验是否通过, 看token中是否包含 username并且 = real_database_username 的信息
     */
    public boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名, x min后过期x
     *
     * @param username 用户名
     * @param secret   加密密钥
     * @return 加密的token
     */
    public String sign(String username, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis() + this.expire);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 判断过期
     *
     * @param token 令牌
     * @return token 是否过期
     */
    public boolean isExpire(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return System.currentTimeMillis() > jwt.getExpiresAt().getTime();
    }

    public String getSecret() {
        return secret;
    }

    public long getExpire() {
        return expire;
    }

    public String getHeader() {
        return header;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}

