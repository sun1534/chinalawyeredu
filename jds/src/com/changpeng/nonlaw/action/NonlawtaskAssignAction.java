package com.changpeng.nonlaw.action;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;

import com.changpeng.nonlaw.util.NonlawUtil;
import com.changpeng.operation.model.ToprCreditcard;
import com.changpeng.operation.model.ToprCredittask;
import com.sxit.common.action.AbstractListAction;
import com.changpeng.operation.util.*;

/**
 * 列表待催收记录并选择记录进行任务分配
 * @author sinhoo
 * Jun 11, 2009
 */
public class NonlawtaskAssignAction extends AbstractListAction  {

        private long userid;
        private long[] check;
        public NonlawtaskAssignAction() {
          rights="nlw2,1";
        }
        public String go() throws HibernateException {
        	if(check==null||check.length<=0){
        		this.message="您需要选择催收记录才能进行任务分配。";
        		return ERROR;
        	}else{
        		try {
					NonlawUtil.consignTask(userid, check);
					this.message="任务分配成功。";
	            	this.nextpage="nonlawtaskList.action";
	            	return SUCCESS;
				} catch (SQLException e) {
					LOG.error(e);
					this.message="任务分配异常【"+e.getMessage()+"】";
	            	return ERROR;
				}
        	}       	
        }

		public void setUserid(long userid){
			this.userid=userid;
		}
		public void setCheck(long[] check){
			this.check=check;
		}
}
