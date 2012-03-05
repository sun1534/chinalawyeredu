package com.changpeng.lessons.action;

import java.util.List;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.lessons.service.LessonsService;
import com.changpeng.models.SysRole;
import com.changpeng.models.SysUser;
import com.changpeng.models.Teacher;

/**
 * 
 * 显示授课律师的课程
 * 
 * 这是课程管理的list，因此考虑的是要根据什么东东来查
 * 
 * @author 华锋
 * 
 */
public class TeacherLessonsStaticAction extends AbstractListAction {

	private String title;

	private int teacherid;

	/**
	 * @return the teacherid
	 */
	public int getTeacherid() {
		return teacherid;
	}

	/**
	 * @param teacherid
	 *            the teacherid to set
	 */
	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	public TeacherLessonsStaticAction() {

	}



	@Override
	protected String go() throws Exception {
		SysUser loginuser = this.getLoginUser();
		SysRole loginrole = loginuser.getSysRole();
		if (loginrole!=null&&loginrole.getRoleid() == 100) {// 授课律师登录
			teacherid = loginuser.getUserid();
			listall = false;
		} else {
			teacherList = basicService.findAll(Teacher.class);
		}
		
		LessonsService service=(LessonsService)getBean("lessonsService");
		System.out.println("teacherid ::"+teacherid);
		this.page=service.getLessonStatic(teacherid, title, null, null, pageNo, pageSize);
		
		
		return SUCCESS;
	}

	private List teacherList;

	public List getTeacherList() {
		return this.teacherList;
	}

	private boolean listall = true;

	public boolean getListall() {
		return listall;
	}


}