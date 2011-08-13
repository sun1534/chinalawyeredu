package com.changpeng.service.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.service.model.*;


/**
 *
 * <p>功能： 创建物业管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-08-28</p>
 * @版本： V1.0
 * @修改：
 */

public class OwnerCreateAction extends AbstractAction {

	private TserOwner owner;


	public OwnerCreateAction() {
           rights="ser2,2";
		owner = new TserOwner();
	}

	public String go() throws HibernateException {
		owner.setCreatetime(new java.util.Date());
		getSession().save(owner);
		set("owner", owner);
                nextpage="ownerList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TserOwner getOwner() {
		return owner;
	}
        public String input() throws Exception {
            return "input";
    }
}
