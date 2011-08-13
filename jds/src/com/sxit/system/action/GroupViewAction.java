//$Id: GroupCreateAction.java,v 1.2 2003/12/13 10:17:48 gavin Exp $
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
public class GroupViewAction extends AbstractAction {
	private TsysGroup group;
        private int groupid;
	public GroupViewAction() {
          rights="sys3,1";
		group = new TsysGroup();
	}

	public String go() throws HibernateException {

           nextpage="groupList.action?pagenumber="+pagenumber;

           group=(TsysGroup)getSession().get(TsysGroup.class,Integer.valueOf(groupid));
           if(group==null){
             message="未找到此用户";
             return "message";
           }
           set("group", group);
           return SUCCESS;
	}
	public TsysGroup getGroup() {
		return group;
	}
        public void setGroupid(int groupid) {

          this.groupid = groupid;
        }
        public int getGroupid() {
          return this.groupid;
        }
}
