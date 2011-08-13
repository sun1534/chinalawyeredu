package com.changpeng.operation.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.*;
import com.changpeng.operation.model.ToprCredittask;

import org.hibernate.Query;
import java.util.List;


/**
 * 约定还款周期
 * @author sinhoo
 * Sep 2, 2009
 */

public class SetPaydateAction extends AbstractAction {
	private long credittaskid;
	private String paydate;
	
	
	

	public long getCredittaskid() {
		return credittaskid;
	}
	public String getPaydate() {
		return paydate;
	}
	public void setCredittaskid(long credittaskid) {
		this.credittaskid = credittaskid;
	}
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	public String go() throws HibernateException {

		getSession().createSQLQuery("update topr_credittask set paydate='"+paydate+"' where credittaskid="+credittaskid).executeUpdate();
		this.message="提交成功.";
		return SUCCESS;
	}
	public String input(){
		
		return INPUT;
	}
}
