//$Id: RoleCreateAction.java,v 1.2 2003/12/13 10:17:48 gavin Exp $
package com.sxit.system.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;


/**
 *
 * @author zrb
 */
public class RoleDeleteAction extends AbstractAction {

	private TsysRole role;

	public RoleDeleteAction() {
           rights="sys4,8";
	}
	public String go() throws HibernateException {
                TsysRole role = (TsysRole) get("role");
                getSession().delete(role);
                message="删除成功！";
                nextpage="roleList.action?pagenumber="+pagenumber;
		return SUCCESS;
	}

	public TsysRole getRole() {
         if (role==null)
            role = (TsysRole) get("role");
          return role;
	}
}
