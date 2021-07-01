package com.marlowe.rbac.service.impl;

import com.marlowe.rbac.entity.UserGroup;
import com.marlowe.rbac.mapper.UserGroupMapper;
import com.marlowe.rbac.service.IUserGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
