package com.changpeng.nonlaw.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.*;

/**
 * 约定还款周期
 * @author sinhoo
 * Sep 2, 2009
 */

public class SetPaydateAction extends AbstractAction {
	private long nonlawtaskid;
	private String paydate;
	
	public long getNonlawtaskid() {
		return nonlawtaskid;
	}
	public String getPaydate() {
		return paydate;
	}
	public void setNonlawtaskid(long nonlawtaskid) {
		this.nonlawtaskid = nonlawtaskid;
	}
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	public String go() throws HibernateException {
		getSession().createSQLQuery("update tnlw_nonlawtask set paydate='"+paydate+"' where nonlawtaskid="+nonlawtaskid).executeUpdate();
        this.message="提交成功.";
		return SUCCESS;
	}
	public String input(){
		
		return INPUT;
	}
}
