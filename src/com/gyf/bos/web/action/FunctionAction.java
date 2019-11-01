package com.gyf.bos.web.action;

import com.gyf.bos.model.Function;
import com.gyf.bos.model.Staff;
import com.gyf.bos.model.User;
import com.gyf.bos.utils.BOSContextUtils;
import com.gyf.bos.web.action.base.BaseAction;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FunctionAction extends BaseAction<Function> {

    Logger logger = Logger.getLogger(FunctionAction.class);



    //===================分页查询返回json数据==========================


    public void pageQuery() throws IOException {
        //1.接收参数 page[当前页] rows[每页显示多少条]
        //封装条件
        /**
         * 因为Funtion模型里有page属性，然后BaseAction也有page属性
         * Struts框架把数据放在模型优先
         */
        pb.setCurrentPage(Integer.parseInt(getModel().getPage()));
        pb.setPageSize(rows);

        //2.调用service,参数里传一个PageBean
        functionService.pageQuery(pb);

        //3.返回json数据
        responseJson(pb,new String[]{"currentPage","pageSize","detachedCriteria","function","functions","roles"});

    }


    @Override
    public String save() {
        functionService.save(getModel());
        return NONE;
    }

    @Override
    public String update() {
        return null;
    }

    @Override
    public String delete() throws IOException {
        return null;
    }

    @Override
    public String list() {
        return null;
    }

    public void listJson() throws IOException {
        List<Function> functions = functionService.findAll();
        responseJson(functions,new String[]{"function","funtions","roles"});

    }

    public void findMenu() throws IOException {
        List<Function> menus = null;
        User loginUser = BOSContextUtils.loginUser();
        if(loginUser.getUsername().equals("admin")){
            menus = functionService.findAllMenu();
        }else{
            menus = functionService.findMenuByUserId(loginUser.getId());
        }

        responseJson(menus,new String[]{"function","funtions","roles"});
    }


}
