package com.gyf.bos.service.base;

import com.gyf.bos.dao.*;
import com.gyf.bos.model.PageBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.Oneway;
import java.io.Serializable;
import java.util.List;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    //业务通知单dao
    @Autowired
    protected INoticebillDao noticebillDao;

    //定区dao
    @Autowired
    protected IDecidedzoneDao decidedzoneDao;

    //工单dao
    @Autowired
    protected IWorkbillDao workbillDao;

    //快速工作单dao
    @Autowired
    protected IWorkordermanageDao workordermanageDao;

    @Autowired
    protected IFunctionDao functionDao;


    @Autowired
    protected IRoleDao roleDao;//角色dao

    @Autowired
    protected IUserDao userDao;

}
