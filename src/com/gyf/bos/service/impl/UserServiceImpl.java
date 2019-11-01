package com.gyf.bos.service.impl;

import com.gyf.bos.dao.IUserDao;
import com.gyf.bos.model.PageBean;
import com.gyf.bos.model.Role;
import com.gyf.bos.model.User;
import com.gyf.bos.service.IUserService;
import com.gyf.bos.service.base.BaseServiceImpl;
import com.gyf.bos.utils.MD5Utils;
import org.activiti.engine.IdentityService;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional//事务是由事务管理器来实现
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Autowired
    private IdentityService identityService;

    @Override
    public User findByTel(String tel) {
        return null;
    }

    @Override
    public User login(String username, String password) {
        return userDao.find(username, MD5Utils.text2md5(password));
    }

    @Override
    public void modifyPassword(String newPwd, String userId) {
        /*String hql = "UPDATE User SET password=? WHERE id = ?";
        userDao.executeUpdate(hql,MD5Utils.text2md5(newPwd),userId);*/

        userDao.executeUpdateByQueryName("updatePwd",MD5Utils.text2md5(newPwd),userId);
    }

    @Override
    public void save(User model, String[] roleIds) {

        //保存到t_user表
        userDao.save(model);  //持久态

        //保存到activiti的act_id_user表
        org.activiti.engine.identity.User actUser = new UserEntity();
        actUser.setId(model.getId());  //uuid
        actUser.setFirstName(model.getUsername());
        identityService.saveUser(actUser);

        //用户拥有的角色
        for (String roleId : roleIds){
            Role role = roleDao.find(roleId);
//            role.setId(roleId);
            model.getRoles().add(role);

            //维护activiti的用户跟组的关系
            String userId = actUser.getId();
            String groupId = role.getName();
            identityService.createMembership(userId,groupId);

        }

    }

    @Override
    public void pageQuery(PageBean<User> pb) {
        userDao.pageQuery(pb);
    }

    @Override
    public void save(User entity) {

        userDao.save(entity);
    }

    @Override
    public void delete(User entity) {
        userDao.delete(entity);
    }

    @Override
    public void update(User entity) {
        userDao.update(entity);
    }

    @Override
    public User find(Serializable id) {
        return userDao.find(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
