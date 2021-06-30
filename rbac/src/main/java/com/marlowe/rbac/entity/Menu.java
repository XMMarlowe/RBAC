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
 * 菜单信息表
 * </p>
 *
 * @author marlowe
 * @since 2021-06-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Menu对象", description="菜单信息表")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "请求路径")
    private String path;

    @ApiModelProperty(value = "0本网页打开1新网页打开")
    private Integer linkType;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "父菜单")
    private Integer parentId;

    @ApiModelProperty(value = "页面id")
    private Integer pageId;


}
