package com.marlowe.rbac.mapper;

import com.marlowe.rbac.entity.dto.UserRoleDTO;

import java.util.List;

/**
 * @program: RBAC
 * @description:
 * @author: Marlowe
 * @create: 2021-07-01 15:12
 **/
public interface UserRoleDTOMapper {

    /**
     * 通过userId查询用户角色信息
     *
     * @param id
     * @return
     */
    List<UserRoleDTO> findRolesByUserId(Integer id);


}
