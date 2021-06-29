package com.marlowe.rbac.service.impl;

import com.marlowe.rbac.entity.User;
import com.marlowe.rbac.mapper.UserMapper;
import com.marlowe.rbac.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
