package com.marlowe.rbac.service;

import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.entity.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 页面信息表 服务类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-29
 */
public interface IPageService extends IService<Page> {


    /**
     * 新增页面
     *
     * @param page
     * @return
     */
    boolean addPage(Page page);


    /**
     * 根据id删除页面
     *
     * @param id
     * @return
     */

    boolean deleteById(Integer id);


    /**
     * 根据主键id查询页面
     *
     * @param id
     * @return
     */
    Page findById(Integer id);


    /**
     * 查询所有页面
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<Page> findAll(int pageNo, int pageSize);

    /**
     * 根据主键更新
     *
     * @param page
     * @return
     */
    boolean updatePageById(Page page);


}
