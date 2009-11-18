/**
 * SysLoginLogService.java
 */

package com.sxit.log.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.sxit.common.BasicService;
import com.sxit.common.PaginationSupport;
import com.sxit.common.exception.ServiceException;
import com.sxit.log.dao.SysLoginLogDAO;
import com.sxit.models.log.SysLoginLog;

/**
 * 登录日志情况
 * 
 * @author 华锋 2008-2-26 下午02:41:25
 * 
 */
public class SysLoginLogService extends BasicService{

	private static Log LOG = LogFactory.getLog(SysLoginLogService.class);

	private SysLoginLogDAO sysLoginLogDAO;
	private PlatformTransactionManager transactionManager;

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setSysLoginLogDAO(SysLoginLogDAO dao) {
		this.sysLoginLogDAO = dao;
	}


	public SysLoginLogDAO getSysLoginLogDAO() {
		return sysLoginLogDAO;
	}

	/**
	 * 更新注销退出信息
	 * 
	 * @param loginid
	 * @param remarks
	 * @throws ServiceException
	 */
	public void updateLogoutInfo(int loginid, String remarks) throws ServiceException {
		try {
			SysLoginLog log = (SysLoginLog) sysLoginLogDAO.get(SysLoginLog.class, loginid);
		
			LOG.debug("log========="+log);
			Timestamp loginTime = log.getLoginTime();
			long now = System.currentTimeMillis();
			long login = loginTime.getTime();
			log.setLogoutTime(new java.sql.Timestamp(now));
			log.setRemarks(remarks);
			log.setInSysTime((int) (now - login) / 1000);
			sysLoginLogDAO.update(log);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 容器关闭导致会话失效
	 * 
	 * @param contextid
	 * @throws ServiceException
	 */
	public void updateLogoutInfo(final String contextid) throws ServiceException {
		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					List list = sysLoginLogDAO
							.find(
									"from com.sxit.models.log.SysLoginLog login where login.contextid=? and (login.logoutTime is null or login.logoutTime='')",
									contextid);
					long now = System.currentTimeMillis();
					for (int i = 0; i < list.size(); i++) {
						SysLoginLog loginlog = (SysLoginLog) list.get(i);
						long login = loginlog.getLoginTime().getTime();
						loginlog.setLogoutTime(new java.sql.Timestamp(now));
						loginlog.setRemarks("WEB容器关闭导致退出");
						loginlog.setInSysTime((int) (now - login) / 1000);
						sysLoginLogDAO.update(loginlog);
					}
				}
			});
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private int loginid;

	public int getLoginId() {
		return this.loginid;
	}

	/**
	 * 这是一个事务处理的过程,将之前的islast都设置为0,新增登录信息
	 * 
	 * @param _loginip
	 * @param _userid
	 * @throws ServiceException
	 */
	public void insertLoginLog(final String _loginip, final int _userid,final String _username,final String _contextid,final String remarks) throws ServiceException {

		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					// 将之前的islast消息都设置为false
					sysLoginLogDAO.updateIsLast2False(_userid);
					// 插入新的数据
					SysLoginLog log = new SysLoginLog();
					log.setIslast(true);
					log.setLoginip(_loginip);
					log.setLoginTime(new java.sql.Timestamp(System.currentTimeMillis()));
					log.setUserid(_userid);
					log.setContextid(_contextid);
					log.setLoginremarks(remarks);
					log.setUsername(_username);
					sysLoginLogDAO.save(log);
					loginid = log.getLoginid();
					LOG.debug("登录的LOGINID=" + loginid);
				}
			});
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public int getLoginCountByUserId(int userid) throws ServiceException {
		try {
			return sysLoginLogDAO.getLoginCountByUserId(userid);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 得到最近的一次登录时间
	 * 
	 * @param userid
	 * @return
	 */
	public Timestamp getLastLoginTime(int userid) throws ServiceException {
		try {
			return sysLoginLogDAO.getLastLoginTime(userid);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据条件得到分页
	 * @param detachedCriteria
	 * @param pageSize
	 * @param pageNo
	 * @return
	 * @throws ServiceException
	 */
	public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize, final int pageNo)
			throws ServiceException {
		try {
			return sysLoginLogDAO.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		}
		catch (Exception e) {

			throw new ServiceException(e);					
		}
	}

}