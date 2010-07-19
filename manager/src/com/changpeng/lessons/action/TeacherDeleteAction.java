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
public class TeacherDeleteAction extends AbstractAction {

	private int userId;

	public TeacherDeleteAction() {
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
	
		
		
		Teacher teacher=(Teacher)service.get(Teacher.class, userId);
		message = "该授课教师的信息删除成功";
		service.delteTeacher(userId);
		this.opResult+=this.getLoginUser().getUsername()+"删除授课老师:"+teacher.getUserid()+"成功";
		
		this.nextPage = "teacherList.pl";
		return SUCCESS;
	}
}
