package com.marlowe.rbac.service;

import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.marlowe.rbac.entity.Page;

/**
 * <p>
 * 菜单信息表 服务类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-29
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 新增菜单
     *
     * @param menu
     * @return
     */
    boolean addMenu(Menu menu);


    /**
     * 根据id删除菜单
     *
     * @param id
     * @return
     */

    boolean deleteById(Integer id);


    /**
     * 根据主键id查询菜单
     *
     * @param id
     * @return
     */
    Menu findById(Integer id);


    /**
     * 查询所有菜单
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<Menu> findAll(int pageNo, int pageSize);

    /**
     * 根据主键更新
     *
     * @param menu
     * @return
     */
    boolean updateMenuById(Menu menu);

}
