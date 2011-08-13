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
public class RoleEditAction extends AbstractAction {

	private TsysRole role;

	public RoleEditAction() {
          rights="sys4,4";
	}

	public String go() throws HibernateException {
                getSession().update(role);
		set("role", role);
                nextpage="roleList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TsysRole getRole() {
         if (role==null)
            role = (TsysRole) get("role");
          return role;
	}

        public String input() throws Exception {
          return "input";
        }


}
