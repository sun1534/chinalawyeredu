package com.changpeng.nonlaw.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.nonlaw.model.TnlwNonlawtask;
import com.changpeng.operation.model.*;
import com.sxit.common.action.AbstractListAction;


/**
 * 电话催收
 * @author sinhoo
 * Jun 12, 2009
 */
public class NonlawCallAction extends AbstractListAction  {
        private List tasklist;
        
        private List banklist; //支行列表
        
        private long bankid;

        private String username;
        private String idcard;
        private String paydate;
        private String canlink;
        private String curdate;
        
        private String bankname; //支行
        private String consigndate; //委托日期
        
        private int overnum=-1;
        
        
        public int getOvernum() {
			return overnum;
		}
		public void setOvernum(int overnum) {
			this.overnum = overnum;
		}
		public String getBankname() {
			return bankname;
		}
		public String getConsigndate() {
			return consigndate;
		}
		public void setBankname(String bankname) {
			this.bankname = bankname;
		}
		public void setConsigndate(String consigndate) {
			this.consigndate = consigndate;
		}
		public String getCurdate() {
			return curdate;
		}
		public void setCurdate(String curdate) {
			this.curdate = curdate;
		}
        public String getCanlink() {
			return canlink;
		}
		public void setCanlink(String canlink) {
			this.canlink = canlink;
		}
        public String getPaydate() {
			return paydate;
		}
		public void setPaydate(String paydate) {
			this.paydate = paydate;
		}
		public NonlawCallAction() {
          rights="nlw3,1";
        }
        public String go() throws HibernateException {
        		//banklist=getSession().createSQLQuery("select bankid,bankname from tnlw_nonlaw group by bankid,bankname order by bankid ").list();
                Criteria criteria = getSession().createCriteria(TnlwNonlawtask.class);
                criteria.add(Expression.eq("userid", curuser.getUserid()));
                if(paydate!=null&&!"".equals(paydate)){
                 	//criteria.add(Expression.eq("paydate", paydate));
                	String now=new java.text.SimpleDateFormat("yyyy-MM-dd 00:00").format(new java.util.Date());
                 	criteria.add(Expression.between("paydate", now, paydate+" 23:59"));
                 }
                
                if(curdate!=null&&!"".equals(curdate))
               	 	criteria.add(Expression.between("paydate", curdate+" 00:00", curdate+" 23:59"));
                
                if(canlink!=null&&!"".equals(canlink)){
               	 if(!"y".equals(canlink))
               		 criteria.add(Expression.eq("canlink", canlink));
               	 else{ //能联系上
               		criteria.add(Restrictions.or(Restrictions.eq("canlink", "y"), Restrictions.isNull("canlink")));
               		// criteria.add(Expression.ne("canlink", "n"));
               		// criteria.add(Expression.ne("canlink", "nn"));
               		// criteria.add(Expression.ne("canlink", "yy"));
               	 }
                }
              //催收中
                criteria.add(Expression.eq("taskstat", 0));    
                
                Criteria c2=criteria.createCriteria("tnlwNonlaw").add(Expression.ne("repaystatus", 2)); //剔除掉已全清的
               
                
                if(overnum==0)
               	 c2.add(Expression.le("overnum", "3"));
                else if(overnum==3)
               	 c2.add(Expression.eq("overnum", "3"));
                else if(overnum==4)
               	 c2.add(Expression.ge("overnum", "3"));
                
                if (bankid != 0)
        			c2.add(Expression.eq("bankid", bankid));        		
     
        		if (username!= null&&!"".equals(username))
        			c2.add(Expression.like("username", username,MatchMode.ANYWHERE));
        		if (idcard!= null&&!"".equals(idcard))
            		c2.add(Expression.like("idcard", idcard,MatchMode.ANYWHERE));
        		
        		if (consigndate!= null&&!"".equals(consigndate))
               	 	c2.add(Expression.eq("consigndate", consigndate));    
        		if (bankname!= null&&!"".equals(bankname))
        			c2.add(Expression.like("bankname", bankname,MatchMode.ANYWHERE));
        		
        		//c2.addOrder(Order.desc("nonlawid"));
        		c2.addOrder(Order.asc("username"));
        		
        		tasklist = c2./*setMaxResults(maxperpage).setFirstResult(
        				maxperpage * pagenumber).*/setCacheable(true).list();
            
                return SUCCESS;
        }
  
        public List getTasklist() {
          return tasklist;
        }
		public long getBankid() {
			return bankid;
		}

		public String getUsername() {
			return username;
		}
	
		public void setBankid(long bankid) {
			this.bankid = bankid;
		}
	
		public void setUsername(String username) {
			this.username = username;
		}
		public String getIdcard() {
			return idcard;
		}
		public void setIdcard(String idcard) {
			this.idcard = idcard;
		}
		public List getBanklist() {
			return banklist;
		}

}
