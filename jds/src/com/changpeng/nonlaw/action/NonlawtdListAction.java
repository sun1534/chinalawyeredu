package com.changpeng.nonlaw.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;

import com.changpeng.nonlaw.model.TnlwNonlaw;
import com.changpeng.operation.model.ToprCreditcard;
import com.sxit.common.action.AbstractListAction;


/**
 *
 * <p>功能： 退单记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-11-08</p>
 * @版本： V1.0
 * @修改：
 */

public class NonlawtdListAction extends AbstractListAction  {
        private List nonlawlist;
        private long bankid;
        private String username;
        private String idcard;
        
        public long getBankid() {
			return bankid;
		}
		public String getUsername() {
			return username;
		}
		public String getIdcard() {
			return idcard;
		}
	
		public void setBankid(long bankid) {
			this.bankid = bankid;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public void setIdcard(String idcard) {
			this.idcard = idcard;
		}

		public NonlawtdListAction() {
          rights="nlw8,1";
        }
        public String go() throws HibernateException {
        	  Criteria criteria = getSession().createCriteria(TnlwNonlaw.class);
      		if (bankid != 0)
      			criteria.add(Expression.eq("bankid", bankid));        		
      
      		if (username!= null&&!"".equals(username))
      			criteria.add(Expression.like("username", username,MatchMode.ANYWHERE));
      		if (idcard!= null&&!"".equals(idcard))
          		criteria.add(Expression.like("idcard", idcard,MatchMode.ANYWHERE));
      		
      	//退单记录
      		criteria.add(Expression.ne("tdflag", 0));
      		
      		nonlawlist =page(criteria);
             
            return SUCCESS;
        }

        public List getNonlawlist() {
          return nonlawlist;
        }
}
