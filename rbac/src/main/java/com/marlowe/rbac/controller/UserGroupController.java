package com.marlowe.rbac.controller;


import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.commons.result.Result;
import com.marlowe.rbac.entity.Role;
import com.marlowe.rbac.entity.UserGroup;
import com.marlowe.rbac.service.IUserGroupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 部门信息表 前端控制器
 * </p>
 *
 * @author marlowe
 * @since 2021-07-01
 */
@RestController
@RequestMapping("/userGroup")
public class UserGroupController {

    @Autowired
    private IUserGroupService userGroupService;


    /**
     * 查询所有部门
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "查询所有角色")
    @GetMapping("all_groups/{pageNo}/{pageSize}")
    public Result<List<Role>> allPages(@PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<UserGroup> pageInfo = userGroupService.findAll(pageNo, pageSize);
        List<UserGroup> groups = pageInfo.getList();
        return Result.ok(groups);
    }
}
