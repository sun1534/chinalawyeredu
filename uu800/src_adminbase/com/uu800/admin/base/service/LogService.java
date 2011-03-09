/**
 * LoginService.java
 */
package com.uu800.admin.base.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.uu800.webbase.BasicService;
import com.uu800.admin.base.entity.Userinfo;
import com.uu800.admin.base.entity.LogLogin;
import com.uu800.admin.base.entity.LogOpt;

/**
 * 
 * 记录登录日志、操作日志等 先期的话，直接入库，后期考虑异步的方式或者额外一层比如ice的方式
 * 
 * @author 华锋 Jul 12, 2010 3:51:49 PM
 */
public class LogService extends BasicService implements ILogService {
	private static Log LOG = LogFactory.getLog(LogService.class);
	private static final DateFormat DF = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 批量插入
	 * 
	 * @param opts
	 */

	public void batchLogOpt(List<LogOpt> opts) {
		for (LogOpt opt : opts)
			super.save(opt);
	}

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.tixs.common.base.ILogService#logLogin(com.uu800.admin.base.entity.Userinfo,
	 *      int, java.lang.String)
	 */
	public int logLogin(Userinfo userinfo, String contextId, String loginIp, String remarks) {
		// TODO Auto-generated method stub
		try {
			LogLogin login = new LogLogin();

			login.setLoginIp(loginIp);
			login.setLoginName(userinfo.getLoginName());
			login.setLoginTime(new java.sql.Timestamp(System.currentTimeMillis()));
			login.setUserId((int) userinfo.getId());
			login.setLoginReamrks(remarks);
			// basicDao.save(login);
			return login.getId();
		} catch (Exception e) {
			LOG.warn("logLogout", e);
			return -1;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tixs.common.base.ILogService#logOpt(com.uu800.admin.base.entity.Userinfo,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public int logOpt(int userId, String loginName, int orgId, String moduleId, String url, String ip, String opResult,
			String result) {
		// TODO Auto-generated method stub
		try {
			LogOpt logopt = new LogOpt();
			logopt.setLoginName(loginName);
			logopt.setLogIp(ip);
			logopt.setLogMsg(opResult);
			logopt.setLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
			logopt.setModuleId(moduleId);
			logopt.setOrgId(orgId);
			logopt.setResult(result);
			logopt.setRightCode(url);
			logopt.setUserId(userId);
			// super.save(logopt);

			return logopt.getId();
		} catch (Exception e) {
			LOG.warn("logOpt", e);
			return -1;
		}
	}
/**
 * 
 * @param start
 * @param end
 * @param moduleId
 * @param loginName
 * @param pageNo
 * @param pageSize
 * @return
 */
	public com.uu800.webbase.PageSupport getOptLogs(Timestamp start, Timestamp end, String moduleId,
			String loginName, int pageNo, int pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(LogOpt.class);
		if (start != null)
			dc.add(Restrictions.ge("logTime", start));
		if (end != null)
			dc.add(Restrictions.le("logTime", end));
		if (moduleId != null)
			dc.add(Restrictions.like("moduleId", moduleId, MatchMode.START));
		if (loginName != null)
			dc.add(Restrictions.like("loginName", moduleId, MatchMode.START));

		dc.addOrder(Order.desc("id"));

		return basicDao.findPageOnPageNo(dc, pageSize, pageNo);
	}

	/**
	 * 
	 * @param start
	 * @param end
	 * @param loginName
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public com.uu800.webbase.PageSupport getLoginLogs(Timestamp start, Timestamp end, String loginName, int pageNo,
			int pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(LogLogin.class);
		if (start != null)
			dc.add(Restrictions.ge("loginTime", start));
		if (end != null)
			dc.add(Restrictions.le("loginTime", end));
		if (loginName != null)
			dc.add(Restrictions.like("loginName", loginName, MatchMode.START));

		dc.addOrder(Order.desc("id"));
		return basicDao.findPageOnPageNo(dc, pageSize, pageNo);
	}

	/*
	 * 更新退出信息
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.tixs.common.base.ILogService#logLogout(int)
	 */
	public void logLogout(int userId, int loginId, String remarks) {
		// TODO Auto-generated method stub
		try {
			LogLogin log = (LogLogin) basicDao.get(LogLogin.class, loginId);
			long now = System.currentTimeMillis();
			log.setLogoutTime(new java.sql.Timestamp(now));
			log.setLogoutReamrks(remarks);
			basicDao.update(log);
			// 清除掉在线用户的信息
		} catch (Exception e) {
			LOG.warn("logLogout", e);
		}
	}

}
