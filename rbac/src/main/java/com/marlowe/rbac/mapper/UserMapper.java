package com.marlowe.rbac.mapper;

import com.marlowe.rbac.entity.Role;
import com.marlowe.rbac.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author marlowe
 * @since 2021-06-26
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过用户名查找用户
     *
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * 通过用户名和密码查找用户
     *
     * @param username
     * @param password
     * @return
     */
    User findByUserNameAndPassword(String username, String password);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> findAll();

    /**
     * 通过id查找用户
     *
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 通过用户名获得用户拥有的所有角色
     * @param username
     * @return
     */
    User findRolesByUserName(String username);

    /**
     * 通过id查询用户详细信息
     * @param id
     * @return
     */
    User findUserDetailById(Integer id);

}
