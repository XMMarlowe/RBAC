package com.marlowe.rbac.controller;


import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.commons.result.Result;
import com.marlowe.rbac.entity.RoleExclusionGroupItem;
import com.marlowe.rbac.service.IRoleExclusionGroupItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author marlowe
 * @since 2021-06-30
 */
@Api(tags = "角色互斥组内容管理控制器")
@RestController
@RequestMapping("/roleExclusionGroupItem")
public class RoleExclusionGroupItemController {
    @Autowired
    private IRoleExclusionGroupItemService roleExclusionGroupItemService;

    /**
     * 添加角色互斥组内容
     *
     * @param roleExclusionGroupItem
     * @return
     */
    @ApiOperation(value = "添加角色互斥组内容")
    @PostMapping("add")
    public Result addPage(@RequestBody RoleExclusionGroupItem roleExclusionGroupItem) {
        boolean addRoleExclusionGroupItem = roleExclusionGroupItemService.addRoleExclusionGroupItem(roleExclusionGroupItem);
        if (addRoleExclusionGroupItem) {
            return Result.ok("添加成功");
        } else {
            return Result.ok("添加失败");
        }
    }

    /**
     * 根据id删除角色互斥组内容
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id删除角色互斥组内容")
    @GetMapping("delete/{id}")
    public Result deleteRole(@PathVariable Integer id) {
        boolean deleteById = roleExclusionGroupItemService.deleteById(id);
        if (deleteById) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }

    /**
     * 根据id查找角色互斥组内容
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查找角色互斥组内容")
    @GetMapping("detail/{id}")
    public Result<RoleExclusionGroupItem> findButtonById(@PathVariable Integer id) {
        RoleExclusionGroupItem roleExclusionGroupItem = roleExclusionGroupItemService.findById(id);
        return Result.ok(roleExclusionGroupItem);
    }

    /**
     * 查询所有角色互斥组内容
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "查询所有角色互斥组内容")
    @GetMapping("all_role_exclusion_groups/{pageNo}/{pageSize}")
    public Result<List<RoleExclusionGroupItem>> allPages(@PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<RoleExclusionGroupItem> pageInfo = roleExclusionGroupItemService.findAll(pageNo, pageSize);
        List<RoleExclusionGroupItem> roleExclusionGroupItems = pageInfo.getList();
        return Result.ok(roleExclusionGroupItems);
    }


    /**
     * 根据主键id更新角色互斥组内容
     *
     * @param roleExclusionGroupItem
     * @return
     */
    @ApiOperation(value = "根据主键id更新角色互斥组内容")
    @PostMapping("update")
    public Result updatePage(@RequestBody RoleExclusionGroupItem roleExclusionGroupItem) {
        boolean updateRoleExclusionGroupItemById = roleExclusionGroupItemService.updateRoleExclusionGroupItemById(roleExclusionGroupItem);
        if (updateRoleExclusionGroupItemById) {
            return Result.ok("更新成功");
        } else {
            return Result.ok("更新失败");
        }

    }

}

