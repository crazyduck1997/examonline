package com.qf.examonline.service.impl;

import com.qf.examonline.dao.UserAnswersDao;
import com.qf.examonline.entity.UserAnswers;
import com.qf.examonline.service.UserAnswerService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {

    @Autowired
    UserAnswersDao userAnswersDao;

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 自动判卷，选择题/判断题
     * @param list
     */
    @Override
    public void commitPaper(List<UserAnswers> list) {
        userAnswersDao.insert(list);
        amqpTemplate.convertAndSend("queue.commit", list);

    }
}
