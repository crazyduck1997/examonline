package com.qf.examonline;


import com.github.pagehelper.PageInfo;
import com.qf.examonline.common.CodeMsg;
import com.qf.examonline.common.ErrorCode;
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
    @Autowired(required = false)
    private CodeMsg codeMsg;
    @Test
//    //查询所有种类测试
    public void findAllTypeTest(){
        PageInfo<Type> allType = typeService.findAllType("python",1, 2);
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
        type.setTypeId(4);
        type.setTypeName("Python");
        typeService.updatePaperTypeName(type);
    }
    @Test
    public void deleteOneType(){
        typeService.deleteOnePaperType(3);
    }

    @Test
   public void add(){
        typeService.addNewPaperType("python");
    }
    @Test
    public void findTypeById(){
        Type type = typeService.findOneTypeByTypeId(1);
        System.out.println(type);
    }
    @Test
    public void findTypeCount(){
        Integer python = typeService.findTypeCount("e");
        System.out.println("总数量......"+python);
    }

}
