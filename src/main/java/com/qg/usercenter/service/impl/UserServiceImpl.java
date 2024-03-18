package com.qg.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qg.usercenter.model.domain.User;
import com.qg.usercenter.service.UserService;
import com.qg.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author qiang
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-03-16 01:43:14
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




