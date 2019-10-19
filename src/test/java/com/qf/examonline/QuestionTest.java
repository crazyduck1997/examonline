package com.qf.examonline;

import com.qf.examonline.dao.QuestionTypeDao;
import com.qf.examonline.dao.QuestionsPaperDao;
import com.qf.examonline.dao.SelectQuestionsDao;
import com.qf.examonline.entity.BooleanQuestions;
import com.qf.examonline.entity.QuestionVo;
import com.qf.examonline.entity.SelectQuestions;
import com.qf.examonline.entity.SketchQuestions;
import com.qf.examonline.service.BooleanQuestionService;
import com.qf.examonline.service.SelectQuestionsService;
import com.qf.examonline.service.SketchQuestionsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestionTest {

    @Resource
    QuestionsPaperDao questionsPaperDao;

    @Resource
    QuestionTypeDao questionTypeDao;

    @Resource
    SelectQuestionsService selectQuestionsService;

    @Resource
    SketchQuestionsService sketchQuestionsService;

    @Resource
    BooleanQuestionService booleanQuestionService;

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

    @Test
    public void test2(){
        List<QuestionVo> allQuertions = questionTypeDao.findAllQuertions();
        for(QuestionVo questionVo : allQuertions){
            System.out.println(questionVo);
        }
    }

    @Test
    public void test3(){
        BooleanQuestions booleanQuestions = new BooleanQuestions();
        booleanQuestions.setBooId(24);
        booleanQuestions.setBooAnswer("错");
        booleanQuestionService.updateBoolean(booleanQuestions);
        System.out.println(booleanQuestions);
    }
}
