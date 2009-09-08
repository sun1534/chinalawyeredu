/**
 * SysLogService.java
 */

package com.changpeng.system.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicDAO;
import com.changpeng.common.BasicService;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.SysOperlog;
import com.changpeng.models.SysUser;

/**
 * @author 华锋 2008-2-26 下午02:23:53
 * 
 */
public class SysOperlogService extends BasicService{

	private BasicDAO basicDAO;

	private static Log _LOG = LogFactory.getLog(SysOperlogService.class);


	/**
	 * @param basicDAO the basicDAO to set
	 */
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
	public void insertLog(  SysUser user,String rightCode, String opResult ) throws ServiceException {
		try {
			SysOperlog log = new SysOperlog();
			log.setLoginid(user.getLoginId());

			log.setOpResult(opResult);
			log.setOpTime(new java.sql.Timestamp(System.currentTimeMillis()));
			log.setRightCode(rightCode);
			log.setUserid(user.getUserid());
			log.setUsername(user.getUsername());
			log.setOfficeid(user.getOfficeid());
			log.setProvinceid(user.getProvinceid());
			log.setCityid(user.getCityid());
			basicDAO.save(log);
		}
		catch (Exception e) {
			_LOG.error("日志插入失败::"+e);
		}
	}


}