/**
 * TSysUserAddAction.java
 */
package com.changpeng.lessons.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.lessons.service.TeacherService;
import com.changpeng.models.Teacher;

/**
 * 
 * 管理员信息删除
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class TeacherViewAction extends AbstractAction {

	private int userId;

	public TeacherViewAction() {
		// this.rightCode = "sysUserDelete";

	}


	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		TeacherService service = (TeacherService) this.getBean("teacherService");
	
		
		
		 teacher=(Teacher)service.get(Teacher.class, userId);
	
		return SUCCESS;
	}
	
	private Teacher teacher;

	public Teacher getTeacher() {

		return teacher;
	}
}
