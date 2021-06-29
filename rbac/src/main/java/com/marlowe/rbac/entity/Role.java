package com.marlowe.rbac.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
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
@ApiModel(value="Role对象", description="角色信息表")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色名称，比如管理员")
    private String name;

    @ApiModelProperty(value = "角色代码，比如admin")
    private String code;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "该角色最多使用的人数")
    private Integer maxCount;

    @ApiModelProperty(value = "已使用的人数")
    private Integer useCount;


}
