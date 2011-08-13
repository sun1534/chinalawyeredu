package com.changpeng.nonlaw.action;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;

import com.changpeng.nonlaw.model.TnlwNonlaw;
import com.changpeng.operation.model.ToprCreditcard;
import com.sxit.common.action.AbstractListAction;
import com.sxit.system.model.*;


/**
 * 列表待催收记录并选择记录进行任务分配
 * @author sinhoo
 * Jun 11, 2009
 */

public class NonlawtaskListAction extends AbstractListAction  {
        private List nonlawlist;
        private long bankid;
        private String username;
        private String idcard;
        private Set roleusers;
        
        private String bankname;
        private String homeaddr;
        private String lendfee;
        
        private int state;
        private String chengbanren;
        
        public int getState() {
			return state;
		}
		public String getChengbanren() {
			return chengbanren;
		}
		public void setState(int state) {
			this.state = state;
		}
		public void setChengbanren(String chengbanren) {
			this.chengbanren = chengbanren;
		}
		public String getBankname() {
			return bankname;
		}
		public String getHomeaddr() {
			return homeaddr;
		}
		public String getLendfee() {
			return lendfee;
		}
		public void setBankname(String bankname) {
			this.bankname = bankname;
		}
		public void setHomeaddr(String homeaddr) {
			this.homeaddr = homeaddr;
		}
		public void setLendfee(String lendfee) {
			this.lendfee = lendfee;
		}
		public Set getRoleusers() {
            return roleusers;
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

		public NonlawtaskListAction() {
          rights="nlw2,1";
        }
        public String go() throws HibernateException {
        	//非诉催收角色
        	TsysRole role=(TsysRole)getSession().get(TsysRole.class, 2);
        	if(role!=null)
        		roleusers=role.getTsysUserRoles();
        //	userlist=getSession().createQuery(" from TsysUser where statusid=1 order by userid").list();
        	
        	/* Criteria criteria = getSession().createCriteria(TnlwNonlaw.class);
      		if (bankid != 0)
      			criteria.add(Expression.eq("bankid", bankid));        		
      
      		if (username!= null&&!"".equals(username))
      			criteria.add(Expression.like("username", username,MatchMode.ANYWHERE));
      		if (idcard!= null&&!"".equals(idcard))
          		criteria.add(Expression.like("idcard", idcard,MatchMode.ANYWHERE));
      		if (bankname!= null&&!"".equals(bankname))
          		criteria.add(Expression.like("bankname", bankname,MatchMode.ANYWHERE));
      		if (homeaddr!= null&&!"".equals(homeaddr))
          		criteria.add(Expression.like("homeaddr", homeaddr,MatchMode.ANYWHERE));
      		if (lendfee!= null&&!"".equals(lendfee))
          		criteria.add(Expression.eq("lendfee", lendfee));
      		
      		criteria.add(Expression.eq("state", 0));   
      		nonlawlist = page(criteria);*/
        	nonlawlist=getQuery().setMaxResults(maxperpage)
            .setFirstResult(maxperpage * pagenumber)
            .setCacheable(true)
            .list();
                return SUCCESS;
        }
        public String export() throws HibernateException {
        	nonlawlist=getQuery().list();
            return "export";
        }
        private Query getQuery() throws HibernateException {
            String queryName ;
    		if(chengbanren!=null&&!"".equals(chengbanren)){	
    			queryName="select a from TnlwNonlaw a,TnlwNonlawtask b,TsysUser c where a.tdflag=0 and a.repaystatus<>2  and a.nonlawid=b.tnlwNonlaw.nonlawid and b.userid=c.userid and c.username like '%"+chengbanren+"%'  and b.taskstat=0";   	
    		}else{
    			queryName="from TnlwNonlaw as nonlaw where 1=1 and repaystatus<>2 ";
                if (state != -1)
                	queryName+=" and state="+state;
                if (bankid != 0)
                	queryName+=" and bankid="+bankid;        		  
        		if (username!= null&&!"".equals(username))
        			queryName+=" and username like '%"+username+"%'"; 
        		if (idcard!= null&&!"".equals(idcard))
        			queryName+=" and idcard like '%"+idcard+"%'"; 
        		if (bankname!= null&&!"".equals(bankname))
        			queryName+=" and bankname like '%"+bankname+"%'"; 
        		if (homeaddr!= null&&!"".equals(homeaddr))
        			queryName+=" and homeaddr like '%"+homeaddr+"%'"; 
        		if (lendfee!= null&&!"".equals(lendfee))
        			queryName+=" and lendfee='"+lendfee+"'"; 
        		
        		//未退单的
        		queryName+=" and tdflag=0";  
        		
        		queryName+=" order by nonlawid";
        		
        		
    		}
    		
    		Query query = getSession().createQuery(queryName);
            
            
            
            recordsize = query.list().size();
            pagesize = (recordsize - 1) / maxperpage + 1;
            pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
            return query;
        }
        public List getNonlawlist() {
          return nonlawlist;
        }
}
