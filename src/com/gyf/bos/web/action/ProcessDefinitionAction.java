package com.gyf.bos.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.List;
import java.util.zip.ZipInputStream;

public class ProcessDefinitionAction extends ActionSupport {

    // 仓库的service
    @Autowired
    private RepositoryService rs;
    private List<ProcessDefinition> list;

    public List<ProcessDefinition> getList() {
        return list;
    }

    public String list(){
        // 查询流程定义
        ProcessDefinitionQuery query = rs.createProcessDefinitionQuery();
        query.orderByDeploymentId().desc();
        list = query.list();

        // 数据存在值栈中，提供list的get方法
        return "list";
    }

    private File zipFile;
    private String zipFileContentType;
    private String zipFileFileName;

    public void setZipFileContentType(String zipFileContentType) {
        this.zipFileContentType = zipFileContentType;
    }

    public void setZipFileFileName(String zipFileFileName) {
        this.zipFileFileName = zipFileFileName;
    }

    public void setZipFile(File zipFile) {
        this.zipFile = zipFile;
    }

    // 发布一个流程
    public String deploy() throws Exception {

        //获取部署的对象
        DeploymentBuilder builder = rs.createDeployment();

        //builder 添加压缩包的输入流
        builder.addZipInputStream(new ZipInputStream(new FileInputStream(zipFile)));

        builder.deploy();

        // 查询最新的数据
        ProcessDefinitionQuery query = rs.createProcessDefinitionQuery();
        query.orderByDeploymentId().desc();
        list = query.list();

        return "list";
    }

    /**
     * 显示流程图
     * @return
     * */
    private String id; //流程定义id

    public void setId(String id) {
        this.id = id;
    }

    public String viewpng(){

        InputStream imgIs =  rs.getProcessDiagram(id);

        ActionContext.getContext().getValueStack().set("imgIS", imgIs);

        return "viewpng";
    }

    public void del() throws IOException {
        //先根据流程定义id查询部署id
        ProcessDefinitionQuery query =  rs.createProcessDefinitionQuery();
        query.processDefinitionId(id);

        ProcessDefinition pd = query.singleResult();

        String deploymentId = pd.getDeploymentId();
        rs.deleteDeployment(deploymentId);

        ServletActionContext.getResponse().getWriter().write("success");
    }


}
