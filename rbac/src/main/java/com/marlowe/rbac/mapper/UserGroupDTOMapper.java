package com.marlowe.rbac.mapper;

import com.marlowe.rbac.entity.dto.UserGroupDTO;

import java.util.List;

/**
 * @program: RBAC
 * @description:
 * @author: Marlowe
 * @create: 2021-07-01 15:20
 **/
public interface UserGroupDTOMapper {

    /**
     * 通过userId查询用户所在部门
     * @param id
     * @return
     */
    List<UserGroupDTO> findGroupsByUserId(Integer id);
}
