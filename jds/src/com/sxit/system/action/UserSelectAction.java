package com.sxit.system.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.MatchMode;

import com.sxit.common.action.AbstractListAction;
import com.sxit.system.model.TsysUser;


/**
 *
 * <p>功能： 选择用户(单选)</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-10-08</p>
 * @版本： V1.0
 * @修改：
 */

public class UserSelectAction extends AbstractListAction  {
        private List userlist;
        private String username;        
        public UserSelectAction() {
          rights="prj3,1";
        }
        public String go() throws HibernateException {
            Criteria criteria = getSession().createCriteria(TsysUser.class);
            criteria.add(Expression.eq("satusid", 1));
            if ( username!=null &&!"".equals(username) ) {
            	criteria.add(Expression.like("username", username,MatchMode.ANYWHERE));
            }
            criteria.addOrder(Order.asc("username"));
            userlist=criteria.list();
            
            return SUCCESS;
        }
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
}
