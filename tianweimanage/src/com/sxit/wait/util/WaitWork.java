package com.sxit.wait.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.sxit.common.BasicService;
import com.sxit.common.Globals;
import com.sxit.models.system.SysUser;
import com.sxit.models.wait.TwatWait;

/**
 * 加入到待办事宜列表
 * 
 * @author 华锋 Jul 6, 2009 1:10:10 AM
 * 
 */
public class WaitWork {
	private static final Log logger = LogFactory.getLog(WaitWork.class);

	private static BasicService basicService = (BasicService) Globals.getBean("basicService");

	public WaitWork() {
	}

	/**
	 * 创建代办事宜接口
	 * 
	 * @param session
	 * @param fromUserid
	 * @param subject
	 * @param url
	 * @param docstatus
	 * @param userid
	 * @param flowstep
	 * @param target
	 * @return
	 */
	public static int Sendwait(int fromUserid, String subject, String url, int docstatus, int userid, String flowstep, String target) {
		TwatWait wait = new TwatWait();
		try {

			wait.setSubject(subject);
			wait.setUrl(url);
			wait.setDocstatus(docstatus);
			wait.setFromUserid(fromUserid);

			SysUser user = (SysUser) basicService.get(SysUser.class, fromUserid);
			wait.setFromUsername(user.getUsername());
			wait.setUserid(userid);
			wait.setTarget(target);
			wait.setFlowstep(flowstep);
			wait.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));

			basicService.save(wait);
			logger.info("成功创建待办事宜 to 用户:" + userid);
		} catch (org.hibernate.HibernateException e) {
			e.printStackTrace();
			logger.info("发送待办事宜失败 to 用户:" + userid);
			return 0;
		}
		return wait.getWaitid();
	}

	/*
	 * 功能用于处理完一个待办事宜接口 × 调用方法
	 * com.sxit.wait.util.WaitWork.EndWait(getSession(),1,curuser);
	 */
	public static boolean EndWait(int waitid, int userid) {
		TwatWait wait = null;
		try {
			// wait = (TwatWait) session.get(TwatWait.class, new
			// Integer(waitid));
			wait = (TwatWait) basicService.get(TwatWait.class, waitid);
			if (wait != null) {
				wait.setStatus(1);// 将这个待办事宜设置成已办
				wait.setUserid(userid);
				basicService.update(wait);
				logger.info("*完成一个待办事宜 用户:" + userid);
			} else {
				logger.info("未找到该待办事宜 用户:" + userid);
			}
		} catch (org.hibernate.HibernateException e) {
			e.printStackTrace();
			logger.info("更改待办事宜失败 用户:" + userid);
			return false;
		}
		return true;
	}

	/*
	 * 功能用于处理完一批待办事宜接口 × 调用方法
	 * com.sxit.wait.util.WaitWork.EndWait(getSession(),1,curuser);
	 */
	public static boolean EndWait(Session session, String waitids, int userid) {
		TwatWait wait = null;
		try {
			if (waitids == null || waitids.equals("")) {
				return true;
			}
			String[] tmp = waitids.split(",");
			for (String waitid : tmp) {
				// wait = (TwatWait) session.get(TwatWait.class,
				// Integer.parseInt(waitid));
				wait = (TwatWait) basicService.get(TwatWait.class, Integer.parseInt(waitid));
				if (wait != null) {
					wait.setStatus(1);// 将这个待办事宜设置成已办
					session.update(wait);
					logger.info("*完成一个待办事宜 用户:" + userid);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("更改待办事宜失败 用户:" + userid);
			return false;
		}
		return true;
	}

	/*
	 * 功能用于删除一个待办事宜接口 × 调用方法
	 * com.sxit.wait.util.WaitWork.DelWait(getSession(),1,curuser);
	 */
	public static boolean DelWait(int waitid, int userid) {
		TwatWait wait = null;
		try {
			wait = (TwatWait) basicService.get(TwatWait.class, waitid);
//			if(wait.getUserid()!=userid)
//				return false;
			if (wait != null) {
				basicService.delete(wait);
				logger.info("*删除一个待办事宜 用户:" + userid);
			} else {
				logger.info("未找到该待办事宜 用户:" + userid);
			}
		} catch (org.hibernate.HibernateException e) {
			e.printStackTrace();
			logger.info("删除待办事宜失败 用户:" + userid);
			return false;
		}
		return true;
	}
}