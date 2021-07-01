package com.marlowe.rbac.service;

import com.marlowe.rbac.entity.UserGroupItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.marlowe.rbac.entity.UserRole;

/**
 * <p>
 * 用户所在部门表 服务类
 * </p>
 *
 * @author marlowe
 * @since 2021-07-01
 */
public interface IUserGroupItemService extends IService<UserGroupItem> {

    /**
     * 通过用户id删除该用户所有部门
     *
     * @param id
     * @return
     */
    boolean deleteGroupItemByUserId(Integer id);


    /**
     * 新增用户部门对应关系
     *
     * @param userGroupItem
     * @return
     */
    boolean addUserGroupItem(UserGroupItem userGroupItem);

}
