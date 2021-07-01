package com.marlowe.rbac.service;

import com.marlowe.rbac.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户角色关系表 服务类
 * </p>
 *
 * @author marlowe
 * @since 2021-07-01
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 通过用户id删除该用户所有角色
     *
     * @param id
     * @return
     */
    boolean deleteRoleByUserId(Integer id);


    /**
     * 新增用户角色对应关系
     *
     * @param userRole
     * @return
     */
    boolean addUserRole(UserRole userRole);

}
