package com.marlowe.rbac.service;

import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.entity.Role;
import com.marlowe.rbac.entity.UserGroup;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 部门信息表 服务类
 * </p>
 *
 * @author marlowe
 * @since 2021-07-01
 */
public interface IUserGroupService extends IService<UserGroup> {

    /**
     * 查询所有角色
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<UserGroup> findAll(int pageNo, int pageSize);

}
