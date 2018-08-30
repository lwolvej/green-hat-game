package com.nuist.service;

import com.nuist.entity.User;

/**
 * 用户服务类
 *
 * @author LwolveJ
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param userName 用户名
     * @param password 用户密码
     * @return 登录成功后的用户实体
     * @throws com.nuist.exception.MyException 抛出异常的情况：用户名错误，或者密码错误
     */
    User login(String userName, String password);

    /**
     * 用户注册
     *
     * @param userName 用户名
     * @param password 密码
     * @return 注册成功后的用户实体
     * @throws com.nuist.exception.MyException 抛出异常的情况：用户名不规范，用户已经存在，密码不规范
     */
    User register(String userName, String password);
}
