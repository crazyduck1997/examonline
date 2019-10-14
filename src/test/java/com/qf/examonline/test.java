package com.qf.examonline;

import com.qf.examonline.dao.UserDao;
import com.qf.examonline.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Resource
    UserDao userDao;


    @Test
    public void test1(){
        List<User> list = userDao.selectAll();
        for(User s : list){
            System.out.println(s);
        }
    }

}
