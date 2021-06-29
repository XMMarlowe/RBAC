package com.marlowe.rbac.entity.dto;

import lombok.Data;

/**
 * @program: RBAC
 * @description:
 * @author: Marlowe
 * @create: 2021-06-29 11:43
 **/
@Data
public class UserLogin {
    private String username;

    private String password;
}