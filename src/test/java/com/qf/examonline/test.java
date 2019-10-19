package com.qf.examonline;

import com.qf.examonline.dao.*;
import com.qf.examonline.entity.SelectQuestions;
import com.qf.examonline.entity.SketchQuestions;
import com.qf.examonline.entity.User;
import com.qf.examonline.utils.ImportExcelUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Resource
    UserDao userDao;


    @Resource
    SelectQuestionsDao selectQuestionsDao;

    @Resource
    BooleanQuestionsDao booleanQuestionsDao;

    @Autowired
    RedisTemplate myRedisTemplate;

    @Resource
    SketchQuestionsDao sketchQuestionsDao;

    @Resource
    UserAnswersDao userAnswersDao;



    @Test
    public void test1(){
        String originUrl = "D:\\XY\\test.xls";

        List<SelectQuestions> linesList;
        try {
            linesList = ImportExcelUtil.importExcel(originUrl,SelectQuestions.class);
            int insert = selectQuestionsDao.insert(linesList);
            System.out.println(insert);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void test2(){
        List<SketchQuestions> list = sketchQuestionsDao.findSketchQuestionsByRandom(1, 3);
        for(SketchQuestions s : list){
            System.out.println(s);
        }
    }


    @Test
    public void test3(){
        Map map =(Map) myRedisTemplate.opsForValue().get("1");
        map.put("uid",1);
        map.put("paperId",1);
        int i = userAnswersDao.insertMap(map);
        System.out.println(i);
    }

    @Test
    public void test4(){
        User userByName = userDao.findUserByName("1");
        System.out.println(userByName);
    }

}
