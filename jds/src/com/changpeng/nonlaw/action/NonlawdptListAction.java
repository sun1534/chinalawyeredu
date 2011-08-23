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
 * <p>功能： 部门催收记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-10-12</p>
 * @版本： V1.0
 * @修改：
 */

public class NonlawdptListAction extends AbstractListAction  {
        private List nonlawlist;
        private long bankid;
        private String username;
        private String idcard;
        private int state=-1;
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
		public NonlawdptListAction() {
          rights="nlw1,1";
        }
        public String go() throws HibernateException {
      		nonlawlist  = getQuery()
            .setMaxResults(maxperpage)
            .setFirstResult(maxperpage * pagenumber)
           // .setCacheable(true)
            .list();
             
                return SUCCESS;
        }
        private Query getQuery() throws HibernateException {
        	
            String queryName="from TnlwNonlaw a,TnlwNonlawtask b,TsysUser c where a.repaystatus<>2 and a.nonlawid=b.tnlwNonlaw.nonlawid and b.userid=c.userid";
            
            if(curuser.getTsysDepartment()!=null)
            	queryName+=" and c.tsysDepartment.departmentid="+curuser.getTsysDepartment().getDepartmentid();
            
            if (bankid != 0)
            	queryName+=" and a.bankid="+bankid;
      
      		if (username!= null&&!"".equals(username))
      			queryName+=" and a.username like '%"+username+"%'";
      		if (idcard!= null&&!"".equals(idcard))
      			queryName+=" and a.idcard like '%"+idcard+"%'";
      		if (state != -1)
      			queryName+=" and a.state="+state; 
      		
      	//未退单的
    		queryName+=" and a.tdflag=0";  
    		 basicDao.setSession(getSession());
    		 
    		 System.out.println("非诉任务:"+queryName);
    		 
            recordsize=basicDao.getCountOfQuery(queryName);
    		queryName="select a "+queryName+" order by a.nonlawid desc";
            Query query = getSession().createQuery(queryName);
//            recordsize = query.list().size();
            pagesize = (recordsize - 1) / maxperpage + 1;
            pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
            
            return query;
    }
        public List getNonlawlist() {
          return nonlawlist;
        }
}
