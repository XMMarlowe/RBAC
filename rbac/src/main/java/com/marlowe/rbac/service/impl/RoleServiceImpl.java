package com.marlowe.rbac.service.impl;

import com.marlowe.rbac.entity.Role;
import com.marlowe.rbac.mapper.RoleMapper;
import com.marlowe.rbac.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
