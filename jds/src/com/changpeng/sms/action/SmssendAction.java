package com.changpeng.sms.action;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.sms.model.*;
import com.sxit.common.action.AbstractAction;

public class SmssendAction extends AbstractAction {
	private SmsLog smsLog;
	public SmssendAction(){
		smsLog=new SmsLog();
	}
	public SmsLog getSmsLog() {
		return smsLog;
	}

	public void setSmsLog(SmsLog smsLog) {
		this.smsLog = smsLog;
	}

	@Override
	protected String go() throws JDBCException, HibernateException {
		if(smsLog.getSmscontent().length()>70){
			this.message="短信内容过长，短信只允许70个字。";
			return ERROR;
		}else if(!smsLog.getMobile().matches("^1(3|5|8)\\d{9}")){
			this.message="请输入正确的手机号码。";
			return ERROR;
		}
		else{
			smsLog.setCreatetime(new java.util.Date());
			smsLog.setCreateuser(curuser.getUserid());
			getSession().save(smsLog);
			
			SmsQueue smsQueue=new SmsQueue();
			smsQueue.setSmslogid(smsLog.getSmslogid());
			smsQueue.setMobile(smsLog.getMobile());
			smsQueue.setSmscontent(smsLog.getSmscontent());
			smsQueue.setCreatetime(smsLog.getCreatetime());
			smsQueue.setCreateuser(smsLog.getCreateuser());
			getSession().save(smsQueue);			
			this.message="短信提交成功.";
		}		
		return SUCCESS;
	}	
	public String input(){
		return INPUT;
	}
}
