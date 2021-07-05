package com.marlowe.rbac.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.entity.Role;
import com.marlowe.rbac.entity.UserGroup;
import com.marlowe.rbac.mapper.UserGroupMapper;
import com.marlowe.rbac.service.IUserGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 部门信息表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-07-01
 */
@Service
public class UserGroupServiceImpl extends ServiceImpl<UserGroupMapper, UserGroup> implements IUserGroupService {
    @Autowired
    private UserGroupMapper userGroupMapper;

    /**
     * 查询所有角色
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<UserGroup> findAll(int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);
        List<UserGroup> userGroups = userGroupMapper.selectList(null);
        PageInfo<UserGroup> pageInfo = new PageInfo(userGroups);
        return pageInfo;
    }
}
