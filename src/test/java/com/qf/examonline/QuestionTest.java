package com.qf.examonline;

import com.qf.examonline.dao.QuestionsPaperDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestionTest {

    @Resource
    QuestionsPaperDao questionsPaperDao;

    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        int i = questionsPaperDao.addQuestions(9, list,3);
        System.out.println(i);
    }


}
