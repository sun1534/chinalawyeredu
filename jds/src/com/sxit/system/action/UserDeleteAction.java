package com.sxit.system.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;




/**
 *
 * @author zrb
 */
public class UserDeleteAction extends AbstractAction {

	private TsysUser user;

	public UserDeleteAction() {
          rights="sys2,8";
	}
	public String go() throws HibernateException {
                TsysUser user = (TsysUser) get("user");
                getSession().delete(user);
                commit();
                message="删除成功！";
                nextpage="userList.action?pagenumber="+pagenumber;
		return SUCCESS;
	}

	public TsysUser getUser() {
         if (user==null)
            user = (TsysUser) get("user");
          return user;
	}
}
