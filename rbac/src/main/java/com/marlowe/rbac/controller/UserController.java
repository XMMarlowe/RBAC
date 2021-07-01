package com.marlowe.rbac.controller;


import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.commons.result.Result;
import com.marlowe.rbac.entity.User;
import com.marlowe.rbac.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @ApiOperation("添加用户")
    @PostMapping("add")
    @RequiresPermissions("user:add")
    public Result addUser(@RequestBody User user) {
        boolean addUser = userService.addUser(user);
        if (addUser) {
            return Result.ok("添加成功");
        } else {
            return Result.ok("添加失败");
        }
    }

    /**
     * 通过id删除用户
     *
     * @param id
     * @return
     */
    @ApiOperation("通过id删除用户")
    @GetMapping("delete/{id}")
    @RequiresPermissions("user:delete")
    public Result deleteUser(@PathVariable Integer id) {
        boolean deleteUserById = userService.deleteUserById(id);
        if (deleteUserById) {
            return Result.ok("添加成功");
        } else {
            return Result.ok("添加失败");
        }
    }

    /**
     * 批量删除用户
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "批量删除用户")
    @PostMapping("deletes")
    @RequiresPermissions("user:delete")
    public Result deleteUsers(@RequestBody List<Integer> ids) {
        boolean deleteUserByIds = userService.deleteUserByIds(ids);
        if (deleteUserByIds) {
            return Result.ok("添加成功");
        } else {
            return Result.ok("添加失败");
        }
    }

    /**
     * 查询所有用户
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation("查询所有用户")
    @GetMapping("all_users/{pageNo}/{pageSize}")
    @RequiresPermissions("user:findAll")
    public Result<List<User>> findAll(@PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<User> pageInfo = userService.findAll(pageNo, pageSize);
        List<User> users = pageInfo.getList();
        // 将用户敏感信息设置为空
        users.forEach(user -> {
            user.setPassword("");
            user.setSalt("");
        });
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
    public Result<User> findUserById(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        // 将用户敏感信息设置为空
        user.setPassword("");
        user.setSalt("");
        return Result.ok(user);
    }

    /**
     * 通过用户名查找用户
     *
     * @param username
     * @return
     */
    @ApiOperation(value = "通过用户名查找用户")
    @GetMapping("detail_name/{username}")
    public Result<User> findUserByUsername(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        // 将用户敏感信息设置为空
        user.setPassword("");
        user.setSalt("");
        return Result.ok(user);
    }

}
