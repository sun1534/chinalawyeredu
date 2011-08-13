package com.changpeng.service.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.service.model.*;




/**
 *
 * <p>功能： 删除车辆管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-08-28</p>
 * @版本： V1.0
 * @修改：
 */

public class BusDeleteAction extends AbstractAction {

	private TserBus bus;

	public BusDeleteAction() {
           rights="ser3,8";
           nextpage="busList.action?pagenumber="+pagenumber;
	}
	public String go() throws HibernateException {
                TserBus bus = (TserBus) get("bus");
                getSession().delete(bus);
                message="删除成功！";
		return SUCCESS;
	}

	public TserBus getBus() {
         if (bus==null)
            bus = (TserBus) get("bus");
          return bus;
	}
}
