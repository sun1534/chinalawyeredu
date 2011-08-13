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
 * <p>功能： 列表催收记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-18</p>
 * @版本： V1.0
 * @修改：
 */

public class NonlawListAction extends AbstractListAction  {
        private List nonlawlist;
        private long bankid;
        private String username;
        private String idcard;
        private int state=-1;
        private int lawflag; //3:预警记录
        private int repaystatus=1;//-1全部1:部分还款 2:全清，去掉不等于全清的
        private String bankname;
        private String consigndate;
        private String chengbanren;
        
        private String begindate;
        private String enddate;
        private int overnum=-1;
        
        public int getOvernum() {
			return overnum;
		}
		public void setOvernum(int overnum) {
			this.overnum = overnum;
		}
        
        public String getBegindate() {
			return begindate;
		}
		public String getEnddate() {
			return enddate;
		}
		public void setBegindate(String begindate) {
			this.begindate = begindate;
		}
		public void setEnddate(String enddate) {
			this.enddate = enddate;
		}
		public String getBankname() {
			return bankname;
		}
		public String getConsigndate() {
			return consigndate;
		}
		public String getChengbanren() {
			return chengbanren;
		}
		public void setBankname(String bankname) {
			this.bankname = bankname;
		}
		public void setConsigndate(String consigndate) {
			this.consigndate = consigndate;
		}
		public void setChengbanren(String chengbanren) {
			this.chengbanren = chengbanren;
		}
		public int getLawflag() {
			return lawflag;
		}
		public void setLawflag(int lawflag) {
			this.lawflag = lawflag;
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
		public int getState() {
			return state;
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
		public void setState(int state) {
			this.state = state;
		}
		public NonlawListAction() {
          rights="nlw1,1";
        }
        public String go() throws HibernateException {
        	/*  Criteria criteria = getSession().createCriteria(TnlwNonlaw.class);
      		
        	  criteria.add(Expression.ne("repaystatus", 2));   
        	  
        	  if(lawflag==3)
        		  criteria.add(Expression.eq("lawflag", lawflag));  
        	  if (bankid != 0)
      			criteria.add(Expression.eq("bankid", bankid));        		
      
      		if (username!= null&&!"".equals(username))
      			criteria.add(Expression.like("username", username,MatchMode.ANYWHERE));
      		if (idcard!= null&&!"".equals(idcard))
          		criteria.add(Expression.like("idcard", idcard,MatchMode.ANYWHERE));
      		if (state != -1)
      			criteria.add(Expression.eq("state", state));  
      		
      		if (bankname!= null&&!"".equals(bankname))
      			criteria.add(Expression.eq("bankname", bankname));  
      		if (consigndate!= null&&!"".equals(consigndate))
      			criteria.add(Expression.eq("consigndate", consigndate)); 
      		nonlawlist =page(criteria);*/
        	nonlawlist=getQuery().setMaxResults(maxperpage)
            .setFirstResult(maxperpage * pagenumber).list();
            return SUCCESS;
        }
        private Query getQuery() throws HibernateException {
        	
            /*String queryName="from TnlwNonlaw a where 1=1  and a.repaystatus<>2";
            if(chengbanren!=null&&!"".equals(chengbanren)){
    			queryName="select a from TnlwNonlaw a,TnlwNonlawtask b,TsysUser c where a.repaystatus<>2 and a.nonlawid=b.tnlwNonlaw.nonlawid and b.userid=c.userid and c.username like '%"+chengbanren.trim()+"%'  and b.taskstat=0";
    		}*/
        	
        	//显示全清记录
        	String queryName="from TnlwNonlaw a where 1=1";
            if(chengbanren!=null&&!"".equals(chengbanren)){
    			queryName="select a from TnlwNonlaw a,TnlwNonlawtask b,TsysUser c where a.nonlawid=b.tnlwNonlaw.nonlawid and b.userid=c.userid and c.username like '%"+chengbanren.trim()+"%'  and b.taskstat=0";
    		}
            
            if(overnum==0)
            	queryName+=" and a.curoverstat<3";
            else if(overnum==3)
            	queryName+=" and a.curoverstat=3";
            else if(overnum==4)
            	queryName+=" and a.curoverstat>3";
            if(repaystatus!=-1)
            	queryName+=" and a.repaystatus="+repaystatus;
            if(lawflag==3)
            	queryName+=" and a.lawflag="+lawflag;
      	  	if (bankid != 0)
      		  queryName+=" and a.bankid="+bankid;      		
    		if (username!= null&&!"".equals(username))
    			queryName+=" and a.username like '%"+username+"%'";  
    		if (idcard!= null&&!"".equals(idcard))
    			queryName+=" and a.idcard like '%"+idcard+"%'";  
    		if (state != -1)
    			queryName+=" and a.state="+state;   
    		
    		if (bankname!= null&&!"".equals(bankname))
    			queryName+=" and a.bankname like '%"+bankname+"%'"; 
    		if (consigndate!= null&&!"".equals(consigndate))
    			queryName+=" and a.consigndate='"+consigndate+"'";   
    		
    		//委托日期从begindate到enddate
    		if (begindate!= null&&!"".equals(begindate)&&enddate!= null&&!"".equals(enddate))
    			queryName+=" and a.consigndate>='"+begindate+"' and a.consigndate<='"+enddate+"'";   
    		
    		//queryName+=" order by to_number(a.lendfee) desc,a.nonlawid desc";
    		
    		//未退单的
    		queryName+=" and a.tdflag=0";  
    		
    		queryName+=" order by a.nonlawid desc";
    		Query query = getSession().createQuery(queryName);
            recordsize = query.list().size();
            pagesize = (recordsize - 1) / maxperpage + 1;
            pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
            
            return query;
    }
        public List getNonlawlist() {
          return nonlawlist;
        }
		/**
		 * @return the repaystatus
		 */
		public int getRepaystatus() {
			return repaystatus;
		}
		/**
		 * @param repaystatus the repaystatus to set
		 */
		public void setRepaystatus(int repaystatus) {
			this.repaystatus = repaystatus;
		}
}
