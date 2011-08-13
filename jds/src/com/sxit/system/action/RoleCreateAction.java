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
public class RoleCreateAction extends AbstractAction {

	private TsysRole role;


	public RoleCreateAction() {
           rights="sys4,2";
		role = new TsysRole();
	}

	public String go() throws HibernateException {
		role.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		getSession().save(role);
		set("role", role);
                nextpage="roleList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TsysRole getRole() {
		return role;
	}
        public String input() throws Exception {
            return "input";
    }
}
