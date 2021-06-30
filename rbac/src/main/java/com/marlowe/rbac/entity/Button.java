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
 * 页面操作按钮信息表
 * </p>
 *
 * @author marlowe
 * @since 2021-06-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Button对象", description="页面操作按钮信息表")
public class Button implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "按钮名称")
    private String name;

    @ApiModelProperty(value = "页面id")
    private Integer pageId;


}
