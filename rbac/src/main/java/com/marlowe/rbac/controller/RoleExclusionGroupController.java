package com.marlowe.rbac.controller;


import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.commons.result.Result;
import com.marlowe.rbac.entity.Role;
import com.marlowe.rbac.entity.RoleExclusionGroup;
import com.marlowe.rbac.service.IRoleExclusionGroupItemService;
import com.marlowe.rbac.service.IRoleExclusionGroupService;
import com.marlowe.rbac.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 互斥角色分组表 前端控制器
 * </p>
 *
 * @author marlowe
 * @since 2021-06-30
 */
@RestController
@RequestMapping("/roleExclusionGroup")
public class RoleExclusionGroupController {

    @Autowired
    private IRoleExclusionGroupService roleExclusionGroupService;

    /**
     * 添加角色互斥组
     *
     * @param roleExclusionGroup
     * @return
     */
    @ApiOperation(value = "添加角色互斥组")
    @PostMapping("add")
    public Result addPage(@RequestBody RoleExclusionGroup roleExclusionGroup) {
        boolean addRoleExclusionGroup = roleExclusionGroupService.addRoleExclusionGroup(roleExclusionGroup);
        if (addRoleExclusionGroup) {
            return Result.ok("添加成功");
        } else {
            return Result.ok("添加失败");
        }
    }

    /**
     * 根据id删除角色互斥组
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id删除角色互斥组")
    @GetMapping("delete/{id}")
    public Result deleteRole(@PathVariable Integer id) {
        boolean deleteById = roleExclusionGroupService.deleteById(id);
        if (deleteById) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }

    /**
     * 根据id查找角色互斥组
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查找角色互斥组")
    @GetMapping("detail/{id}")
    public Result<Role> findButtonById(@PathVariable Integer id) {
        RoleExclusionGroup roleExclusionGroup = roleExclusionGroupService.findById(id);
        return Result.ok(roleExclusionGroup);
    }

    /**
     * 查询所有角色互斥组
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "查询所有角色互斥组")
    @GetMapping("all_role_exclusion_groups/{pageNo}/{pageSize}")
    public Result<List<Role>> allPages(@PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<RoleExclusionGroup> pageInfo = roleExclusionGroupService.findAll(pageNo, pageSize);
        List<RoleExclusionGroup> roleExclusionGroups = pageInfo.getList();
        return Result.ok(roleExclusionGroups);
    }


    /**
     * 根据主键id更新角色互斥组
     *
     * @param roleExclusionGroup
     * @return
     */
    @ApiOperation(value = "根据主键id更新角色互斥组")
    @PostMapping("update")
    public Result updatePage(@RequestBody RoleExclusionGroup roleExclusionGroup) {
        boolean updateRoleExclusionGroupById = roleExclusionGroupService.updateRoleExclusionGroupById(roleExclusionGroup);
        if (updateRoleExclusionGroupById) {
            return Result.ok("更新成功");
        } else {
            return Result.ok("更新失败");
        }

    }

}
