package com.marlowe.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marlowe.rbac.entity.UserRole;
import com.marlowe.rbac.mapper.UserRoleMapper;
import com.marlowe.rbac.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-07-01
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 通过用户id删除该用户所有角色
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteRoleByUserId(Integer id) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", id);
        return userRoleMapper.delete(queryWrapper) > 0;
    }

    /**
     * 新增用户角色对应关系
     *
     * @param userRole
     * @return
     */
    @Override
    public boolean addUserRole(UserRole userRole) {
        return userRoleMapper.insert(userRole) > 0;
    }
}
