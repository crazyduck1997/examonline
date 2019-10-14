package com.qf.examonline.login;

import com.qf.examonline.entity.User;
import com.qf.examonline.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginTest {
    @Autowired
    LoginService loginService;
    @Test
    public void login(){
        User user = loginService.login("zhangsan");
        System.out.println(user);
    }
    @Test
    public void register(){
//        User user = new User();
//        user.setUsername("wangwu");
//        user.setPassword("123");
        int result = loginService.register("wangwu", "123");
        System.out.println(result);
    }
}
