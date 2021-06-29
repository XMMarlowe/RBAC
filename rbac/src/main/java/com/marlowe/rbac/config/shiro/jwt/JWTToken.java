package com.marlowe.rbac.config.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @auther yincaiTA
 * @date 2021/3/17 08:29
 * @description 证书或令牌 定义为 token
 */
public class JWTToken implements AuthenticationToken {

    // 证书/密钥
    private String token;

    public JWTToken() {
    }

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
