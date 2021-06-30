package com.marlowe.rbac.service;

import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.entity.Button;
import com.baomidou.mybatisplus.extension.service.IService;
import com.marlowe.rbac.entity.Page;

/**
 * <p>
 * 页面操作按钮信息表 服务类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-29
 */
public interface IButtonService extends IService<Button> {

    /**
     * 新增按钮
     *
     * @param button
     * @return
     */
    boolean addButton(Button button);


    /**
     * 根据id删除按钮
     *
     * @param id
     * @return
     */

    boolean deleteById(Integer id);


    /**
     * 根据主键id查询按钮
     *
     * @param id
     * @return
     */
    Button findById(Integer id);


    /**
     * 查询所有按钮
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<Button> findAll(int pageNo, int pageSize);

    /**
     * 根据主键更新
     *
     * @param button
     * @return
     */
    boolean updateButtonById(Button button);
}
