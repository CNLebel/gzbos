package com.gyf.bos.service.impl;

import com.gyf.bos.dao.IDecidedzoneDao;
import com.gyf.bos.dao.ISubareaDao;
import com.gyf.bos.model.*;
import com.gyf.bos.service.IDecidedzoneService;
import com.gyf.bos.service.IRoleService;
import com.gyf.bos.service.base.BaseServiceImpl;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional//事务是由事务管理器来实现
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {

    @Autowired
    private IdentityService identityService;

    @Override
    public void save(Role role, String funtionIds) {

        //role:瞬时状态[no session,no id]
        //1.保存角色
        roleDao.save(role);//持久状态[session id]

        //把角色保存到activiti的组中
        Group group = new GroupEntity();
        group.setId(role.getName());
        group.setName(role.getName());
        identityService.saveGroup(group);

        //2.添加角色-权限 中间表数据
        //funtionIds:11,112,113,114,115,116
        //2.1 拆分id
        String[] funtionIdsArr = funtionIds.split(",");
        for(String funtionId : funtionIdsArr){
            //2.2把id封装成Funtion模型
            Function function = new Function();
            function.setId(funtionId);

            //2.3 把fuction 存在Role里面去
            role.getFunctions().add(function);//内部执行insert语句
        }

    }

    @Override
    public void pageQuery(PageBean<Role> pb) {
        roleDao.pageQuery(pb);
    }

    @Override
    public void save(Role entity) {

    }

    @Override
    public void delete(Role entity) {

    }

    @Override
    public void update(Role entity) {

    }

    @Override
    public Role find(Serializable id) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
