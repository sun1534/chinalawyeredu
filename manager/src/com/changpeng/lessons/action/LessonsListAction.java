package com.changpeng.lessons.action;

import java.sql.Timestamp;
import java.util.List;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.jifen.util.CommonDatas;
import com.changpeng.jifen.util.JifenTime;
import com.changpeng.lessons.service.LessonsService;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUnionparams;
import com.changpeng.models.SysUser;

/**
 * 
 * 这是课程管理的list，因此考虑的是要根据什么东东来查
 * 
 * @author 华锋
 * 
 */
public class LessonsListAction extends AbstractListAction {

	private String title;
	private JifenTime jifentime;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private int lessontype = 0;
	private String teachers = "";
//	private int groupid = -1; // 0是代表来自平台的添加
	private List fromlist = null;

	/**
	 * @return the jifentime
	 */
	public JifenTime getJifentime() {
		return jifentime;
	}

	private int lessonstyle = 0;

	public LessonsListAction() {
		datavisible = new DataVisible();
		datavisible.setProvinceid(-1);
	}

	@Override
	protected String go() throws Exception {

		BasicService basicservice = (BasicService) this.getBean("basicService");
		LessonsService lessonsService = (LessonsService) this.getBean("lessonsService");

		SysGroup _mygroup = this.getLoginUser().getSysGroup();
		String nianshen = "01-01";
		
		int groupid=-1;
		
		if (_mygroup != null) {
			mygroup = _mygroup.getGroupid();
		
			if (_mygroup.getGrouptype() == 1)
				groupid = _mygroup.getParentid();
			else if (_mygroup.getGrouptype() == 2)
				groupid = _mygroup.getGroupid();
		

		}
		
		if (datavisible.getProvinceid() != 0)
			groupid = datavisible.getProvinceid();
		if (datavisible.getCityid() != 0)
			groupid = datavisible.getCityid();
		
		
//		System.out.println("=============groupid="+groupid);
		
		
		SysUnionparams params = (SysUnionparams) basicservice.get(SysUnionparams.class, groupid);
		if (params != null)
			nianshen = params.getNianshen();
		Timestamp start=null;
		Timestamp end=null;

		jifentime = CommonDatas.getJifenTime(nianshenyear, nianshen);
if(nianshenyear!=0){
		this.nianshenyear = jifentime.getNianshenyear();
		start=jifentime.getStart();
    	end=jifentime.getEnd();
}
//		System.out.println(jifentime.getStart()+",,,"+jifentime.getEnd());

	
		this.page = lessonsService.getPages(_mygroup, groupid, lessonstyle, lessontype, title, teachers, pageSize,
				pageNo, start, end);
		com.changpeng.system.util.CommonDatas.getGroups();

		// 课程来源的选择列表，这里就不什么省、市的选择了，统一一个课程的来源
		// fromlist = groupservice.getAllsharedunion();

		// 也显示全国律协的以及系统层级的
		SysUser user = new SysUser();
		this.datavisible.getVisibleDatas(user, true);

		if (lessonstyle == 1) {
			return "local";
		}
		return "online";
	}

	private int mygroup;

	public int getMygroup() {
		return this.mygroup;
	}

	public int getLessontype() {
		return lessontype;
	}

	public void setLessontype(int lessontype) {
		this.lessontype = lessontype;
	}

	public String getTeachers() {
		return teachers;
	}

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

//	/**
//	 * @return the groupid
//	 */
//	public int getGroupid() {
//		return groupid;
//	}
//
//	/**
//	 * @param groupid
//	 *            the groupid to set
//	 */
//	public void setGroupid(int groupid) {
//		this.groupid = groupid;
//	}

	/**
	 * @return the fromlist
	 */
	public List getFromlist() {
		return fromlist;
	}

	/**
	 * @return the lessonstyle
	 */
	public int getLessonstyle() {
		return lessonstyle;
	}

	/**
	 * @param lessonstyle
	 *            the lessonstyle to set
	 */
	public void setLessonstyle(int lessonstyle) {
		this.lessonstyle = lessonstyle;
	}

	private int nianshenyear = 0;

	/**
	 * @return the nianshenyear
	 */
	public int getNianshenyear() {
		return nianshenyear;
	}

	/**
	 * @param nianshenyear
	 *            the nianshenyear to set
	 */
	public void setNianshenyear(int nianshenyear) {
		this.nianshenyear = nianshenyear;
	}

}