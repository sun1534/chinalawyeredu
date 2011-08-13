//$Id: UserCreateAction.java,v 1.2 2003/12/13 10:17:48 gavin Exp $
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
public class UserViewAction extends AbstractAction {
	private TsysUser user;
        private int userid;
	public UserViewAction() {
		user = new TsysUser();
                rights="sys2,1";
	}

	public String go() throws HibernateException {

           nextpage="userList.action?pagenumber="+pagenumber;

           TsysUser user=(TsysUser)getSession().get(TsysUser.class,Long.valueOf(userid));
           if(user==null){
             message="未找到此用户";
             return "message";
           }
//           System.out.println("user:"+user.getUsername());
//           System.out.println("user:"+user.getTsysDepartment().getDepartmentname());
           set("user", user);
           return SUCCESS;
 /**
          List userlist = getQuery()
              .setInteger("userid",userid)
              .list();
          if(userlist==null&&userlist.size()!=1){
            nextpage="userList.action";
            return ERROR;
          }else{
            user = (TsysUser) userlist.get(0);
            set("user", user);
            nextpage="userList.action?pagenumber="+pagenumber;
            return SUCCESS;
          }
**/
	}
        public void setUserid(int userid) {

          this.userid = userid;
        }
        public int getUserid() {
          return this.userid;
        }

//        private Query getQuery() throws HibernateException {
//                String queryName;
//                queryName="from TsysUser as user where userid=:userid";
//                return getSession().createQuery(queryName);
//        }
}
