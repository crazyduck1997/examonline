package com.qf.examonline.service.impl;

import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.common.ErrorCode;
import com.qf.examonline.dao.BooleanQuestionsDao;
import com.qf.examonline.entity.BooleanQuestions;
import com.qf.examonline.utils.MultioartFileUp;
import com.qf.examonline.utils.MultipartFileToFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.qf.examonline.service.BooleanQuestionService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class BooleanQuestionsServiceImpl implements BooleanQuestionService {

    @Autowired
    CodeMsg codeMsg;

    @Autowired
    BooleanQuestionsDao booleanQuestionsDao;

    @Override
    public int insertBooleanQuestions(MultipartFile file) {
        File dest = MultioartFileUp.upLoad(file, codeMsg.getTempUrl());
        List<BooleanQuestions> list = MultipartFileToFileUtil.change(dest, BooleanQuestions.class);
        int insert = booleanQuestionsDao.insert(list);
        if(insert == 0){
            throw new RuntimeException(codeMsg.getExecteFaile());
        }
        return insert;

    }

    @Override
    public int addBooleanQuestion(List<BooleanQuestions> list) {
        if(list==null){
            throw new RuntimeException(codeMsg.getIsEmpty());
        }
        int insert = booleanQuestionsDao.insert(list);
        if(insert == 0){
            throw new RuntimeException(codeMsg.getExecteFaile());
        }
        return insert;
    }

}
