package com.changpeng.operation.action;

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
import com.changpeng.operation.model.ToprCredittask;
import com.sxit.common.action.AbstractListAction;
import com.sxit.system.model.*;
/**
 * 任务重新分配
 * @author sinhoo
 * Aug 4, 2009
 */
public class ReassignTaskAction extends AbstractListAction  {
        private long creditcardid;
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
		
		

		public long getCreditcardid() {
			return creditcardid;
		}

		public void setCreditcardid(long creditcardid) {
			this.creditcardid = creditcardid;
		}

		public ReassignTaskAction() {
          //rights="nlw2,1";
        }
        public String go() throws HibernateException {
        	ToprCredittask task=null;
        	List list=getSession().createQuery(" from ToprCredittask where taskstat=0 and toprCreditcard.creditcardid="+creditcardid+"").list();
        	//System.out.println("unassign::::"+unassign);
        	if(list!=null&&list.size()>0)
        		task=(ToprCredittask)list.get(0);
        	if(unassign==1){
        		task.setTaskstat(1);
        		getSession().update(task);
        		//更新记录状态
        		getSession().createSQLQuery("update topr_creditcard set state=0 where creditcardid="+creditcardid+"").executeUpdate();
        		this.message="撤销任务成功.";
        	}else{
        		System.out.println("task.getUserid():::"+task.getUserid()+"    userid::::"+userid);
        		if(task.getUserid()!=userid){
        			task.setTaskstat(1);
            		getSession().update(task);

            		//插入用户任务表
            		getSession().createSQLQuery("insert into topr_credittask (credittaskid,creditcardid,userid,createtime,taskstat) values (ToprCredittaskid.nextval,"+creditcardid+","+userid+",sysdate,0)").executeUpdate();
    				//更新记录状态
            		getSession().createSQLQuery("update topr_creditcard set state=1 where creditcardid="+creditcardid+"").executeUpdate();
    				//将对应的客户信息记录同步到该用户通讯录
            		getSession().createSQLQuery("update tusr_address set userid="+userid+" where oprid="+creditcardid+" and oprflag=1").executeUpdate();
            		
            		//将对应的日志记录更改到新接单人
            		getSession().createSQLQuery("update topr_creditlog set userid="+userid+" where creditcardid="+creditcardid+"").executeUpdate();
            		
            		
        		}
        		this.message="任务重新分配成功.";
        	}
            return SUCCESS;
        }
        public String input(){
        	//信用卡催收角色
        	TsysRole role=(TsysRole)getSession().get(TsysRole.class, 1);
        	if(role!=null)
        		roleusers=role.getTsysUserRoles();
        	return INPUT;
        }
}
