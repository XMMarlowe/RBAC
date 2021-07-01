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
 * 
 * </p>
 *
 * @author marlowe
 * @since 2021-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Permission对象", description="")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限代码")
    private String code;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;


}
