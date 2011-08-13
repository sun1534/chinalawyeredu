package com.changpeng.operation.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;

import com.changpeng.operation.model.ToprCreditcard;
import com.sxit.common.action.AbstractListAction;


/**
 * 列表结案记录
 * @author sinhoo
 * Jul 31, 2009
 */
public class CreditendListAction extends AbstractListAction  {
        private List creditcardlist;
        private long bankid;
        private String consigntype;
        private String consignflag;
        private String username;
        private String creditcard;
        private int state=-1;
        public CreditendListAction() {
          rights="opr6,1";
        }
        public String go() throws HibernateException {
              /*  creditcardlist = getQuery()
                          .setMaxResults(maxperpage)
                        .setFirstResult(maxperpage * pagenumber)
                        .setCacheable(true)
                        .list();*/
                
                Criteria criteria = getSession().createCriteria(ToprCreditcard.class);
        		if (bankid != 0)
        			criteria.add(Expression.eq("bankid", bankid));        		
        		if (consigntype!= null&&!"".equals(consigntype))
        			criteria.add(Expression.eq("consigntype", consigntype));
        		if (consignflag!= null&&!"".equals(consignflag))
            		criteria.add(Expression.eq("consigntype", consignflag));
        		if (username!= null&&!"".equals(username))
        			criteria.add(Expression.like("username", username,MatchMode.ANYWHERE));
        		if (creditcard!= null&&!"".equals(creditcard))
            		criteria.add(Expression.like("creditcard", creditcard,MatchMode.ANYWHERE));
        		
        		//criteria.add(Expression.eq("lawflag", 2));   
        		criteria.add(Expression.eq("repaystatus", 2));  
        		creditcardlist = page(criteria);
        	
                return SUCCESS;
        }
       /* private Query getQuery() throws HibernateException {
                String queryName ;
                queryName="from ToprCreditcard as creditcard order by creditcard.creditcardid desc";
                Query query = getSession().createQuery(queryName);
                recordsize = query.list().size();
                pagesize = (recordsize - 1) / maxperpage + 1;
                pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
                return query;
        }*/
        public List getCreditcardlist() {
          return creditcardlist;
        }
		public long getBankid() {
			return bankid;
		}
		public String getConsigntype() {
			return consigntype;
		}
		public String getConsignflag() {
			return consignflag;
		}
		public String getUsername() {
			return username;
		}
		public String getCreditcard() {
			return creditcard;
		}
		public void setBankid(long bankid) {
			this.bankid = bankid;
		}
		public void setConsigntype(String consigntype) {
			this.consigntype = consigntype;
		}
		public void setConsignflag(String consignflag) {
			this.consignflag = consignflag;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public void setCreditcard(String creditcard) {
			this.creditcard = creditcard;
		}
		public int getState() {
			return state;
		}
		public void setState(int state) {
			this.state = state;
		}
}
