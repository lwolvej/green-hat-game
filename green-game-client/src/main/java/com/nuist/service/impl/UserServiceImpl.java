package com.nuist.service.impl;

import com.nuist.entity.User;
import com.nuist.exception.MyException;
import com.nuist.service.UserService;
import com.nuist.util.HttpUtil;
import com.nuist.util.JsonUtil;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * 用户服务实现
 *
 * @author LwolveJ
 */
public class UserServiceImpl implements UserService {

    private OkHttpClient client = HttpUtil.getCLIENT();

    private static final String GET_USER = "http://47.92.195.140:13152/api/user/";

    private static final String REGISTER_USER = "http://47.92.195.140:13152/api/user";

    @Override
    public User login(String userName, String password) {
        Response response = HttpUtil.doGet(GET_USER + userName, client);
        if (response.body() != null) {
            String json;
            try {
                json = response.body().string();
            } catch (Exception e) {
                throw new MyException("Exception happen when do get request");
            }
            User user = JsonUtil.getLoginInfoFromJson(json);
            if (user == null) {
                throw new MyException("用户名错误");
            } else {
                if (password.equals(user.getPassword())) {
                    return user;
                } else {
                    throw new MyException("密码错误");
                }
            }
        } else {
            throw new MyException("No source found!");
        }
    }

    @Override
    public User register(String userName, String password) {
        Response response = HttpUtil.doPost1(REGISTER_USER, userName, password, client);
        if (response.body() != null) {
            String json;
            try {
                json = response.body().string();
            } catch (Exception e) {
                throw new MyException("Exception happen when do register action!");
            }
            User user = JsonUtil.getUserInfoFromJson(json);
            if (user == null) {
                throw new MyException("用户已存在");
            } else {
                return user;
            }
        } else {
            throw new MyException("No source found!");
        }
    }
}
