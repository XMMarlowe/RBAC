package com.marlowe.rbac.service;

import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.entity.Permission;
import com.marlowe.rbac.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-26
 */
public interface IRoleService extends IService<Role> {
    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    boolean addRole(Role role);


    /**
     * 根据id删除角色
     *
     * @param id
     * @return
     */

    boolean deleteById(Integer id);


    /**
     * 根据主键id查询角色
     *
     * @param id
     * @return
     */
    Role findById(Integer id);


    /**
     * 查询所有角色
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<Role> findAll(int pageNo, int pageSize);

    /**
     * 根据主键更新角色
     *
     * @param role
     * @return
     */
    boolean updateRoleById(Role role);

    /**
     * 根据角色id查询角色拥有的权限
     *
     * @param id
     * @return
     */
    List<Permission> findPermissionsByRoleId(Integer id);
}
