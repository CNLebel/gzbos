package com.gyf.bos.service.impl;

import com.gyf.bos.dao.IDecidedzoneDao;
import com.gyf.bos.dao.ISubareaDao;
import com.gyf.bos.model.Decidedzone;
import com.gyf.bos.model.Function;
import com.gyf.bos.model.PageBean;
import com.gyf.bos.model.Subarea;
import com.gyf.bos.service.IDecidedzoneService;
import com.gyf.bos.service.IFunctionService;
import com.gyf.bos.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional//事务是由事务管理器来实现
public class FunctionServiceImpl extends BaseServiceImpl<Function> implements IFunctionService {


    @Override
    public void pageQuery(PageBean<Function> pb) {

        functionDao.pageQuery(pb);
    }

    @Override
    public List<Function> findMenuByUserId(String id) {
        return functionDao.findMenuByUserId(id);
    }

    @Override
    public List<Function> findAllMenu() {
        return functionDao.findAllMenu();
    }

    @Override
    public void save(Function entity) {
        functionDao.save(entity);
    }

    @Override
    public void delete(Function entity) {

    }

    @Override
    public void update(Function entity) {

    }

    @Override
    public Function find(Serializable id) {
        return null;
    }

    @Override
    public List<Function> findAll() {
        return functionDao.findAll();
    }
}
