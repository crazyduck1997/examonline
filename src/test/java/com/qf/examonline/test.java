package com.qf.examonline;

import com.qf.examonline.dao.SelectQuestionsDao;
import com.qf.examonline.dao.UserDao;
import com.qf.examonline.entity.SelectQuestions;
import com.qf.examonline.entity.User;
import com.qf.examonline.utils.ImportExcelUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Resource
    UserDao userDao;


    @Resource
    SelectQuestionsDao selectQuestionsDao;
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

}
