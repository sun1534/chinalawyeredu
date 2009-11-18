package com.sxit.log.action;

import com.sxit.common.action.AbstractListAction;
import com.sxit.log.service.SysLoginLogService;
import com.sxit.models.log.SysLoginLog;

public class SysLoginLogBatchDeleteAction extends AbstractListAction{
	private Integer[] checks;
	
	protected String go() throws Exception{
		SysLoginLogService service = (SysLoginLogService)this.getBean("sysLoginLogService");
		if(null != checks && checks.length > 0){
			for(int i = 0; i < checks.length; i ++){
				SysLoginLog loginLog = (SysLoginLog)service.getSysLoginLogDAO().get(checks[i]);
				service.delete(loginLog);
			}
		}
		return SUCCESS;
	}

	public Integer[] getChecks() {
		return checks;
	}

	public void setChecks(Integer[] checks) {
		this.checks = checks;
	}
}
