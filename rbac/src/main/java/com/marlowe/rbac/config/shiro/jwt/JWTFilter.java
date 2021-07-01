package com.marlowe.rbac.config.shiro.jwt;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marlowe.rbac.commons.result.Result;
import com.marlowe.rbac.utils.JWTUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @auther yincaiTA
 * @date 2021/3/17 10:06
 * @description JWTToken检验过滤器 前端请求带token时进行处理
 */
@Component
public class JWTFilter extends BasicHttpAuthenticationFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private JWTUtils jwtUtils;

    /*
        @Override isAccessAllowed() 时, 该方法是 在认证之前执行
        如果已经认证的用户, 直接放行(即 subject.isAuthenticated() 为 true)
     */

    /**
     * 因为是前后端分离, 所以除了ShiroConfig中定义了放行的路径外, 其余路径皆会访问到这个方法, 因为 subject.isAuthenticated() 总是false
     * 拦截校验
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        // 完成token登入
        // 1. 检查请求头中是否含有token
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(this.jwtUtils.getHeader());
        // 2. 如果客户端没有携带token，拦下请求
        if (null == token || "".equals(token)) {
            this.responseTokenError(response, "请求头异常或token为空值!");
            return false;
        }
        // 3. 如果有，对进行进行token验证
        JWTToken jwtToken = new JWTToken(token);
        try {
            SecurityUtils.getSubject().login(jwtToken);
        } catch (AuthenticationException e) {
            responseTokenError(response, e.getMessage());
            return false;
        } catch (JWTDecodeException e) {
            responseTokenError(response, e.getCause().getMessage());
            return false;
        }

        // 放行 访问controller
        return true;
    }

    /**
     * 拦截器的前置拦截, 前后端分离, 项目中除了需要跨域全局配置之外, 我们再拦截器中也需要提供跨域支持. 这样, 拦截器才不会在进入Controller之前就被限制了
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 无需转发，直接返回Response信息 Token认证错误
     */
    private void responseTokenError(ServletResponse response, String msg) {
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try {
            PrintWriter out = httpServletResponse.getWriter();
            HashMap<String, Object> errorData = new HashMap<>();
            errorData.put("errorCode", "-1");
            errorData.put("errorMsg", msg);
            Result result = Result.ok(errorData);
            // 序列化响应信息
            String data = this.objectMapper.writeValueAsString(result);
            out.append(data);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
