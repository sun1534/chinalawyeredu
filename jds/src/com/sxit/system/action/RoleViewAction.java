//$Id: RoleCreateAction.java,v 1.2 2003/12/13 10:17:48 gavin Exp $
package com.sxit.system.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;
import org.hibernate.Query;
import java.util.List;

/**
 *
 * @author zrb
 */
public class RoleViewAction extends AbstractAction {
	private TsysRole role;
        private int roleid;
	public RoleViewAction() {
          rights="sys4,1";
	   role = new TsysRole();
	}

	public String go() throws HibernateException {
           nextpage="roleList.action?pagenumber="+pagenumber;
           role=(TsysRole)getSession().get(TsysRole.class,Integer.valueOf(roleid));
           if(role==null){
             message="未找到此用户";
             return "message";
           }
           set("role", role);
           return SUCCESS;
	}
	public TsysRole getRole() {
		return role;
	}
        public void setRoleid(int roleid) {

          this.roleid = roleid;
        }
        public int getRoleid() {
          return this.roleid;
        }

}
