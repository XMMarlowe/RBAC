package com.marlowe.rbac.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 互斥角色分组表
 * </p>
 *
 * @author marlowe
 * @since 2021-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="RoleExclusionGroup对象", description="互斥角色分组表")
public class RoleExclusionGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "分组名称")
    private String name;

    @ApiModelProperty(value = "描述互斥的规则")
    private String description;


}
