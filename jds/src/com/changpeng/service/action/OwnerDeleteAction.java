package com.changpeng.service.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.service.model.*;




/**
 *
 * <p>功能： 删除物业管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-08-28</p>
 * @版本： V1.0
 * @修改：
 */

public class OwnerDeleteAction extends AbstractAction {

	private TserOwner owner;

	public OwnerDeleteAction() {
           rights="ser2,8";
           nextpage="ownerList.action?pagenumber="+pagenumber;
	}
	public String go() throws HibernateException {
                TserOwner owner = (TserOwner) get("owner");
                getSession().delete(owner);
                message="删除成功！";
		return SUCCESS;
	}

	public TserOwner getOwner() {
         if (owner==null)
            owner = (TserOwner) get("owner");
          return owner;
	}
}
