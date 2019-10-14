package com.qf.examonline;


import com.qf.examonline.entity.Type;
import com.qf.examonline.service.TypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PaperControllerTest {

    @Autowired
    private TypeService typeService;

    @Test
    //查询所有种类测试
    public void findAllTypeTest(){
        List<Type> allType = typeService.findAllType();
        System.out.println(allType);
    }
    @Test
    //添加新的种类测试
    public void addNewType(){
        typeService.addNewPaperType("python");
    }
    @Test
    // 修改种类名字测试
    public void updateTypeName(){
        Type type = new Type();
        type.setTypeId(3);
        type.setTypeName("pyrhon1");
        typeService.updatePaperTypeName(type);
    }
    @Test
    public void deleteOneType(){
        typeService.deleteOnePaperType(3);
    }
}
