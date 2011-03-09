package com.uu800.admin.base.service;

import javax.annotation.Resource;

import com.uu800.webbase.BasicService;
import com.uu800.admin.base.dao.SysLogDAO;

public class Logger4DB extends BasicService 
{
	private SysLogDAO sysLogDAO;
//
//	
//
//	@Transactional(propagation=Propagation.REQUIRES_NEW)
//	public void add(Object target, OperationLogTag tag,int resultType,String memo)
//	{
//		if(target instanceof AbstractAction)
//		{
//			recordLog4Action(tag, resultType, memo);
//		}
//		else
//		{
//			recordLog4Service(tag, resultType, memo);
//		}
//	}
//	
//	/**
//	 * 从Action层上获取操作记录
//	 * @param tag
//	 * @param target
//	 */
//	private void recordLog4Action(OperationLogTag tag,int resultType,String memo)
//	{
//		
//	}
//	
//	/**
//	 * 从Service层上获取操作记录
//	 * @param tag
//	 * @param target
//	 */
//	private void recordLog4Service(OperationLogTag tag,int resultType,String memo)
//	{
//		TsysLogs log = new TsysLogs();
//		ISession session = SessionManager.getInstance().getSessionContext();
////		log.setCorpCode(session.getCorpCode());
////		log.setDescript(tag.descript());
////		log.setLogType(tag.actionType().name());
////		log.setModelCode(tag.moduleCode());
////		log.setOpDate(DateHelper.getCurrentDate(DateHelper.YEAR_MONTH_DAY_HH_MM_SS));
////		log.setOperateResult(resultType);
////	    log.setMemo(memo);
////		log.setOpIp(session.getUserIp());
////		log.setRightCode(tag.rightCode());
////		log.setUserId(session.getUserId());
////		log.setUserLoginname(session.getUserName());
////		log.setUserType(tag.portalCode().name());
//		sysLogDAO.addSysLog(log);
//	}
//
    @Resource
	public void setSysLogDAO(SysLogDAO sysLogDAO) {
		this.sysLogDAO = sysLogDAO;
		super.basicDao = sysLogDAO;
	}

}
