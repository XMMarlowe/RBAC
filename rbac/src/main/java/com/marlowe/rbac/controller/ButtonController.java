package com.marlowe.rbac.controller;


import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.commons.result.Result;
import com.marlowe.rbac.entity.Button;
import com.marlowe.rbac.service.IButtonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 页面操作按钮信息表 前端控制器
 * </p>
 *
 * @author marlowe
 * @since 2021-06-29
 */
@RestController
@RequestMapping("/button")
@Api(tags = "按钮管理控制器")
public class ButtonController {

    @Autowired
    private IButtonService buttonService;


    /**
     * 添加按钮
     *
     * @param button
     * @return
     */
    @ApiOperation(value = "添加按钮")
    @PostMapping("add")
    public Result addButton(@RequestBody Button button) {
        boolean addButton = buttonService.addButton(button);
        if (addButton) {
            return Result.ok("添加成功");
        } else {
            return Result.ok("添加失败");
        }
    }

    /**
     * 根据id删除按钮
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id删除按钮")
    @GetMapping("delete/{id}")
    public Result deleteButton(@PathVariable Integer id) {
        boolean deleteById = buttonService.deleteById(id);
        if (deleteById) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }

    /**
     * 根据id查找按钮
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查找按钮")
    @GetMapping("detail/{id}")
    public Result<Button> findButtonById(@PathVariable Integer id) {
        Button button = buttonService.findById(id);
        return Result.ok(button);
    }

    /**
     * 查询所有按钮
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "查询所有按钮")
    @GetMapping("all_buttons/{pageNo}/{pageSize}")
    public Result<List<Button>> allButtons(@PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<Button> pageInfo = buttonService.findAll(pageNo, pageSize);
        List<Button> buttons = pageInfo.getList();
        return Result.ok(buttons);
    }


    /**
     * 根据主键id更新按钮
     *
     * @param button
     * @return
     */
    @ApiOperation(value = "根据主键id更新按钮")
    @PostMapping("update")
    public Result updateButton(@RequestBody Button button) {
        boolean updateButtonById = buttonService.updateButtonById(button);
        if (updateButtonById) {
            return Result.ok("更新成功");
        } else {
            return Result.ok("更新失败");
        }

    }

}
