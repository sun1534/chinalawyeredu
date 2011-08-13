package com.sxit.wait.util;

import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import com.sxit.wait.model.TwatWait;
import com.sxit.system.model.TsysUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * <p>
 * 功能： 处理待办事宜的接口
 * </p>
 * <p>
 * 作者： 张如兵
 * </p>
 * <p>
 * 公司： 深训信科
 * </p>
 * <p>
 * 日期： 2005.
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */
public class WaitWork {
	private static final Log logger = LogFactory.getLog(WaitWork.class);

	public WaitWork() {
	}

	/**
	 * 创建代办事宜接口
	 * 
	 * @param session
	 *            Session
	 * @param user
	 *            TsysUser
	 * @param subject
	 *            String
	 * @param url
	 *            String
	 * @param docstatus
	 *            int docstatus==1 急件 docstatus==2 办件 docstatus==3 阅件
	 * @param fromto
	 *            String
	 * @param flowstep
	 *            String
	 * @param target
	 *            String
	 * @return int
	 */
	public static int Sendwait(Session session, TsysUser user, String subject,
			String url, int docstatus, String fromto, String flowstep,
			String target) {
		TwatWait wait = new TwatWait();
		try {
			wait.setTsysUser(user);
			wait.setSubject(subject);
			wait.setUrl(url);
			wait.setDocstatus(docstatus);
			wait.setFromto(fromto);
			// wait.setTarget(target==null||target.equals("")?"1":target);
			wait.setTarget(target);
			wait.setFlowstep(flowstep);
			wait.setCreatetime(new java.sql.Timestamp(System
					.currentTimeMillis()));

			session.save(wait);
			logger.info("成功创建待办事宜 to 用户:" + user.getUsername());
		} catch (org.hibernate.HibernateException e) {
			e.printStackTrace();
			logger.info("发送待办事宜失败 to 用户:" + user.getUsername());
			return 0;
		}
		return wait.getWaitid();
	}

	/*
	 * 功能用于处理完一个待办事宜接口 × 调用方法
	 * com.sxit.wait.util.WaitWork.EndWait(getSession(),1,curuser);
	 */
	public static boolean EndWait(Session session, int waitid, TsysUser user) {
		TwatWait wait;
		try {
			wait = (TwatWait) session.get(TwatWait.class, new Integer(waitid));
			if (wait != null) {
				wait.setStatus(1);// 将这个待办事宜设置成已办
				session.update(wait);
				logger.info("*完成一个待办事宜 用户:" + user.getUsername());
			} else {
				logger.info("未找到该待办事宜 用户:" + user.getUsername());
			}
		} catch (org.hibernate.HibernateException e) {
			e.printStackTrace();
			logger.info("更改待办事宜失败 用户:" + user.getUsername());
			return false;
		}
		return true;
	}

	/*
	 * 功能用于处理完一批待办事宜接口 × 调用方法
	 * com.sxit.wait.util.WaitWork.EndWait(getSession(),1,curuser);
	 */
	public static boolean EndWait(Session session, String waitids, TsysUser user) {
		TwatWait wait;
		try {
			if (waitids == null) {
				return true;
			}
			String[] tmp = waitids.split(",");
			for (String waitid : tmp) {
				wait = (TwatWait) session.get(TwatWait.class, Integer
						.parseInt(waitid));
				if (wait != null) {
					wait.setStatus(1);// 将这个待办事宜设置成已办
					session.update(wait);
					logger.info("*完成一个待办事宜 用户:" + user.getUsername());
				}
			}

		} catch (org.hibernate.HibernateException e) {
			e.printStackTrace();
			logger.info("更改待办事宜失败 用户:" + user.getUsername());
			return false;
		}
		return true;
	}

	/*
	 * 功能用于删除一个待办事宜接口 × 调用方法
	 * com.sxit.wait.util.WaitWork.DelWait(getSession(),1,curuser);
	 */
	public static boolean DelWait(Session session, int waitid, TsysUser user) {
		TwatWait wait;
		try {
			wait = (TwatWait) session.get(TwatWait.class, new Integer(waitid));
			if (wait != null) {
				session.delete(wait);
				session.flush();
				logger.info("*删除一个待办事宜 用户:" + user.getUsername());
			} else {
				logger.info("未找到该待办事宜 用户:" + user.getUsername());
			}
		} catch (org.hibernate.HibernateException e) {
			e.printStackTrace();
			logger.info("删除待办事宜失败 用户:" + user.getUsername());
			return false;
		}
		return true;
	}

}
