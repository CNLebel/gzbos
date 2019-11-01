package com.gyf.bos.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ProcessInstanceAction extends ActionSupport {

    @Autowired
    private RuntimeService rs;

    private List<ProcessInstance> list;

    public List<ProcessInstance> getList() {
        return list;
    }

    public String list(){
        //查询流程实例
        ProcessInstanceQuery query = rs.createProcessInstanceQuery();
        query.orderByProcessInstanceId().desc();
        list = query.list();
        return "list";
    }

    private String id; //流程实现id

    public void setId(String id) {
        this.id = id;
    }

    public void findData() throws IOException {
        //根据流程实例id返回流程变量数据
        Map<String, Object> data = rs.getVariables(id);
        ServletActionContext.getResponse().setHeader("content-Type","text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().write(data.toString());

    }
}
