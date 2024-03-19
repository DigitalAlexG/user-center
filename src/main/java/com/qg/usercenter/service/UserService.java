package com.qg.usercenter.service;

import com.qg.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * User Service
 *
 * @author qiang
 */
public interface UserService extends IService<User> {

    /**
     * User Register
     *
     * @param userAccount User account
     * @param userPassword User password
     * @param checkPassword Check the password
     * @return New user id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * User Login
     *
     * @param userAccount User account
     * @param userPassword User password
     * @return user info
     */
    User doLogin(String userAccount, String userPassword, HttpServletRequest request);

}
