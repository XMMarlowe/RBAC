package com.marlowe.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.entity.Page;
import com.marlowe.rbac.entity.Permission;
import com.marlowe.rbac.entity.Role;
import com.marlowe.rbac.mapper.RoleMapper;
import com.marlowe.rbac.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {


    @Autowired
    private RoleMapper roleMapper;

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @Override
    public boolean addRole(Role role) {
        return roleMapper.insert(role) > 0;
    }

    /**
     * 根据id删除角色
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Integer id) {
        return roleMapper.deleteById(id) > 0;
    }

    /**
     * 根据主键id查询角色
     *
     * @param id
     * @return
     */
    @Override
    public Role findById(Integer id) {
        return roleMapper.selectById(id);
    }

    /**
     * 查询所有角色
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Role> findAll(int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);
        List<Role> roles = roleMapper.selectList(null);
        PageInfo<Role> pageInfo = new PageInfo(roles);
        return pageInfo;
    }

    /**
     * 根据主键更新角色
     *
     * @param role
     * @return
     */
    @Override
    public boolean updateRoleById(Role role) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", role.getId());
        return roleMapper.update(role, queryWrapper) > 0;
    }

    /**
     * 根据角色id查询角色拥有的权限
     *
     * @param id
     * @return
     */
    @Override
    public List<Permission> findPermissionsByRoleId(Integer id) {
        return roleMapper.findPermissionsByRoleId(id);
    }
}
