package com.changpeng.operation.action;

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
	private long credittaskid;
	private String canlink;
	private boolean result; //结果
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
	

	public long getCredittaskid() {
		return credittaskid;
	}
	
	public void setCredittaskid(long credittaskid) {
		this.credittaskid = credittaskid;
	}
	
	public String getCanlink() {
		return canlink;
	}

	public void setCanlink(String canlink) {
		this.canlink = canlink;
	}

	public String go() throws HibernateException {

		getSession().createSQLQuery("update topr_credittask set canlink='"+canlink+"' where credittaskid="+credittaskid).executeUpdate();
		result=true;
		return SUCCESS;
	}
	public String input(){
		
		return INPUT;
	}
}
