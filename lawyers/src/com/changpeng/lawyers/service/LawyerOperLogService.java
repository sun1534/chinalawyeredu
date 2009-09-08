/**
 * SysLogService.java
 */

package com.changpeng.lawyers.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicDAO;
import com.changpeng.common.BasicService;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.LawyerOperlog;
import com.changpeng.models.Lawyers;

/**
 * @author 华锋 2008-2-26 下午02:23:53
 * 
 */
public class LawyerOperLogService extends BasicService{

	private BasicDAO basicDAO;

	/**
	 * @param basicDAO the basicDAO to set
	 */
	public void setBasicDAO(BasicDAO basicDAO) {
		this.basicDAO = basicDAO;
	}
	private static Log _LOG = LogFactory.getLog(LawyerOperLogService.class);
	

	/**
	 * 记录日志情况
	 * 
	 * @param loginid
	 * @param module
	 * @param opResult
	 * @param rightCode
	 * @param lawyerid
	 * @throws ServiceException
	 */
	public void insertLog( Lawyers lawyers,String opResult, String rightCode) throws ServiceException {
		try {
			LawyerOperlog log = new LawyerOperlog();
			log.setLoginid(lawyers.getLoginId());
	    	log.setLawyername(lawyers.getLawyername());
			log.setOpResult(opResult);
			log.setOpTime(new java.sql.Timestamp(System.currentTimeMillis()));
			log.setRightCode(rightCode);
			log.setLawyerid(lawyers.getLawyerid());
			log.setCityid(lawyers.getDirectunion());
			log.setProvinceid(lawyers.getProvinceunion());
			log.setOfficeid(lawyers.getTheoffice());
			
			basicDAO.save(log);
		}
		catch (Exception e) {
			_LOG.error("日志插入失败::"+e);
		}
	}



}