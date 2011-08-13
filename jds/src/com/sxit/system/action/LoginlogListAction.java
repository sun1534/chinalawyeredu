//$Id: ListRolesAction.java,v 1.5 2003/12/13 13:37:49 gavin Exp $
package com.sxit.system.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.action.AbstractListAction;
import com.sxit.system.model.SysLoginlog;

/**
 * 用户登录日志
 * @author sinhoo
 * Jul 7, 2009
 */
public class LoginlogListAction extends AbstractListAction  {
        private List loglist;
        private String loginname;
        private String logintime;
        public LoginlogListAction() {
    
          rights="sys8,1";
        }
        
        public List getLoglist() {
			return loglist;
		}

		public String getLoginname() {
			return loginname;
		}

		public String getLogintime() {
			return logintime;
		}

		public void setLoginname(String loginname) {
			this.loginname = loginname;
		}

		public void setLogintime(String logintime) {
			this.logintime = logintime;
		}

		public String go() throws HibernateException{
			  Criteria criteria = getSession().createCriteria(SysLoginlog.class);
	      	
	      		if (loginname!= null&&!"".equals(loginname))
	      			criteria.add(Expression.like("loginname", loginname,MatchMode.ANYWHERE));
	      		if (logintime != null && !logintime.equals("")) {
	      			DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      			try{
	      				java.util.Date begin = new java.util.Date(df.parse(logintime + " 00:00:00").getTime());
		    			java.util.Date end = new java.util.Date(df.parse(logintime + " 23:59:59").getTime());
		    			criteria.add(Restrictions.between("logintime", begin, end));
	      			}catch(ParseException e){
	      				LOG.error("ParseException:"+e);
	      			}
	    			
	    		}
	      		criteria.addOrder(Order.desc("loginid"));

	      		loglist=page(criteria);
	            return SUCCESS;
        }
    
}
