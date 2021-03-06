package com.marlowe.rbac.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: RBAC
 * @description:
 * @author: Marlowe
 * @create: 2021-06-29 11:43
 **/
@Data
public class UserLogin implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;

    private String password;
}