package com.changpeng.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.core.model.TwatWait;
import com.changpeng.core.user.model.CoreUser;

/**
 * 加入到待办事宜列表
 * 
 * @author 华锋 Jul 6, 2009 1:10:10 AM
 * 
 */
public class WaitWork {
	private static final Log logger = LogFactory.getLog(WaitWork.class);

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
	public static int Sendwait(int fromUserid, String subject,BasicService service) {
		TwatWait wait = new TwatWait();
		try {
			wait.setSubject(subject);
			wait.setUrl("/users/usersConfirmDoView.action?userId="+fromUserid);

			wait.setFromUserid(fromUserid);

			CoreUser user = (CoreUser) service.get(CoreUser.class, fromUserid);
			wait.setFromUsername(user.getUserName());
			wait.setUserid(0);
			wait.setTarget("0");
			wait.setFlowstep("0");
			wait.setDocstatus(2);
			wait.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));

			service.save(wait);
//			logger.info("成功创建待办事宜 to 用户:" + userid);
		} catch (org.hibernate.HibernateException e) {
			e.printStackTrace();
//			logger.info("发送待办事宜失败 to 用户:" + userid);
			return 0;
		}
		return wait.getWaitid();
	}

}
