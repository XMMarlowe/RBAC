package com.marlowe.rbac.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author marlowe
 * @since 2021-06-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "User对象", description = "用户信息表")
public class User implements Serializable {

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "最后一次登录时间")
    private LocalDateTime lastLoginTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "定义角色集合")
    private List<Role> roles;


}
