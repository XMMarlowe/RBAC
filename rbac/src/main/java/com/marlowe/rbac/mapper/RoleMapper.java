package com.marlowe.rbac.mapper;

import com.marlowe.rbac.entity.Permission;
import com.marlowe.rbac.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author marlowe
 * @since 2021-06-26
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过角色id查找权限信息
     *
     * @param id
     * @return
     */
    List<Permission> findPermissionsByRoleId(Integer id);

}
