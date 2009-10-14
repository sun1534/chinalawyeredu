package com.sxit.log.action;

import com.sxit.common.action.AbstractListAction;
import com.sxit.log.service.SysLogService;
import com.sxit.models.log.SysLog;

public class SysOperLogDeleteAction extends AbstractListAction{
	private Integer logid;

	@Override
	protected String go() throws Exception {
		SysLogService service = (SysLogService)this.getBean("sysLogService");
		SysLog sysLog = (SysLog)basicService.get(SysLog.class,logid);
		service.delete(sysLog);
		return SUCCESS;
	}

	public Integer getLogid() {
		return logid;
	}

	public void setLogid(Integer logid) {
		this.logid = logid;
	}

}
