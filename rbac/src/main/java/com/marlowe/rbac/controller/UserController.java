package com.marlowe.rbac.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.commons.result.Result;
import com.marlowe.rbac.entity.User;
import com.marlowe.rbac.entity.UserGroupItem;
import com.marlowe.rbac.entity.UserRole;
import com.marlowe.rbac.entity.dto.UserDTO;
import com.marlowe.rbac.entity.dto.UserGroupDTO;
import com.marlowe.rbac.entity.dto.UserRoleDTO;
import com.marlowe.rbac.service.IUserGroupItemService;
import com.marlowe.rbac.service.IUserRoleService;
import com.marlowe.rbac.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IUserGroupItemService userGroupItemService;

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
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
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


    /**
     * 通过用户id编辑用户信息，需要查询出用户所有权限，用户所在部门
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "通过用户id编辑用户信息，需要查询出用户所有权限，用户所在部门")
    @GetMapping("detail_all/{id}")
    public Result<JSONObject> editUserMsg(@PathVariable Integer id) {

        JSONObject jsonObject = new JSONObject();

        // 通过id 查询出用户基本信息
        User user = userService.findUserById(id);
        user.setPassword("");
        user.setSalt("");
        jsonObject.put("user", user);


        // 通过userId查询用户角色信息
        List<UserRoleDTO> roles = userService.findRolesByUserId(id);
        jsonObject.put("roles", roles);

        // 通过userId查询用户所在部门信息
        List<UserGroupDTO> groups = userService.findGroupsByUserId(id);
        jsonObject.put("groups", groups);
        return Result.ok(jsonObject);

    }

    @ApiOperation(value = "更新用户信息")
    @PostMapping("update")
    public Result updateUserMsg(@RequestBody UserDTO userDTO) {

        // 获取用户id
        Integer id = userDTO.getId();

        // 更新用户基本信息
        User user = new User();
        user.setId(userDTO.getId())
                .setUsername(userDTO.getUsername())
                .setName(userDTO.getName())
                .setSex(userDTO.getSex())
                .setOld(userDTO.getOld());
        userService.updateUser(user);

        // 通过id 查询用户现有角色，并清空该用户现有角色
        boolean deleteRoleByUserId = userRoleService.deleteRoleByUserId(id);

        // 获取前端传过来的用户新的角色列表，并插入中间表
        List<UserRoleDTO> userRoleDTOS = userDTO.getUserRoleDTOS();


        for (UserRoleDTO userRoleDTO : userRoleDTOS) {
            UserRole userRole = new UserRole();
            userRole.setUserId(id);
            userRole.setRoleId(userRoleDTO.getRoleId());
            userRoleService.addUserRole(userRole);
        }

        // 通过id 查询用户现有部门，并清空该用户现有部门
        boolean deleteGroupItemByUserId = userGroupItemService.deleteGroupItemByUserId(id);

        // 获取前端传过来的用户新的部门列表，并插入中间表
        List<UserGroupDTO> userGroupDTOS = userDTO.getUserGroupDTOS();

        for (UserGroupDTO userGroupDTO : userGroupDTOS) {
            UserGroupItem userGroupItem = new UserGroupItem();
            userGroupItem.setUserId(id);
            userGroupItem.setUserGroupId(userGroupDTO.getGroupId());
            userGroupItemService.addUserGroupItem(userGroupItem);
        }

        return Result.ok("更新成功");
    }


}
