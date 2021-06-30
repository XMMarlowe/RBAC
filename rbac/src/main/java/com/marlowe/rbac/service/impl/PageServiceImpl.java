package com.marlowe.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.entity.Page;
import com.marlowe.rbac.mapper.PageMapper;
import com.marlowe.rbac.service.IPageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 页面信息表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-29
 */
@Service
public class PageServiceImpl extends ServiceImpl<PageMapper, Page> implements IPageService {

    @Autowired
    private PageMapper pageMapper;

    /**
     * 新增页面
     *
     * @param page
     * @return
     */
    @Override
    public boolean addPage(Page page) {
        return pageMapper.insert(page) > 0;
    }

    /**
     * 根据id删除页面
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Integer id) {
        return pageMapper.deleteById(id) > 0;
    }

    /**
     * 根据主键id查询页面
     *
     * @param id
     * @return
     */
    @Override
    public Page findById(Integer id) {
        return pageMapper.selectById(id);
    }

    /**
     * 查询所有页面
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Page> findAll(int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);

        List<Page> pages = pageMapper.selectList(null);

        PageInfo<Page> pageInfo = new PageInfo(pages);

        return pageInfo;
    }

    /**
     * 根据主键更新
     *
     * @param page
     * @return
     */
    @Override
    public boolean updatePageById(Page page) {
        QueryWrapper<Page> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", page.getId());
        return pageMapper.update(page, queryWrapper) > 0;
    }


}
