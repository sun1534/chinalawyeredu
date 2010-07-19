/**
 * SysLoginLogService.java
 */

package com.changpeng.lawyers.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.BasicDAO;
import com.changpeng.common.BasicService;
import com.changpeng.common.CommonDatas;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.LawyerLoginlog;
import com.changpeng.models.Lawyers;

/**
 * 登录日志情况
 * 
 * @author 华锋 2008-2-26 下午02:41:25
 * 
 */
public class LawyerLoginLogService extends BasicService {

	private static Log LOG = LogFactory.getLog(LawyerLoginLogService.class);

	private BasicDAO basicDAO;

	/**
	 * @param basicDAO
	 *            the basicDAO to set
	 */
	public void setBasicDAO(BasicDAO basicDAO) {
		this.basicDAO = basicDAO;
	}

	/**
	 * 更新注销退出信息
	 * 
	 * @param loginid
	 * @param remarks
	 * @throws ServiceException
	 */
	public void updateLogoutInfo(int lawyerId,int loginid, String remarks) throws ServiceException {
		try {
			LawyerLoginlog log = (LawyerLoginlog) basicDAO.get(LawyerLoginlog.class, loginid);
			long now = System.currentTimeMillis();
			LOG.debug("log=========" + log);
			if(log!=null){
			Timestamp loginTime = log.getLoginTime();
			
			long login = loginTime.getTime();
			log.setInSysTime((int) (now - login) / 1000);
			}else
				log.setInSysTime(0);
			log.setLogoutTime(new java.sql.Timestamp(now));
			log.setRemarks(remarks);
		
			basicDAO.update(log);
			//清除掉在线用户的信息
			CommonDatas.ONLINE_USERS.remove(lawyerId);
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
	@Transactional
	public void updateLogoutInfo(final String contextid) {
		try {

			List list = basicDAO
					.find(
							"from com.changpeng.models.LawyerLoginlog login where login.contextid=? and (login.logoutTime is null or login.logoutTime='')",
							contextid);
			long now = System.currentTimeMillis();
			for (int i = 0; i < list.size(); i++) {
				LawyerLoginlog loginlog = (LawyerLoginlog) list.get(i);
				long login = loginlog.getLoginTime().getTime();
				loginlog.setLogoutTime(new java.sql.Timestamp(now));
				loginlog.setRemarks("WEB容器关闭导致退出");
				loginlog.setInSysTime((int) (now - login) / 1000);
				basicDAO.update(loginlog);
			}

		} catch (Exception e) {
			LOG.error("退出日志出错=" + e);
		}
	}

	/**
	 * 这是一个事务处理的过程,将之前的islast都设置为0,新增登录信息
	 * 
	 * @param _loginip
	 * @param _userid
	 * @throws ServiceException
	 */
	@Transactional
	public int insertLoginLog(final String _loginip, Lawyers lawyer, final String _contextid, final String remarks) {

		try {
			// 将之前的islast消息都设置为false
			basicDAO.execute(
					"update com.changpeng.models.LawyerLoginlog log set log.islast=false where log.lawyerid=?", lawyer
							.getLawyerid());

			// 插入新的数据
			LawyerLoginlog log = new LawyerLoginlog();
			log.setIslast(true);
			log.setLoginip(_loginip);
			log.setLoginTime(new java.sql.Timestamp(System.currentTimeMillis()));
			log.setLawyerid(lawyer.getLawyerid());
			log.setLawyername(lawyer.getLawyername());
			log.setCityid(lawyer.getDirectunion());
			log.setProvinceid(lawyer.getProvinceunion());
			log.setOfficeid(lawyer.getTheoffice());
			log.setContextid(_contextid);
			log.setLoginremarks(remarks);

			basicDAO.save(log);
			LOG.debug("登录的LOGINID=" + log.getLoginid());
			
			CommonDatas.ONLINE_USERS.put(log.getLawyerid(), log);
			
			return log.getLoginid();

		} catch (Exception e) {
			LOG.error("登陆日志出错=" + e);
			return 0;
		}
	}

	public int getLoginCountByUserId(int lawyerid) throws ServiceException {
		try {
			List list = basicDAO.find("from com.changpeng.models.LawyerLoginlog log where log.lawyerid=?", lawyerid);
			if (list != null && list.size() != 0)
				return list.size();
			return 0;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 得到最近的一次登录时间
	 * 
	 * @param lawyerid
	 * @return
	 */
	public Timestamp getLastLoginTime(int lawyerid) throws ServiceException {
		try {
			List list = basicDAO.find("from com.changpeng.models.LawyerLoginlog log where log.islast=true");
			if (list != null && list.size() != 0) {
				LawyerLoginlog loginlog = (LawyerLoginlog) (list.get(0));
				return loginlog.getLoginTime();
			}
			return null;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}