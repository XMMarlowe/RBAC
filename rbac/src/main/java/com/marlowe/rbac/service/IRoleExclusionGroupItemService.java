package com.marlowe.rbac.service;

import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.entity.RoleExclusionGroup;
import com.marlowe.rbac.entity.RoleExclusionGroupItem;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-30
 */
public interface IRoleExclusionGroupItemService extends IService<RoleExclusionGroupItem> {

    /**
     * 添加角色到指定互斥组
     *
     * @param roleExclusionGroupItem
     * @return
     */
    boolean addRoleExclusionGroupItem(RoleExclusionGroupItem roleExclusionGroupItem);

    /**
     * 根据id删除角色互斥组内容
     *
     * @param id
     * @return
     */

    boolean deleteById(Integer id);


    /**
     * 根据id查询角色互斥组内容
     *
     * @param id
     * @return
     */
    RoleExclusionGroupItem findById(Integer id);

    /**
     * 查询所有角色互斥组内容
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<RoleExclusionGroupItem> findAll(int pageNo, int pageSize);

    /**
     * 根据主键更新角色互斥组内容
     *
     * @param roleExclusionGroupItem
     * @return
     */
    boolean updateRoleExclusionGroupItemById(RoleExclusionGroupItem roleExclusionGroupItem);
}
