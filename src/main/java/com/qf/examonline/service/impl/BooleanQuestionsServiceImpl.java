package com.qf.examonline.service.impl;

import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.dao.BooleanQuestionsDao;
import com.qf.examonline.entity.BooleanQuestions;
import com.qf.examonline.utils.ImportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.qf.examonline.service.BooleanQuestionService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class BooleanQuestionsServiceImpl implements BooleanQuestionService {

    @Autowired
    CodeMsg codeMsg;

    @Autowired
    BooleanQuestionsDao booleanQuestionsDao;

    @Override
    public int insertBooleanQuestions(MultipartFile file) {
        List<BooleanQuestions> list = null;
        try {
            InputStream inputStream = file.getInputStream();
            list = ImportExcelUtil.importExcel(inputStream, BooleanQuestions.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
