/**
 * SysLoginLogService.java
 */

package com.changpeng.system.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.BasicService;
import com.changpeng.common.CommonDatas;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.SysLoginlog;
import com.changpeng.models.SysUser;
import com.changpeng.system.dao.SysLoginLogDAO;

/**
 * 登录日志情况
 * 
 * @author 华锋 2008-2-26 下午02:41:25
 * 
 */
public class SysLoginLogService extends BasicService {

	private static Log LOG = LogFactory.getLog(SysLoginLogService.class);

	private SysLoginLogDAO sysLoginLogDAO;

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
	public void updateLogoutInfo(int userId,int loginid, String remarks) throws ServiceException {
		try {
			SysLoginlog log = (SysLoginlog) sysLoginLogDAO.get(SysLoginlog.class, loginid);

			LOG.debug("log=========" + log);
			Timestamp loginTime = log.getLoginTime();
			long now = System.currentTimeMillis();
			long login = loginTime.getTime();
			log.setLogoutTime(new java.sql.Timestamp(now));
			log.setRemarks(remarks);
			log.setInSysTime((int) (now - login) / 1000);
			
			
			sysLoginLogDAO.update(log);
			//清除掉在线用户的信息
			CommonDatas.ONLINE_USERS.remove(userId);
		} catch (Exception e) {
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

			List list = sysLoginLogDAO
					.find(
							"from com.changpeng.models.SysLoginlog login where login.contextid=? and (login.logoutTime is null or login.logoutTime='')",
							contextid);
			long now = System.currentTimeMillis();
			for (int i = 0; i < list.size(); i++) {
				SysLoginlog loginlog = (SysLoginlog) list.get(i);
				long login = loginlog.getLoginTime().getTime();
				loginlog.setLogoutTime(new java.sql.Timestamp(now));
				loginlog.setRemarks("WEB容器关闭导致退出");
				loginlog.setInSysTime((int) (now - login) / 1000);
				sysLoginLogDAO.update(loginlog);
			}

		} catch (Exception e) {
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
	@Transactional
	public int insertLoginLog(final SysUser user,final String _loginip,  final String _contextid, final String remarks)
			throws ServiceException {

		try {
		
					// 将之前的islast消息都设置为false
					sysLoginLogDAO.updateIsLast2False(user.getUserid());
					// 插入新的数据
					SysLoginlog log = new SysLoginlog();
					log.setIslast(true);
					log.setLoginip(_loginip);
					log.setLoginTime(new java.sql.Timestamp(System.currentTimeMillis()));
					log.setUsername(user.getUsername());
					log.setOfficeid(user.getOfficeid());
					log.setProvinceid(user.getProvinceid());
					log.setCityid(user.getCityid());
					log.setUserid(user.getUserid());
					log.setContextid(_contextid);
					log.setLoginremarks(remarks);
					sysLoginLogDAO.save(log);
					loginid = log.getLoginid();
					LOG.debug("登录的LOGINID=" + loginid);
					/**
					 * 加入到在线列表中
					 */
					CommonDatas.ONLINE_USERS.put(user.getUserid(), log);
					
					return loginid;
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public int getLoginCountByUserId(int userid) throws ServiceException {
		try {
			return sysLoginLogDAO.getLoginCountByUserId(userid);
		} catch (Exception e) {
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
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}