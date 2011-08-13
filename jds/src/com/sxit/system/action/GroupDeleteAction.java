//$Id: GroupCreateAction.java,v 1.2 2003/12/13 10:17:48 gavin Exp $
package com.sxit.system.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;




/**
 *
 * @author zrb
 */
public class GroupDeleteAction extends AbstractAction {

	private TsysGroup group;

	public GroupDeleteAction() {
          rights="sys3,8";
	}
	public String go() throws HibernateException {
                TsysGroup group = (TsysGroup) get("group");
                getSession().delete(group);
                message="删除成功！";
                nextpage="groupList.action?pagenumber="+pagenumber;
		return SUCCESS;
	}

	public TsysGroup getGroup() {
         if (group==null)
            group = (TsysGroup) get("group");
          return group;
	}
}
