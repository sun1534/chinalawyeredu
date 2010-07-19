/**
 * LawerChangeCardNoAction.java
 */

package com.changpeng.lessons.action.ajax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.lessons.service.TeacherService;
import com.changpeng.models.Teacher;

/**
 * ajax的方式重置密码
 * 
 * @author 华锋 2008-5-5 下午05:34:22
 * 
 */
public class PasswordResetAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(PasswordResetAction.class);

	public PasswordResetAction() {
		this.rightCode = "passwordReset";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		String newpass = "123456";
		Teacher teacher = (Teacher) basicService.get(Teacher.class, userid);
		try {
			TeacherService basicService = (TeacherService) getBean("teacherService");
			basicService.updatePassword(userid, newpass);

			this.opResult = "管理员" + this.getLoginUser().getUsername() + "替" + teacher.getUsername() + "的密码重置为"
					+ newpass;

			changeok = "密码重置为" + newpass + "成功";

		} catch (Exception e) {
			LOG.error("密码重置为" + newpass + "失败:" + e);
			changeok = "密码重置为" + newpass + "失败:" + e;
			this.opResult = "管理员" + this.getLoginUser().getUsername() + "替" + teacher.getUsername() + "的密码重置为"
					+ newpass + "失败:" + e;
		}

		// TODO Auto-generated method stub
		return SUCCESS;
	}

	private String changeok;

	public String getChangeok() {
		return this.changeok;
	}

	private int userid;

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * @param now
	 *            the now to set
	 */
	public void setNow(String now) {

	}

}
