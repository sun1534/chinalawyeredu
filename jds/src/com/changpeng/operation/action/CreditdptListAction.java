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
 *
 * <p>功能： 部门催收记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-09</p>
 * @版本： V1.0
 * @修改：
 */

public class CreditdptListAction extends AbstractListAction  {
        private List creditcardlist;
        private long bankid;
        private String consigntype;
        private String consignflag;
        private String username;
        private String creditcard;
        private int state=-1;
        private String consigndate; //委托日期
        private String chengbanren; //承办人
        
        public String getConsigndate() {
			return consigndate;
		}
		public String getChengbanren() {
			return chengbanren;
		}
		public void setConsigndate(String consigndate) {
			this.consigndate = consigndate;
		}
		public void setChengbanren(String chengbanren) {
			this.chengbanren = chengbanren;
		}
		public CreditdptListAction() {
          rights="opr7,1";
        }
        public String go() throws HibernateException {
                creditcardlist = getQuery()
                          .setMaxResults(maxperpage)
                        .setFirstResult(maxperpage * pagenumber)
                       // .setCacheable(true)
                        .list();
          
        		
                return SUCCESS;
        }
   
        private Query getQuery() throws HibernateException {
        	
                String queryName="select a from ToprCreditcard a,ToprCredittask b,TsysUser c where a.repaystatus<>2 and a.creditcardid=b.toprCreditcard.creditcardid and b.userid=c.userid and b.taskstat=0";
                
                
                if(!curuser.isIsleader()&&curuser.getTsysDepartment()!=null)
                	queryName+=" and c.tsysDepartment.departmentid="+curuser.getTsysDepartment().getDepartmentid();
                
                if(chengbanren!=null&&!"".equals(chengbanren))
        			queryName+=" and c.username like '%"+chengbanren.trim()+"%'";
          
                if (bankid != 0)
                	queryName+=" and a.bankid="+bankid;	
        		if (consigntype!= null&&!"".equals(consigntype))
        			queryName+=" and a.consigntype='"+consigntype+"'";
        		if (consignflag!= null&&!"".equals(consignflag))
        			queryName+=" and a.consignflag='"+consignflag+"'";
        		if (consigndate!= null&&!"".equals(consigndate))
        			queryName+=" and a.consigndate='"+consigndate+"'";
        		if (username!= null&&!"".equals(username))
        			queryName+=" and a.username like '%"+username+"%'";
        		if (creditcard!= null&&!"".equals(creditcard))
        			queryName+=" and a.creditcard like '%"+creditcard+"%'";
        		if (state != -1)
        			queryName+=" and a.state="+state;
        		
        		//未退单的
        		queryName+=" and a.tdflag=0";
        		
        		queryName+=" order by to_number(a.curcnfee) desc,a.creditcardid desc";
                Query query = getSession().createQuery(queryName);
                recordsize = query.list().size();
                pagesize = (recordsize - 1) / maxperpage + 1;
                pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
                
                return query;
        }
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
