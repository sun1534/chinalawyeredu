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
public class GroupEditAction extends AbstractAction {

	private TsysGroup group;

	public GroupEditAction() {
          rights="sys3,4";
	}

	public String go() throws HibernateException {
                getSession().update(group);
		set("group", group);
                nextpage="groupList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TsysGroup getGroup() {
         if (group==null)
            group = (TsysGroup) get("group");
          return group;
	}

        public String input() throws Exception {
          return "input";
        }


}
