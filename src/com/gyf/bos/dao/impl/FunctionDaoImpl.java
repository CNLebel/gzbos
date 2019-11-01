package com.gyf.bos.dao.impl;

import com.gyf.bos.dao.IDecidedzoneDao;
import com.gyf.bos.dao.IFunctionDao;
import com.gyf.bos.dao.base.BaseDaoImpl;
import com.gyf.bos.model.Decidedzone;
import com.gyf.bos.model.Function;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {


    @Override
    public List<Function> findListByUserId(String userId) {
      /*  SELECT DISTINCT f.id,f.name,f.page,f.code
        FROM auth_function f
        LEFT OUTER JOIN role_function rf
        ON rf.function_id = f.id
        LEFT OUTER JOIN auth_role r
        ON rf.role_id = r.id
        LEFT OUTER JOIN user_role ur
        ON r.id = ur.role_id
        WHERE ur.user_id = '4028b8816367b603016367b7add80001';*/
        String hql = "SELECT DISTINCT f FROM Function f ";
        hql += "LEFT OUTER JOIN f.roles r ";
        hql += "LEFT OUTER JOIN r.users u ";
        hql += "WHERE u.id = ?";
        return hibernateTemplate.find(hql,userId);
    }

    @Override
    public List<Function> findMenuByUserId(String id) {
        String hql = "SELECT DISTINCT f From Function f ";
        hql += "LEFT OUTER JOIN f.roles r ";
        hql += "LEFT OUTER JOIN r.users u ";
        hql += "WHERE u.id = ? AND f.generatemenu = '1' ORDER BY f.zindex DESC";
        return (List<Function>) hibernateTemplate.find(hql,id);
    }

    @Override
    public List<Function> findAllMenu() {
        String hql = "From Function f where f.generatemenu = '1' ORDER BY f.zindex DESC";
        return (List<Function>) hibernateTemplate.find(hql);
    }
}
