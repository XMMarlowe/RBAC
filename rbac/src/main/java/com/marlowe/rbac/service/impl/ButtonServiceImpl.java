package com.marlowe.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.entity.Button;
import com.marlowe.rbac.entity.Page;
import com.marlowe.rbac.mapper.ButtonMapper;
import com.marlowe.rbac.service.IButtonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 页面操作按钮信息表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-29
 */
@Service
public class ButtonServiceImpl extends ServiceImpl<ButtonMapper, Button> implements IButtonService {


    @Autowired
    private ButtonMapper buttonMapper;

    /**
     * 新增按钮
     *
     * @param button
     * @return
     */
    @Override
    public boolean addButton(Button button) {
        return buttonMapper.insert(button) > 0;
    }

    /**
     * 根据id删除按钮
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Integer id) {
        return buttonMapper.deleteById(id) > 0;
    }

    /**
     * 根据主键id查询按钮
     *
     * @param id
     * @return
     */
    @Override
    public Button findById(Integer id) {
        return buttonMapper.selectById(id);
    }

    /**
     * 查询所有按钮
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Button> findAll(int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);

        List<Button> buttons = buttonMapper.selectList(null);

        PageInfo<Button> pageInfo = new PageInfo(buttons);

        return pageInfo;
    }

    /**
     * 根据主键更新
     *
     * @param button
     * @return
     */
    @Override
    public boolean updateButtonById(Button button) {
        QueryWrapper<Button> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", button.getId());
        return buttonMapper.update(button, queryWrapper) > 0;
    }
}
