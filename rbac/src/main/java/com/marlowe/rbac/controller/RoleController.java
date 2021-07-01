package com.marlowe.rbac.controller;


import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.commons.result.Result;
import com.marlowe.rbac.entity.Page;
import com.marlowe.rbac.entity.Role;
import com.marlowe.rbac.service.IPageService;
import com.marlowe.rbac.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author marlowe
 * @since 2021-06-26
 */
@Api(tags = "角色管理控制器")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @ApiOperation(value = "添加角色")
    @PostMapping("add")
    public Result addPage(@RequestBody Role role) {
        boolean addRole = roleService.addRole(role);
        if (addRole) {
            return Result.ok("添加成功");
        } else {
            return Result.ok("添加失败");
        }
    }

    /**
     * 根据id删除角色
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id删除角色")
    @GetMapping("delete/{id}")
    public Result deleteRole(@PathVariable Integer id) {
        boolean deleteById = roleService.deleteById(id);
        if (deleteById) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }

    /**
     * 根据id查找角色
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查找角色")
    @GetMapping("detail/{id}")
    public Result<Role> findButtonById(@PathVariable Integer id) {
        Role role = roleService.findById(id);
        return Result.ok(role);
    }

    /**
     * 查询所有角色
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "查询所有角色")
    @GetMapping("all_roles/{pageNo}/{pageSize}")
    public Result<List<Role>> allPages(@PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<Role> pageInfo = roleService.findAll(pageNo, pageSize);
        List<Role> roles = pageInfo.getList();
        return Result.ok(roles);
    }


    /**
     * 根据主键id更新角色
     *
     * @param role
     * @return
     */
    @ApiOperation(value = "根据主键id更新角色")
    @PostMapping("update")
    public Result updatePage(@RequestBody Role role) {
        boolean updateRoleById = roleService.updateRoleById(role);
        if (updateRoleById) {
            return Result.ok("更新成功");
        } else {
            return Result.ok("更新失败");
        }

    }
}
