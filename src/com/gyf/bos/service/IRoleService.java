package com.gyf.bos.service;

import com.gyf.bos.model.Decidedzone;
import com.gyf.bos.model.PageBean;
import com.gyf.bos.model.Role;
import com.gyf.bos.service.base.IBaseService;

public interface IRoleService extends IBaseService<Role>{


    /**
     *
     * @param role 角色模型
     * @param funtionIds 权限、功能id
     */
    public void save(Role role,String funtionIds);


    public void pageQuery(PageBean<Role> pb);
}
