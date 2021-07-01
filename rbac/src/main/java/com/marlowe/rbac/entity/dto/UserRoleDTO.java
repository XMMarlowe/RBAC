package com.marlowe.rbac.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: RBAC
 * @description: 用户所有角色信息
 * @author: Marlowe
 * @create: 2021-07-01 15:06
 **/
@Data
public class UserRoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

}
