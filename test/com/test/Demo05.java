package com.test;

import com.gyf.bos.dao.IFunctionDao;
import com.gyf.bos.model.Function;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Demo05 {

    @Autowired
    private IFunctionDao functionDao;
    //测试Dao
    @Test
    public void test1(){
       List<Function> functions = functionDao.findListByUserId("4028b8816367b603016367b6d6a20000");
        System.out.println("size:" + functions.size());
       for(Function f : functions){
           System.out.println(f.getId() + ":" + f.getName());
       }
    }


    @Autowired
    private RuntimeService rs;

    @Test
    public void test2(){
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("bxyy","出差路费报销");
        info.put("bxje","2688.00");
        info.put("employeeName","wangwu");
        rs.startProcessInstanceByKey("bxlc",info);
    }

    @Autowired
    private TaskService ts;
    @Test
    public void test3(){
        ts.complete("307");
    }
}
