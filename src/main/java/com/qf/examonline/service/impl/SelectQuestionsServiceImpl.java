package com.qf.examonline.service.impl;

import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.common.ErrorCode;
import com.qf.examonline.dao.SelectQuestionsDao;
import com.qf.examonline.entity.SelectQuestions;
import com.qf.examonline.service.SelectQuestionsService;
import com.qf.examonline.utils.ImportExcelUtil;
import com.qf.examonline.utils.MultioartFileUp;
import com.qf.examonline.utils.MultipartFileToFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class SelectQuestionsServiceImpl implements SelectQuestionsService {

    @Autowired
    SelectQuestionsDao selectQuestionsDao;

    @Autowired
    CodeMsg codeMsg;

    @Override
    public int insertSelectQuestions(MultipartFile file) {
        File dest = MultioartFileUp.upLoad(file, codeMsg.getTempUrl());
        List<SelectQuestions> list = MultipartFileToFileUtil.change(dest, SelectQuestions.class);
        int insert = selectQuestionsDao.insert(list);
            if(insert==0) {
                return ErrorCode.ERROR;
            }
        return ErrorCode.SUCCESS;
    }



}
