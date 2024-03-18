package com.qg.usercenter.service;

import com.qg.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * Test the user service
 *
 * @author qg
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAddUser(){

        User user = new User();
        user.setUserName("123");
        user.setUserAccount("222");
        user.setAvatarUrl("https://i.pinimg.com/564x/f0/a4/17/f0a4170e43fae6a84ff990b6df105199.jpg");
        user.setGender(0);
        user.setUserPassword("qqq");
        user.setPhone("222");
        user.setEmail("343");
        user.setUserStatus(0);


        boolean result = userService.save(user);

        System.out.println(user.getId());

        Assertions.assertTrue(result);



    }

}