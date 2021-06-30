package com.marlowe.rbac.service;

import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.entity.Page;
import com.marlowe.rbac.entity.RoleExclusionGroup;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 互斥角色分组表 服务类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-30
 */
public interface IRoleExclusionGroupService extends IService<RoleExclusionGroup> {

    /**
     * 新增角色互斥组
     *
     * @param roleExclusionGroup
     * @return
     */
    boolean addRoleExclusionGroup(RoleExclusionGroup roleExclusionGroup);


    /**
     * 根据id删除角色互斥组
     *
     * @param id
     * @return
     */

    boolean deleteById(Integer id);


    /**
     * 根据主键id查询角色互斥组
     *
     * @param id
     * @return
     */
    RoleExclusionGroup findById(Integer id);


    /**
     * 查询所有角色互斥组
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<RoleExclusionGroup> findAll(int pageNo, int pageSize);

    /**
     * 根据主键更新角色互斥组
     *
     * @param roleExclusionGroup
     * @return
     */
    boolean updateRoleExclusionGroupById(RoleExclusionGroup roleExclusionGroup);

}
