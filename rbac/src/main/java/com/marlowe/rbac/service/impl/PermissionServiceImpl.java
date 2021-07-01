package com.marlowe.rbac.service.impl;

import com.marlowe.rbac.entity.Permission;
import com.marlowe.rbac.mapper.PermissionMapper;
import com.marlowe.rbac.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-07-01
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
