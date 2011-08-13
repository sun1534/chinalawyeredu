package com.changpeng.nonlaw.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.*;
import com.changpeng.operation.model.ToprCredittask;

import org.hibernate.Query;
import java.util.List;

/**
 * 设置任务联系人是否能联系上 其中canlink为2时表示联系不上
 * @author sinhoo
 * Sep 3, 2009
 */

public class TaskcanlinkAction extends AbstractAction {
	private long nonlawtaskid;
	private String canlink;
	
	private boolean result; //结果
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
	public long getNonlawtaskid() {
		return nonlawtaskid;
	}

	public void setNonlawtaskid(long nonlawtaskid) {
		this.nonlawtaskid = nonlawtaskid;
	}

	public String getCanlink() {
		return canlink;
	}

	public void setCanlink(String canlink) {
		this.canlink = canlink;
	}

	public String go() throws HibernateException {
		LOG.info("nonlawtaskid:"+nonlawtaskid);
		LOG.info("canlink:"+canlink);
		getSession().createSQLQuery("update tnlw_nonlawtask set canlink='"+canlink+"' where nonlawtaskid="+nonlawtaskid).executeUpdate();
		//this.message="提交成功.";
		result=true;
		return SUCCESS;
	}

}
