package com.marlowe.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.entity.RoleExclusionGroup;
import com.marlowe.rbac.entity.RoleExclusionGroupItem;
import com.marlowe.rbac.mapper.RoleExclusionGroupItemMapper;
import com.marlowe.rbac.service.IRoleExclusionGroupItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-30
 */
@Service
public class RoleExclusionGroupItemServiceImpl extends ServiceImpl<RoleExclusionGroupItemMapper, RoleExclusionGroupItem> implements IRoleExclusionGroupItemService {

    @Autowired
    private RoleExclusionGroupItemMapper roleExclusionGroupItemMapper;

    /**
     * 添加角色到指定互斥组
     *
     * @param roleExclusionGroupItem
     * @return
     */
    @Override
    public boolean addRoleExclusionGroupItem(RoleExclusionGroupItem roleExclusionGroupItem) {
        return roleExclusionGroupItemMapper.insert(roleExclusionGroupItem) > 0;
    }

    /**
     * 根据id删除角色互斥组内容
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Integer id) {
        return roleExclusionGroupItemMapper.deleteById(id) > 0;
    }

    /**
     * 根据id查询角色互斥组内容
     *
     * @param id
     * @return
     */
    @Override
    public RoleExclusionGroupItem findById(Integer id) {
        return roleExclusionGroupItemMapper.selectById(id);
    }

    /**
     * 查询所有角色互斥组内容
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<RoleExclusionGroupItem> findAll(int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);
        List<RoleExclusionGroupItem> roleExclusionGroupItems = roleExclusionGroupItemMapper.selectList(null);
        PageInfo<RoleExclusionGroupItem> pageInfo = new PageInfo(roleExclusionGroupItems);
        return pageInfo;
    }

    /**
     * 根据主键更新角色互斥组内容
     *
     * @param roleExclusionGroupItem
     * @return
     */
    @Override
    public boolean updateRoleExclusionGroupItemById(RoleExclusionGroupItem roleExclusionGroupItem) {
        QueryWrapper<RoleExclusionGroupItem> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", roleExclusionGroupItem.getId());
        return roleExclusionGroupItemMapper.update(roleExclusionGroupItem, queryWrapper) > 0;
    }
}
