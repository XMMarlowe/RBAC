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
 * 部门信息表
 * </p>
 *
 * @author marlowe
 * @since 2021-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UserGroup对象", description="部门信息表")
public class UserGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "分组名称")
    private String name;

    @ApiModelProperty(value = "父ID")
    private Integer parentId;

    @ApiModelProperty(value = "分组描述")
    private String description;


}
