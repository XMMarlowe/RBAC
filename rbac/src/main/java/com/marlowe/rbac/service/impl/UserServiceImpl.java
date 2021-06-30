package com.marlowe.rbac.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.entity.User;
import com.marlowe.rbac.mapper.UserMapper;
import com.marlowe.rbac.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        // 将密码进行注册时同样的加密

        // 查找用户的盐值
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User tmpUser = userMapper.selectOne(wrapper);
        // 如果用户名存在
        String salt = "";
        if (tmpUser != null) {
            salt = tmpUser.getSalt();
        }
        // 2. 明文密码进行md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(password, salt, 1024);
        password = md5Hash.toHex();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("password", password);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @Override
    public boolean register(User user) {
        // 1.生成随机盐
        String salt = IdUtil.simpleUUID();
        // 2. 将随机盐保存到数据库
        user.setSalt(salt);
        // 3. 明文密码进行md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        return userMapper.insert(user) > 0;
    }

    /**
     * 根据 username 查询用户
     *
     * @param username 用户名
     * @return User
     */
    @Override
    public User findUserByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    /**
     * 查询所有用户
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<User> findAll(int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);

        List<User> users = userMapper.selectList(null);

        PageInfo<User> pageInfo = new PageInfo(users);

        return pageInfo;
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteUserById(int id) {
        return false;
    }

    /**
     * 批量删除用户
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteUserByIds(List<Integer> ids) {
        return false;
    }

    /**
     * 根据主键id查找用户
     *
     * @param id
     * @return
     */
    @Override
    public User findUserById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean addUser(User user) {
        return userMapper.insert(user) > 0;
    }

}
