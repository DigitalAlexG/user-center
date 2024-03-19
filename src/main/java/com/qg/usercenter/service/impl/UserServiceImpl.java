package com.qg.usercenter.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qg.usercenter.model.domain.User;
import com.qg.usercenter.service.UserService;
import com.qg.usercenter.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Implement the user service
 *
 * @author qiang
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;

    /**
     * SALT value
     */
    private static final String SALT = "Gian";

    /**
     * User Login State Key
     */
    private static final String USER_LOGIN_STATE = "userLoginState";

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {

        // 1. Verification
        // Account and password can not be empty and null.
        if(StringUtils.isAnyBlank(userAccount, userPassword,checkPassword)){
            return -1;
        }

        // The length of account can not be less than 4.
        if(userAccount.length() < 4){
            return  -1;
        }

        // The length of password can not be less than 8.
        if(userPassword.length() < 8 || checkPassword.length() < 8){
            return  -1;
        }

        // Account validity verification
        String regExp = "^[^\\d][\\w_]";
        // String validPattern = "^[A-Za-z][\\w]{4,10}$";
        Matcher matcher = Pattern.compile(regExp).matcher(userAccount);
        if(!matcher.find()){
            return -1;
        }

        // Password verification
        if(!userPassword.equals(checkPassword)){
            return -1;
        }

        // Account repeated verification
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        Long count = userMapper.selectCount(queryWrapper);
        if(count > 0){
            return -1;
        }

        // 2. Encryption
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        // 3. Insert the data
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean result = this.save(user);
        if(!result){
            return -1;
        }

        return user.getId() ;
    }


    @Override
    public User doLogin(String userAccount, String userPassword, HttpServletRequest request) {

        // 1. Verification
        // Account and password can not be empty and null.
        if(StringUtils.isAnyBlank(userAccount, userPassword)){
            return null;
        }

        // The length of account can not be less than 4.
        if(userAccount.length() < 4){
            return  null;
        }

        // The length of password can not be less than 8.
        if(userPassword.length() < 8){
            return  null;
        }

        // Account validity verification
        String regExp = "^[^\\d][\\w_]";
        // String validPattern = "^[A-Za-z][\\w]{4,10}$";
        Matcher matcher = Pattern.compile(regExp).matcher(userAccount);
        if(!matcher.find()){
            return null;
        }

        // 2. Encryption
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        queryWrapper.eq("user_password", encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            log.info("User login failed, userAccount can not match userPassword");
            return null;
        }

        // 3. Desensitization for user
        User saftyUser = new User();
        saftyUser.setId(user.getId());
        saftyUser.setUserName(user.getUserName());
        saftyUser.setUserAccount(user.getUserAccount());
        saftyUser.setAvatarUrl(user.getAvatarUrl());
        saftyUser.setGender(user.getGender());
        saftyUser.setPhone(user.getPhone());
        saftyUser.setEmail(user.getEmail());
        saftyUser.setUserStatus(user.getUserStatus());
        saftyUser.setCreateTime(user.getCreateTime());

        // 4.Record the login state
        request.getSession().setAttribute(USER_LOGIN_STATE,saftyUser);

        return saftyUser;
    }
}




