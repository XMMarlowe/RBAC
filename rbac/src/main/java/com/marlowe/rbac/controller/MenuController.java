package com.marlowe.rbac.controller;


import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.commons.result.Result;
import com.marlowe.rbac.entity.Menu;
import com.marlowe.rbac.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单信息表 前端控制器
 * </p>
 *
 * @author marlowe
 * @since 2021-06-29
 */
@Api(tags = "菜单管理控制器")
@RestController
@RequestMapping("/menu")
public class MenuController {


    @Autowired
    private IMenuService menuService;

    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    @ApiOperation(value = "添加菜单")
    @PostMapping("add")
    public Result addMenu(@RequestBody Menu menu) {
        boolean addMenu = menuService.addMenu(menu);
        if (addMenu) {
            return Result.ok("添加成功");
        } else {
            return Result.ok("添加失败");
        }
    }

    /**
     * 根据id删除菜单
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id删除菜单")
    @GetMapping("delete/{id}")
    public Result deleteMenu(@PathVariable Integer id) {
        boolean deleteById = menuService.deleteById(id);
        if (deleteById) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }

    /**
     * 根据id查找菜单
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查找菜单")
    @GetMapping("detail/{id}")
    public Result<Menu> findButtonById(@PathVariable Integer id) {
        Menu menu = menuService.findById(id);
        return Result.ok(menu);
    }

    /**
     * 查询所有菜单
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "查询所有菜单")
    @GetMapping("all_menus/{pageNo}/{pageSize}")
    public Result<List<Menu>> allMenus(@PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<Menu> pageInfo = menuService.findAll(pageNo, pageSize);
        List<Menu> menus = pageInfo.getList();
        return Result.ok(menus);
    }


    /**
     * 根据主键id更新菜单
     *
     * @param menu
     * @return
     */
    @ApiOperation(value = "根据主键id更新按钮")
    @PostMapping("update")
    public Result updateMenu(@RequestBody Menu menu) {
        boolean updateMenuById = menuService.updateMenuById(menu);
        if (updateMenuById) {
            return Result.ok("更新成功");
        } else {
            return Result.ok("更新失败");
        }

    }

}
