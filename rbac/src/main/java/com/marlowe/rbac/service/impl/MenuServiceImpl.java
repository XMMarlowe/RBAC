package com.marlowe.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.entity.Menu;
import com.marlowe.rbac.entity.Page;
import com.marlowe.rbac.mapper.MenuMapper;
import com.marlowe.rbac.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单信息表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-29
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 新增菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean addMenu(Menu menu) {
        return menuMapper.insert(menu) > 0;
    }

    /**
     * 根据id删除菜单
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Integer id) {
        return menuMapper.deleteById(id) > 0;
    }

    /**
     * 根据主键id查询菜单
     *
     * @param id
     * @return
     */
    @Override
    public Menu findById(Integer id) {
        return menuMapper.selectById(id);
    }

    /**
     * 查询所有菜单
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Menu> findAll(int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);

        List<Menu> menus = menuMapper.selectList(null);

        PageInfo<Menu> pageInfo = new PageInfo(menus);

        return pageInfo;
    }

    /**
     * 根据主键更新
     *
     * @param menu
     * @return
     */
    @Override
    public boolean updateMenuById(Menu menu) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", menu.getId());
        return menuMapper.update(menu, queryWrapper) > 0;
    }
}
