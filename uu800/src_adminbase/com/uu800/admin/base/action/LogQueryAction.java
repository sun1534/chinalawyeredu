package com.uu800.admin.base.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.TsysDictvalue;
import com.uu800.admin.base.entity.TsysLogs;
import com.uu800.admin.base.service.SysLogService;


public class LogQueryAction extends AbstractAdminAction 
{

	private SysLogService sysLogService;
	private String opDateBegin;
	private String opDateEnd;
	private String loginName;
	private String userType;
	private String logType;
	private String corpCode;
	private long logid;
	
	// 操作类型 Log_Type
	private List<TsysDictvalue> list;
	private List<TsysLogs> tsysLogs;
	private TsysLogs tsysLog;
	
	// 操作类型
	private Map logTypeMap = new HashMap();
	
	// 功能名称
	private Map rightCodeMap = new HashMap();
	
	// 模块名称
	private Map modelCodeMap = new HashMap();
	
	
	
	public LogQueryAction()
	{
		sysLogService = (SysLogService)getBean("sysLogService");

		
	}
	
	@Override
	public String execute() 
	{
		String areacode = null;
		if(getUserinfo().getUsertype()>1)
		{
			areacode = getUserinfo().getAreacode();
		}
		page = sysLogService.findTsysLogs(opDateBegin, opDateEnd, loginName, userType, logType, corpCode, areacode, pageSize,pageNo);
		tsysLogs = page.getItems();
        return SUCCESS;
	}

	public String getOpDateBegin() {
		return opDateBegin;
	}

	public void setOpDateBegin(String opDateBegin) {
		this.opDateBegin = opDateBegin;
	}

	public String getOpDateEnd() {
		return opDateEnd;
	}

	public void setOpDateEnd(String opDateEnd) {
		this.opDateEnd = opDateEnd;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getCorpCode() {
		return corpCode;
	}

	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}

	public long getLogid() {
		return logid;
	}

	public void setLogid(long logid) {
		this.logid = logid;
	}

	public Map getLogTypeMap() {
		return logTypeMap;
	}

	public void setLogTypeMap(Map logTypeMap) {
		this.logTypeMap = logTypeMap;
	}

	public Map getRightCodeMap() {
		return rightCodeMap;
	}

	public void setRightCodeMap(Map rightCodeMap) {
		this.rightCodeMap = rightCodeMap;
	}

	public Map getModelCodeMap() {
		return modelCodeMap;
	}

	public void setModelCodeMap(Map modelCodeMap) {
		this.modelCodeMap = modelCodeMap;
	}

	public List<TsysLogs> getTsysLogs() {
		return tsysLogs;
	}

	public void setTsysLogs(List<TsysLogs> tsysLogs) {
		this.tsysLogs = tsysLogs;
	}

	public TsysLogs getTsysLog() {
		return tsysLog;
	}

	public void setTsysLog(TsysLogs tsysLog) {
		this.tsysLog = tsysLog;
	}
	

}
