package com.marlowe.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.entity.Role;
import com.marlowe.rbac.entity.RoleExclusionGroup;
import com.marlowe.rbac.mapper.RoleExclusionGroupMapper;
import com.marlowe.rbac.service.IRoleExclusionGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 互斥角色分组表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-30
 */
@Service
public class RoleExclusionGroupServiceImpl extends ServiceImpl<RoleExclusionGroupMapper, RoleExclusionGroup> implements IRoleExclusionGroupService {

    @Autowired
    private RoleExclusionGroupMapper roleExclusionGroupMapper;

    /**
     * 新增角色互斥组
     *
     * @param roleExclusionGroup
     * @return
     */
    @Override
    public boolean addRoleExclusionGroup(RoleExclusionGroup roleExclusionGroup) {
        return roleExclusionGroupMapper.insert(roleExclusionGroup) > 0;
    }

    /**
     * 根据id删除角色互斥组
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Integer id) {
        return roleExclusionGroupMapper.deleteById(id) > 0;
    }

    /**
     * 根据主键id查询角色互斥组
     *
     * @param id
     * @return
     */
    @Override
    public RoleExclusionGroup findById(Integer id) {
        return roleExclusionGroupMapper.selectById(id);
    }

    /**
     * 查询所有角色互斥组
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<RoleExclusionGroup> findAll(int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);
        List<RoleExclusionGroup> roleExclusionGroups = roleExclusionGroupMapper.selectList(null);
        PageInfo<RoleExclusionGroup> pageInfo = new PageInfo(roleExclusionGroups);
        return pageInfo;
    }

    /**
     * 根据主键更新角色互斥组
     *
     * @param roleExclusionGroup
     * @return
     */
    @Override
    public boolean updateRoleExclusionGroupById(RoleExclusionGroup roleExclusionGroup) {
        QueryWrapper<RoleExclusionGroup> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", roleExclusionGroup.getId());
        return roleExclusionGroupMapper.update(roleExclusionGroup, queryWrapper) > 0;
    }
}
