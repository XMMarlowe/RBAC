package com.marlowe.rbac.service;

import com.github.pagehelper.PageInfo;
import com.marlowe.rbac.entity.Role;
import com.marlowe.rbac.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.marlowe.rbac.entity.dto.UserGroupDTO;
import com.marlowe.rbac.entity.dto.UserRoleDTO;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-26
 */
public interface IUserService extends IService<User> {

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    boolean deleteUserById(Integer id);

    /**
     * 批量删除用户
     *
     * @param ids
     * @return
     */
    boolean deleteUserByIds(List<Integer> ids);


    /**
     * 根据主键id查找用户
     *
     * @param id
     * @return
     */
    User findUserById(Integer id);

    /**
     * 根据 username 查询用户
     *
     * @param username 用户名
     * @return User
     */
    User findUserByUsername(String username);

    /**
     * 通过id查询用户详细信息
     *
     * @param id
     * @return
     */
    User findUserDetailById(Integer id);


    /**
     * 查询所有用户
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<User> findAll(int pageNo, int pageSize);


    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    boolean updateUser(User user);

    /**
     * 通过用户名查询角色信息
     *
     * @param username
     * @return
     */
    User findRolesByUserName(String username);


    /**
     * 通过userId查询用户角色信息
     *
     * @param id
     * @return
     */
    List<UserRoleDTO> findRolesByUserId(Integer id);


    /**
     * 通过userId查询用户部门信息
     *
     * @param id
     * @return
     */
    List<UserGroupDTO> findGroupsByUserId(Integer id);


}
