package com.marlowe.rbac.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: RBAC
 * @description:
 * @author: Marlowe
 * @create: 2021-07-01 15:19
 **/
@Data
public class UserGroupDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 部门id
     */
    private Integer groupId;

    /**
     * 部门名称
     */
    private String groupName;
}
