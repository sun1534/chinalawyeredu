package com.sxit.log.action;

import com.sxit.common.action.AbstractListAction;
import com.sxit.log.service.SysLoginLogService;
import com.sxit.models.log.SysLoginLog;

public class SysLoginLogDeleteAction extends AbstractListAction{
	private Integer loginid;

	protected String go() throws Exception{
		SysLoginLogService service = (SysLoginLogService)this.getBean("sysLoginLogService");
		SysLoginLog loginLog = (SysLoginLog)service.getSysLoginLogDAO().get(loginid);
		service.delete(loginLog);
		return SUCCESS;
	}

	public Integer getLoginid() {
		return loginid;
	}

	public void setLoginid(Integer loginid) {
		this.loginid = loginid;
	}
}
