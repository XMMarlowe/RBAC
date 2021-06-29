package com.marlowe.rbac.controller;


import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.commons.result.Result;
import com.marlowe.rbac.entity.User;
import com.marlowe.rbac.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author marlowe
 * @since 2021-06-26
 */
@Api(tags = "用户管理控制器")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/all/{pageNo}/{pageSize}")
    public Result<List<User>> findAll(@PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<User> pageInfo = userService.findAll(pageNo, pageSize);
        List<User> users = pageInfo.getList();
        return Result.ok(users);
    }


    /**
     * 通过id查找用户
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "通过id查找用户")
    @GetMapping("{id}")
    public Result findUserById(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        return Result.ok(user);
    }

}
