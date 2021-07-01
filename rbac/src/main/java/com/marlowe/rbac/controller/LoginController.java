package com.marlowe.rbac.controller;

import com.alibaba.fastjson.JSONObject;
import com.marlowe.rbac.commons.result.Result;
import com.marlowe.rbac.entity.User;
import com.marlowe.rbac.entity.dto.UserLogin;
import com.marlowe.rbac.service.IUserService;
import com.marlowe.rbac.utils.JWTUtils;
import com.marlowe.rbac.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;

/**
 * @program: RBAC
 * @description:
 * @author: Marlowe
 * @create: 2021-06-26 20:01
 **/
@RestController
@Slf4j
@Api(tags = "用户注册、登录、注销控制器")
public class LoginController {
    @Autowired
    private IUserService userService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     * @throws CredentialException
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestParam String username, @RequestParam String password) throws CredentialException {

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return Result.ok("请输入用户名和密码！");
        }
        // 根据加密时候的加密规则
        // 通过账户密码查询用户

        User user = userService.login(username, password);
        if (user == null) {
            return Result.ok("用户名或密码错误!");
        }
        // 返回token
        return Result.ok(jwtUtils.sign(username, jwtUtils.getSecret()));
    }


    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody User user) {

        // 根据用户名查询用户，判断该用户是否注册
        User userByUsername = userService.findUserByUsername(user.getUsername());
        if (userByUsername != null) {
            return Result.ok("该用户已注册，请登录");
        }

        boolean register = userService.register(user);
        if (register) {
            return Result.ok("注册成功");
        } else {
            return Result.ok("注册失败");
        }
    }

    /**
     * 退出登录
     *
     * @return
     */
    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getPrincipal());
        User user = (User) subject.getPrincipal();
        if (subject.isAuthenticated()) {
            // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            subject.logout();
        }
        return Result.ok("用户" + user.getUsername() + "退出登录");
    }


}