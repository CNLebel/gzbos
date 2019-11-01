package com.gyf.bos.service;

import com.gyf.bos.model.Decidedzone;
import com.gyf.bos.model.Noticebill;
import com.gyf.bos.model.PageBean;
import com.gyf.bos.service.base.IBaseService;

public interface INoticebillService extends IBaseService<Noticebill>{

    /**
     *
     * @param entity 业务通知单模型
     * @param decidedzoneId 定区id,用于自动分单
     */
    public void save(Noticebill entity,String decidedzoneId);
}
