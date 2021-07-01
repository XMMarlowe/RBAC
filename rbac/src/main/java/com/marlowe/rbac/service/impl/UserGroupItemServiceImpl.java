package com.marlowe.rbac.service.impl;

import com.marlowe.rbac.entity.UserGroupItem;
import com.marlowe.rbac.mapper.UserGroupItemMapper;
import com.marlowe.rbac.service.IUserGroupItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
