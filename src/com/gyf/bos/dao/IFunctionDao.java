package com.gyf.bos.dao;

import com.gyf.bos.dao.base.IBaseDao;
import com.gyf.bos.model.Decidedzone;
import com.gyf.bos.model.Function;

import java.util.List;

public interface IFunctionDao extends IBaseDao<Function> {

    //根据用户id查找权限
    public List<Function> findListByUserId(String userId);

    /**
     * 根据id查找菜单
     * @param id
     * @return
     */
    public List<Function> findMenuByUserId(String id);

    /**
     * 查找所有菜单
     * @return
     */
    public List<Function> findAllMenu() ;
}
