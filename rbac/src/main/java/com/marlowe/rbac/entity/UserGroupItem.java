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
 * 用户所在部门表
 * </p>
 *
 * @author marlowe
 * @since 2021-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UserGroupItem对象", description="用户所在部门表")
public class UserGroupItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "用户所在部门id")
    private Integer userGroupId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;


}
