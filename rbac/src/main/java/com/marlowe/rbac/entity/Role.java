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
 * 角色信息表
 * </p>
 *
 * @author marlowe
 * @since 2021-06-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Role对象", description = "角色信息表")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色名称，比如管理员")
    private String name;

    @ApiModelProperty(value = "角色代码，比如admin")
    private String code;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "该角色最多使用的人数")
    private Integer maxCount;

    @ApiModelProperty(value = "已使用的人数")
    private Integer useCount;

    @TableField(exist = false)
    @ApiModelProperty(value = "权限集合")
    private List<Permission> permissions;


}
