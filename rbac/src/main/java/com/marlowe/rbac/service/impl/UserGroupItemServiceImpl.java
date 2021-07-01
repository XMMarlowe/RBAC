package com.marlowe.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marlowe.rbac.entity.UserGroupItem;
import com.marlowe.rbac.entity.UserRole;
import com.marlowe.rbac.mapper.UserGroupItemMapper;
import com.marlowe.rbac.service.IUserGroupItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户所在部门表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-07-01
 */
@Service
public class UserGroupItemServiceImpl extends ServiceImpl<UserGroupItemMapper, UserGroupItem> implements IUserGroupItemService {

    @Autowired
    private UserGroupItemMapper userGroupItemMapper;

    /**
     * 通过用户id删除该用户所有部门
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteGroupItemByUserId(Integer id) {
        QueryWrapper<UserGroupItem> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", id);
        return userGroupItemMapper.delete(queryWrapper) > 0;
    }

    /**
     * 新增用户部门对应关系
     *
     * @param userGroupItem
     * @return
     */
    @Override
    public boolean addUserGroupItem(UserGroupItem userGroupItem) {
        return false;
    }
}
