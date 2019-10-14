package com.qf.examonline;

import com.qf.examonline.dao.StudentDao;
import com.qf.examonline.entity.Student;
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
    StudentDao studentDao;


    @Test
    public void test1(){
        List<Student> list = studentDao.selectAll();
        for(Student s : list){
            System.out.println(s);
        }
    }

}
