package com.qf.examonline.service.impl;

import com.qf.examonline.dao.TypeDao;
import com.qf.examonline.entity.Type;
import com.qf.examonline.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired(required = false)
    private TypeDao typeDao;


    @Override
    //业务层：查询所有试卷种类
    public List<Type> findAllType() {
        List<Type> typeList = typeDao.findAllType();
        return typeList;
    }

    @Override
    //添加一个新的种类
    public void addNewPaperType(String typeName) {
            typeDao.addNewPaperType(typeName);
    }

    @Override
    //删除一个种类
    public void deleteOnePaperType(Integer typeId) {
        typeDao.deleteOnePaperType(typeId);
    }

    @Override
    //修改一个种类的名字
    public void updatePaperTypeName(Type type) {
        typeDao.updatePaperTypeName(type);
    }
}
