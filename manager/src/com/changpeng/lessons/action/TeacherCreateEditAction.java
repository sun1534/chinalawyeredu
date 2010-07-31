/**
 * TTeachersAddAction.java
 */

package com.changpeng.lessons.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.lessons.service.TeacherService;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysRole;
import com.changpeng.models.Teacher;
import com.changpeng.system.service.SysUserService;

/**
 * 
 * 新增老师信息
 * 
 * 默认所在的locationid为其所在的id
 * 
 * @author 华锋 2008-2-25 上午11:12:05 2009-3-11 Tompan 新增分配角色部分,默认为这个人所对应的角色
 * 
 */
public class TeacherCreateEditAction extends AbstractAction {

	private Teacher teacher;

	public Teacher getTeacher() {
		if (teacher == null)
			teacher = (Teacher) get("teacher");
		return teacher;
	}

	public TeacherCreateEditAction() {
//		this.teacher = new Teacher();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * 这里还涉及到了所在地域的问题
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		TeacherService tservice = (TeacherService) this.getBean("teacherService");
		SysUserService service = (SysUserService) this.getBean("sysUserService");

		if (isnew) {
			if (service.getSysUserByLoginname(teacher.getLoginname()) != null) {
				this.message = "对不起，您输入的帐号【" + teacher.getLoginname() + "】已经被他人使用。";
				return "message";
			}
			if (!teacher.getPassword().equals(passagain)) {
				this.message = "两次密码输入不同,请重新输入!";
				return "message";
			}

			teacher.setCreateuser(super.getLoginUser().getUsername());
			teacher.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			teacher.setCreateuserid(this.getLoginUser().getUserid());

			tservice.addTeacher(teacher);

			this.opResult = "管理员" + super.getLoginUser().getUsername() + "新增了授课律师" + teacher.getUsername();
			this.message = "授课律师信息新增成功";
		} else {

			System.out.println("teacher.getLoginname():"+teacher.getLoginname());
			System.out.println("teacher.getPassword():"+teacher.getPassword());
			System.out.println("teacher.getCreateuser():"+teacher.getCreateuser());
			
			tservice.updateTeacher(teacher);
			this.opResult = "管理员" + super.getLoginUser().getUsername() + "修改了授课律师" + teacher.getUsername() + "信息";
			this.message = "授课律师信息修改成功";
		}
		this.nextPage = "teacherList.pl";
		return SUCCESS;
	}

	/**
	 * 这里要将部门信息放出来,树形结构显示
	 * 
	 * 这里得到省一级的地市信息数据
	 */
	@Override
	public String input() throws Exception {

		// 也显示全国律协的以及系统层级的

		this.teacher = (Teacher) basicService.get(Teacher.class, userId);

		if (teacher == null) {
			isnew = true;
			teacher = new Teacher();
		}
		set("teacher", teacher);
		System.out.println("isnew:::::::::" + isnew);
		return INPUT;
	}

	private int userId;
	private boolean isnew;

	private String passagain;

	public void setPassagain(String passagain1) {
		this.passagain = passagain1;
	}

	/**
	 * @return the passagain
	 */
	public String getPassagain() {
		return passagain;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the isnew
	 */
	public boolean getIsnew() {
		return isnew;
	}

	/**
	 * @param isnew
	 *            the isnew to set
	 */
	public void setIsnew(boolean isnew) {
		this.isnew = isnew;
	}
}