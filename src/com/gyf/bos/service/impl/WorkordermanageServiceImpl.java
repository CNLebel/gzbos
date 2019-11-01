package com.gyf.bos.service.impl;

import com.gyf.bos.dao.IDecidedzoneDao;
import com.gyf.bos.dao.ISubareaDao;
import com.gyf.bos.model.Decidedzone;
import com.gyf.bos.model.PageBean;
import com.gyf.bos.model.Subarea;
import com.gyf.bos.model.Workordermanage;
import com.gyf.bos.service.IDecidedzoneService;
import com.gyf.bos.service.IWorkordermanageService;
import com.gyf.bos.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional//事务是由事务管理器来实现
public class WorkordermanageServiceImpl extends BaseServiceImpl<Workordermanage> implements IWorkordermanageService {


    @Override
    public void save(Workordermanage entity) {
        workordermanageDao.save(entity);
    }

    @Override
    public void delete(Workordermanage entity) {

    }

    @Override
    public void update(Workordermanage entity) {

    }

    @Override
    public Workordermanage find(Serializable id) {
        return null;
    }

    @Override
    public List<Workordermanage> findAll() {
        return null;
    }
}
