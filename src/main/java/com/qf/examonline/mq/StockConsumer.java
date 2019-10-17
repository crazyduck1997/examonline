package com.qf.examonline.mq;

import com.qf.examonline.dao.*;
import com.qf.examonline.entity.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RabbitListener(queues = "queue.commit")
public class StockConsumer {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    PaperDao paperDao;

    @Autowired
    QuestionsPaperDao questionsPaperDao;

    @Autowired
    SelectQuestionsDao selectQuestionsDao;

    @Autowired
    BooleanQuestionsDao booleanQuestionsDao;

    @Autowired
    SketchQuestionsDao sketchQuestionsDao;

    @Autowired
    ScoreDao scoreDao;


    @RabbitHandler
    public void receiver(List<UserAnswers> list) {
        UserAnswers userAnswers = list.get(0);
        //初始化分数
        Integer sum = 0;
        //试卷id
        Integer paperId = userAnswers.getPaperId();
        //学生id
        Integer uid = userAnswers.getUid();
        //循环获取提交的答案
        for(int i = 0;i<list.size();i++){
            UserAnswers answers = list.get(i);
            Integer questionId = answers.getCQuestionId();
            Integer questionType = answers.getCQuestionType();
            //答案类型进行判断
            if(questionType==1){
                SelectQuestions selectQuestions = selectQuestionsDao.selectByPrimaryKey(questionId);
                //对答案进行校验
                if(selectQuestions.getSelectAnswer().trim().equalsIgnoreCase(answers.getCAnswer().trim())){
                    sum = sum+selectQuestions.getSelectScore();
                }
            }else if(questionType == 2){
                BooleanQuestions booleanQuestions = booleanQuestionsDao.selectByPrimaryKey(questionId);
                if(booleanQuestions.getBooAnswer().trim().equals(answers.getCAnswer().trim())){
                    sum = sum + booleanQuestions.getBooScore();
                }
            }

        }
        //添加分数
        Score score = new Score();
        score.setPaperId(paperId);
        score.setScore(sum);
        score.setStuId(uid);
        score.setCommitRepeat(String.valueOf(uid)+String.valueOf(paperId));
        scoreDao.insert(score);

    }
}




