package com.sxit.log.action;

import com.sxit.common.action.AbstractListAction;
import com.sxit.log.service.SysLogService;
import com.sxit.models.log.SysLog;

public class SysOperLogBatchDeleteAction extends AbstractListAction{
	private Integer[] checks;

	@Override
	protected String go() throws Exception {
		SysLogService service = (SysLogService)this.getBean("sysLogService");
		if(null != checks && checks.length > 0){
			for(int i = 0; i < checks.length; i ++){
//				SysLog sysLog = (SysLog)service.getSysLogDAO().get(checks[i]);
				SysLog sysLog = (SysLog)basicService.get(SysLog.class,checks[i]);
				service.delete(sysLog);
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
