package com.changpeng.nonlaw.action;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;

import com.changpeng.nonlaw.model.TnlwNonlaw;
import com.changpeng.nonlaw.model.TnlwNonlawtask;
import com.changpeng.operation.model.ToprCreditcard;
import com.sxit.common.action.AbstractListAction;
import com.sxit.system.model.*;

/**
 * 任务重新分配
 * @author sinhoo
 * Aug 4, 2009
 */

public class ReassignTaskAction extends AbstractListAction  {
        private long nonlawid;
        private Set roleusers;
        private int unassign;
        private long userid;
        
        public long getUserid() {
			return userid;
		}

		public void setUserid(long userid) {
			this.userid = userid;
		}

		public int getUnassign() {
			return unassign;
		}

		public void setUnassign(int unassign) {
			this.unassign = unassign;
		}

		public Set getRoleusers() {
            return roleusers;
          }
		
		public long getNonlawid() {
			return nonlawid;
		}

		public void setNonlawid(long nonlawid) {
			this.nonlawid = nonlawid;
		}

		public ReassignTaskAction() {
          //rights="nlw2,1";
        }
        public String go() throws HibernateException {
        	TnlwNonlawtask task=null;
        	List list=getSession().createQuery(" from TnlwNonlawtask where taskstat=0 and tnlwNonlaw.nonlawid="+nonlawid+"").list();
        	if(list!=null&&list.size()>0)
        		task=(TnlwNonlawtask)list.get(0);
        	if(unassign==1){
        		task.setTaskstat(1);
        		getSession().update(task);
        		//更新记录状态
        		getSession().createSQLQuery("update tnlw_nonlaw set state=0 where nonlawid="+nonlawid+"").executeUpdate();
        		this.message="撤销任务成功.";
        	}else{
        		if(task.getUserid()!=userid){
        			task.setTaskstat(1);
            		getSession().update(task);

            		getSession().createSQLQuery("insert into tnlw_nonlawtask (nonlawtaskid,nonlawid,userid,createtime,taskstat) values (tnlwnonlawtaskid.nextval,"+nonlawid+","+userid+",sysdate,0)").executeUpdate();
    				//更新记录状态
            		getSession().createSQLQuery("update tnlw_nonlaw set state=1 where nonlawid="+nonlawid+"").executeUpdate();
    				//将对应的客户信息记录同步到该用户通讯录
            		getSession().createSQLQuery("update tusr_address set userid="+userid+" where oprid="+nonlawid+" and oprflag=2").executeUpdate();
            		
            		//将对应的日志记录更改到新接单人
            		getSession().createSQLQuery("update tnlw_nonlawlog set userid="+userid+" where nonlawid="+nonlawid+"").executeUpdate();
            		
        		}
        		this.message="任务重新分配成功.";
        	}
            return SUCCESS;
        }
        public String input(){
        	//非诉催收角色
        	TsysRole role=(TsysRole)getSession().get(TsysRole.class, 2);
        	if(role!=null)
        		roleusers=role.getTsysUserRoles();
        	return INPUT;
        }
}
