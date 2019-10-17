package com.qf.examonline.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.examonline.dao.PaperDao;
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
    @Autowired(required = false)
    private PaperDao paperDao;

       @Override
//    //业务层：查询所有试卷种类
    public List<Type> findAllType(String typeName,Integer page, Integer limit) {
        //设置查询的页码和记录数
        PageHelper.startPage(page,limit);
        List<Type> typeList = typeDao.findAllType(typeName);
        return typeList;
    }


    @Override
    //添加一个新的种类
    public void addNewPaperType(String typeName) {
        Type type = typeDao.findOneTypeByTypeName(typeName);
        if (type != null){
            throw new RuntimeException("类别重复");
        }
        typeDao.addNewPaperType(typeName);
    }

    @Override
    //删除一个种类
    public void deleteOnePaperType(Integer typeId) {
        //调用方法删除种类
        typeDao.deleteOnePaperType(typeId);
        //调用方法修改对应试卷的状态
        paperDao.updatePaperStatus(typeId);
    }

    @Override
    //修改一个种类的名字
    public void updatePaperTypeName(Type type) {
        Type type1 = typeDao.findOneTypeByTypeName(type.getTypeName());
        if (type1 != null){
            throw new RuntimeException("类别重复");
        }
        typeDao.updatePaperTypeName(type1);
    }
    @Override
    ////通过id查询type
    public Type findOneTypeByTypeId(Integer typeId) {
        Type type = typeDao.findOneTypeByTypeId(typeId);
        return type;
    }

    @Override
    public Integer findTypeCount(String typeName) {
        Integer count = typeDao.findTypeCount(typeName);
        return count;
    }
}
