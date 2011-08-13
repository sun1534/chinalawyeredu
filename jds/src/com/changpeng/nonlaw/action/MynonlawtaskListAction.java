package com.changpeng.nonlaw.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.nonlaw.model.TnlwNonlaw;
import com.changpeng.nonlaw.model.TnlwNonlawtask;
import com.changpeng.operation.model.ToprCreditcard;
import com.changpeng.operation.model.ToprCredittask;
import com.sxit.common.action.AbstractListAction;


/**
 *
 * <p>功能： 列表催收记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-18</p>
 * @版本： V1.0
 * @修改：
 */

public class MynonlawtaskListAction extends AbstractListAction  {
        private List tasklist;
        private long bankid;
        private String username;
        private String idcard;
        private String paydate;
        private String canlink;
        private String curdate;
        private int lawflag; //3:预警记录
        private String bankname; //支行
        private String consigndate; //委托日期
        private int overnum=-1;
        
        
        public int getOvernum() {
			return overnum;
		}
		public void setOvernum(int overnum) {
			this.overnum = overnum;
		}
        public String getConsigndate() {
			return consigndate;
		}
		public void setConsigndate(String consigndate) {
			this.consigndate = consigndate;
		}
		public String getBankname() {
			return bankname;
		}
		public void setBankname(String bankname) {
			this.bankname = bankname;
		}
		public int getLawflag() {
			return lawflag;
		}
		public void setLawflag(int lawflag) {
			this.lawflag = lawflag;
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
	
		public MynonlawtaskListAction() {
          rights="nlw3,1";
        }
        public String go() throws HibernateException {
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
                		
            	 }
             }
             //催收中
             criteria.add(Expression.eq("taskstat", 0));    
             
             Criteria c2=criteria.createCriteria("tnlwNonlaw");
             //显示的不是全清的
             c2.add(Expression.ne("repaystatus", 2));   
             
             if(overnum==0)
            	 c2.add(Expression.lt("curoverstat", 3));
             else if(overnum==3)
            	 c2.add(Expression.eq("curoverstat", 3));
             else if(overnum==4)
            	 c2.add(Expression.gt("curoverstat", 3));
             
             if(lawflag==3)
            	 c2.add(Expression.eq("lawflag", lawflag));   
             if (bankid != 0)
     			c2.add(Expression.eq("bankid", bankid));        		
             
             if (consigndate!= null&&!"".equals(consigndate))
            	 c2.add(Expression.eq("consigndate", consigndate));    
     		if (username!= null&&!"".equals(username))
     			c2.add(Expression.like("username", username,MatchMode.ANYWHERE));
     		if (bankname!= null&&!"".equals(bankname))
     			c2.add(Expression.like("bankname", bankname,MatchMode.ANYWHERE));
     		
     		if (idcard!= null&&!"".equals(idcard))
         		c2.add(Expression.like("idcard", idcard,MatchMode.ANYWHERE));
     		
     		c2.add(Expression.eq("tdflag", 0));  //未退单的
     		
     		c2.addOrder(Order.desc("nonlawid"));
     		
     		tasklist = page(c2);//c2.list();//page(c2);
     		return SUCCESS;
        }

        public List getTasklist() {
          return tasklist;
        }
}
