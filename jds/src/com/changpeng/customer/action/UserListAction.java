package com.changpeng.customer.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.sxit.common.action.AbstractListAction;
import com.sxit.system.model.*;
/**
 * 员工列表，即员工通信录
 * @author sinhoo
 * Aug 31, 2009
 */
public class UserListAction extends AbstractListAction  {
        private List userlist;
        private String username;
        
		public List getUserlist() {
			return userlist;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public UserListAction() {
          rights="usr3,1";
        }
        public String go() throws HibernateException {
        	Criteria criteria = getSession().createCriteria(TsysUser.class); 
        	criteria.add(Expression.ne("userid", 1l));
        	criteria.add(Expression.ne("userid", 2l));
     		if(username!=null&&!"".equals(username)){
     			criteria.add(Expression.ilike("username", username, MatchMode.ANYWHERE));  
     		}
     		criteria.addOrder(Order.asc("tsysDepartment.departmentid"));
     		criteria.addOrder(Order.asc("userid"));
     		userlist =page(criteria);
     		return SUCCESS;
        }
       
}
