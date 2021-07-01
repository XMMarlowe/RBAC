package com.marlowe.rbac.entity.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: RBAC
 * @description: 用户详细信息对象
 * @author: Marlowe
 * @create: 2021-07-01 15:51
 **/
@Data
public class UserDTO implements Serializable {


    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "登录名")
    private String username;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "盐")
    private String salt;

    @ApiModelProperty(value = "真实姓名")
    private String name;

    @ApiModelProperty(value = "0未知 1男 2女")
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    private Integer old;

    @ApiModelProperty(value = "用户现有权限")
    private List<UserRoleDTO> userRoleDTOS;

    @ApiModelProperty(value = "用户现有部门")
    private List<UserGroupDTO> userGroupDTOS;
}
