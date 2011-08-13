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
public class GroupCreateAction extends AbstractAction {

	private TsysGroup group;


	public GroupCreateAction() {
		group = new TsysGroup();
	}

	public String go() throws HibernateException {
		group.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		getSession().save(group);
		set("group", group);
                nextpage="groupList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TsysGroup getGroup() {
		return group;
	}
        public String input() throws Exception {
            return "input";
    }
}
