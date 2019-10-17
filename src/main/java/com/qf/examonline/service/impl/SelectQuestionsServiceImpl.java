package com.qf.examonline.service.impl;

import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.dao.SelectQuestionsDao;
import com.qf.examonline.entity.SelectQuestions;
import com.qf.examonline.service.SelectQuestionsService;
import com.qf.examonline.utils.ImportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class SelectQuestionsServiceImpl implements SelectQuestionsService {

    @Autowired
    SelectQuestionsDao selectQuestionsDao;

    @Autowired
    CodeMsg codeMsg;

    @Override
    public int insertQuestions(MultipartFile file) {
        List<SelectQuestions> list = null;
        try {
            InputStream inputStream = file.getInputStream();
            list = ImportExcelUtil.importExcel(inputStream, SelectQuestions.class);
        } catch (Exception e) {
           throw new RuntimeException(codeMsg.getExecteFaile());
        }
        int insert = selectQuestionsDao.insert(list);
        if(insert == 0){
            throw new RuntimeException(codeMsg.getExecteFaile());
        }
        return insert;
    }

    @Override
    public int addSelectQuestions(List<SelectQuestions> list) {

        int insert = selectQuestionsDao.insert(list);
        if(insert == 0){
            throw new RuntimeException(codeMsg.getExecteFaile());
        }
        return insert;
    }
}
