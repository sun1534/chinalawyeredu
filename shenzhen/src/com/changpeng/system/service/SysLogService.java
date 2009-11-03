/**
 * SysLogService.java
 */

package com.changpeng.system.service;

import org.hibernate.criterion.DetachedCriteria;

import com.changpeng.common.PaginationSupport;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.system.SysLog;
import com.changpeng.system.dao.SysLogDAO;

/**
 * @author 华锋 2008-2-26 下午02:23:53
 * 
 */
public class SysLogService {

	private SysLogDAO sysLogDAO;

	public void setSysLogDAO(SysLogDAO dao) {
		this.sysLogDAO = dao;
	}

	/**
	 * 记录日志情况
	 * 
	 * @param loginid
	 * @param module
	 * @param opResult
	 * @param rightCode
	 * @param userid
	 * @throws ServiceException
	 */
	public void insertLog(int loginid, String module, String opResult, String rightCode, int userid) throws ServiceException {
		try {
			SysLog log = new SysLog();
			log.setLoginid(loginid);
			log.setModule(module);
			log.setOpResult(opResult);
			log.setOpTime(new java.sql.Timestamp(System.currentTimeMillis()));
			log.setRightCode(rightCode);
			log.setUserid(userid);
			sysLogDAO.save(log);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize, final int pageNo)
			throws ServiceException {
		try {
			return sysLogDAO.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		}
		catch (Exception e) {

			throw new ServiceException(e);
		}
	}

}