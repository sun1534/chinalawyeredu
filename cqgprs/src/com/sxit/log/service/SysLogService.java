/**
 * SysLogService.java
 */

package com.sxit.log.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;

import com.sxit.common.BasicDAO;
import com.sxit.common.BasicService;
import com.sxit.common.PaginationSupport;
import com.sxit.common.exception.ServiceException;
import com.sxit.models.log.SysLog;

/**
 * @author 华锋 2008-2-26 下午02:23:53
 * 
 */
public class SysLogService extends BasicService{

	private BasicDAO basicDAO;

	private static Log _LOG = LogFactory.getLog(SysLogService.class);


	public void setBasicDAO(BasicDAO basicDAO) {
		this.basicDAO = basicDAO;
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
	public void insertLog(int loginid, String opResult, String rightCode, int userid,String username) throws ServiceException {
		try {
			SysLog log = new SysLog();
			log.setLoginid(loginid);
//			log.setModule(module);
			log.setOpResult(opResult);
			log.setOpTime(new java.sql.Timestamp(System.currentTimeMillis()));
			log.setRightCode(rightCode);
			log.setUserid(userid);
			log.setUsername(username);
			basicDAO.save(log);
		}
		catch (Exception e) {
//			throw new ServiceException(e);
			_LOG.error("日志插入失败::"+e);
		}
	}

	public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize, final int pageNo)
			throws ServiceException {
		try {
			return basicDAO.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		}
		catch (Exception e) {

			throw new ServiceException(e);
		}
	}
}