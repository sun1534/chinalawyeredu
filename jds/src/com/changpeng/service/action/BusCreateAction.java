package com.changpeng.service.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.service.model.*;


/**
 *
 * <p>功能： 创建车辆管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-08-28</p>
 * @版本： V1.0
 * @修改：
 */

public class BusCreateAction extends AbstractAction {

	private TserBus bus;


	public BusCreateAction() {
           rights="ser3,2";
		bus = new TserBus();
	}

	public String go() throws HibernateException {
		bus.setCreatetime(new java.util.Date());
		getSession().save(bus);
		set("bus", bus);
                nextpage="busList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TserBus getBus() {
		return bus;
	}
        public String input() throws Exception {
            return "input";
    }
}
