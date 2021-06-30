package com.marlowe.rbac.controller;


import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.commons.result.Result;
import com.marlowe.rbac.entity.Page;
import com.marlowe.rbac.service.IPageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 页面信息表 前端控制器
 * </p>
 *
 * @author marlowe
 * @since 2021-06-29
 */
@Api(tags = "页面管理控制器")
@RestController
@RequestMapping("/page")
public class PageController {

    @Autowired
    private IPageService pageService;

    /**
     * 添加页面
     *
     * @param page
     * @return
     */
    @ApiOperation(value = "添加页面")
    @PostMapping("add")
    public Result addPage(@RequestBody Page page) {
        boolean addPage = pageService.addPage(page);
        if (addPage) {
            return Result.ok("添加成功");
        } else {
            return Result.ok("添加失败");
        }
    }

    /**
     * 根据id删除页面
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id删除页面")
    @GetMapping("delete/{id}")
    public Result deletePage(@PathVariable Integer id) {
        boolean deleteById = pageService.deleteById(id);
        if (deleteById) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }

    /**
     * 根据id查找页面
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查找页面")
    @GetMapping("detail/{id}")
    public Result<Page> findButtonById(@PathVariable Integer id) {
        Page page = pageService.findById(id);
        return Result.ok(page);
    }

    /**
     * 查询所有页面
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "查询所有页面")
    @GetMapping("all_pages/{pageNo}/{pageSize}")
    public Result<List<Page>> allPages(@PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<Page> pageInfo = pageService.findAll(pageNo, pageSize);
        List<Page> pages = pageInfo.getList();
        return Result.ok(pages);
    }


    /**
     * 根据主键id更新页面
     *
     * @param page
     * @return
     */
    @ApiOperation(value = "根据主键id更新页面")
    @PostMapping("update")
    public Result updatePage(@RequestBody Page page) {
        boolean updateMenuById = pageService.updatePageById(page);
        if (updateMenuById) {
            return Result.ok("更新成功");
        } else {
            return Result.ok("更新失败");
        }

    }

}
