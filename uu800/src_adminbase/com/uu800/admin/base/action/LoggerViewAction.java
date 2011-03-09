package com.uu800.admin.base.action;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.TsysLogs;
import com.uu800.admin.base.service.SysLogService;

public class LoggerViewAction extends AbstractAdminAction 
{

	private SysLogService sysLogService;
	private TsysLogs tsysLog;
	private long logid;
	
	public LoggerViewAction()
	{
		sysLogService = (SysLogService)getBean("sysLogService");
	}
	
	@Override
	public String execute() 
	{
		tsysLog = sysLogService.getTsysLogs(logid);
	       return SUCCESS;
	}

	public TsysLogs getTsysLog() {
		return tsysLog;
	}

	public void setTsysLog(TsysLogs tsysLog) {
		this.tsysLog = tsysLog;
	}

	public long getLogid() {
		return logid;
	}

	public void setLogid(long logid) {
		this.logid = logid;
	}

}
