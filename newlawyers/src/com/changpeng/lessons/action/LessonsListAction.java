package com.changpeng.lessons.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.jifen.service.LawyerlessonxfService;
import com.changpeng.jifen.util.CommonDatas;
import com.changpeng.jifen.util.JifenTime;
import com.changpeng.lawyers.service.SysGroupService;
import com.changpeng.lessons.service.LessonsService;
import com.changpeng.models.BasicLawyerlessonxf;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.Lawyers;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lessontype;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUnionparams;

/**
 * 查看本地课程。 只显示所在律协的以及所在省律协的
 * 
 * @author 华锋
 * 
 */

public class LessonsListAction extends AbstractListAction {

	private BasicService basicService = null;
	private JifenTime jifentime;
	private int lessontype=-1;
	private Lessontype notfenlei;
	/**
	 * @return the notfenlei
	 */
	public Lessontype getNotfenlei() {
		return notfenlei;
	}

	public int getLessontype() {
		return lessontype;
	}

	/**
	 * @return the jifentime
	 */
	public JifenTime getJifentime() {
		return jifentime;
	}

	public LessonsListAction() {
		this.datavisible = new DataVisible();
		basicService = (BasicService) this.getBean("basicService");
	}

	/**
	 * 数据的可见性
	 */
	protected DataVisible datavisible;

	public DataVisible getDatavisible() {
		return this.datavisible;
	}

	@Override
	protected String go() throws Exception {
//		this.pageSize = 20;
		
		if(lessonstyle==2)
			moduleId=1000;
		else
			moduleId=1002;
		
		LessonsService lessonservice = (LessonsService) this.getBean("lessonsService");

//		System.out.println("lessonservice===="+lessonservice);
		this.datavisible.getVisibleDatas(this.getLoginUser(), false);
		notfenlei=(Lessontype)basicService.get(Lessontype.class, 0);
		
		
//		System.out.println("notfenlei===="+notfenlei);
		SysGroupService groupservice = (SysGroupService) this.getBean("sysGroupService");

		Lawyers lawyers = this.getLoginUser();
		// 根据查询的年来查,默认为当前时间所在的积分年
		SysUnionparams params = (SysUnionparams) basicService.get(SysUnionparams.class, lawyers.getDirectunion());
		Timestamp start = null;
		Timestamp end = null;

		jifentime = CommonDatas.getJifenTime(nianshenyear, params.getNianshen());
		if (nianshenyear != 0) {
			this.nianshenyear = jifentime.getNianshenyear();
			start = jifentime.getStart();
			end = jifentime.getEnd();
		}
		SysGroup mygroup = (SysGroup) basicService.get(SysGroup.class, this.getLoginUser().getDirectunion());

		LawyerlessonxfService xfservice = (LawyerlessonxfService) this.getBean("lawyerlessonxfService");

		int groupid = -1;
		if (lessonstyle == 2 || lessonstyle == 100) {
			if (datavisible.getProvinceid() != 0)
				groupid = datavisible.getProvinceid();
			if (datavisible.getCityid() != 0)
				groupid = datavisible.getCityid();
		}
//System.out.println("audioQuality="+audioQuality+",,videoQuality="+videoQuality);
		
		
		
//		this.page = lessonservice.getPages(mygroup, groupid, audioQuality, videoQuality, onlineType, lessonstyle,
//				lessontype, title, teachers, 8, pageNo, start, end);
		
		if(resultType.equals("view"))
			pageSize=8;
		this.page=lessonservice.getPages(mygroup, groupid, audioQuality, videoQuality, onlineType, lessonstyle, lessontype, title, teachers, isfree, orderBy, pageSize, pageNo);
			

		int lawyerid = this.getLoginUser().getLawyerid();
		lessonlist = new ArrayList();
		List list = page.getItems();
		for (int i = 0; list != null && i < list.size(); i++) {
			Lessons s = (Lessons) list.get(i);
			int lessonid = s.getLessonid();

			BasicLawyerlessonxf xf = null;
			if (this.getLoginUser().getLawyertype() == -1)
				xf = xfservice.getXuefenShixi(lessonid, lawyerid, 0);
			else if (this.getLoginUser().getLawyertype() == -2)
				xf = xfservice.getXuefenGongzheng(lessonid, lawyerid, 0);
			else
				xf = xfservice.getXuefen(lessonid, lawyerid, 0);

			// Lawyerlessonxf xf = xfservice.getXuefen(lessonid, lawyerid, 0);
			if (xf == null) {
				xf = new Lawyerlessonxf();
				xf.setLawyerid(lawyerid);
				xf.setPxxf(0f);
				xf.setIsfull(false);
			}
			s.setYihuoxuefen(xf);
			lessonlist.add(s);

		}

		// 课程来源的选择列表，这里就不什么省、市的选择了，统一一个课程的来源
		fromlist = groupservice.getAllsharedunion();
		if (this.lessonstyle == 100)
			viewother = true;
		if (this.lessonstyle == 1 || this.lessonstyle == 100) {
			return "local";
		}
		return "online";
	}

	
	private String isfree="-1";
	private String orderBy;
	private String resultType="view";
	
	private int audioQuality = -1;
	private int videoQuality = -1;
	private int onlineType = -1;
	private List lessonlist;

	public List getLessonlist() {
		return this.lessonlist;
	}

	private List fromlist = null;

	/**
	 * @return the fromlist
	 */
	public List getFromlist() {
		return fromlist;
	}

	private boolean viewother;

	public boolean getViewother() {
		return this.viewother;
	}

	private String title;
	private String teachers;

	private int lessonstyle;

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

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the teachers
	 */
	public String getTeachers() {
		return teachers;
	}

	/**
	 * @param teachers
	 *            the teachers to set
	 */
	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

	/**
	 * @param lessontype
	 *            the lessontype to set
	 */
	public void setLessontype(int lessontype) {
		this.lessontype = lessontype;
	}

	/**
	 * @return the onlineType
	 */
	public int getOnlineType() {
		return onlineType;
	}

	/**
	 * @param onlineType
	 *            the onlineType to set
	 */
	public void setOnlineType(int onlineType) {
		this.onlineType = onlineType;
	}

	/**
	 * @return the audioQuality
	 */
	public int getAudioQuality() {
		return audioQuality;
	}

	/**
	 * @param audioQuality
	 *            the audioQuality to set
	 */
	public void setAudioQuality(int audioQuality) {
		this.audioQuality = audioQuality;
	}

	/**
	 * @return the videoQuality
	 */
	public int getVideoQuality() {
		return videoQuality;
	}

	/**
	 * @param videoQuality
	 *            the videoQuality to set
	 */
	public void setVideoQuality(int videoQuality) {
		this.videoQuality = videoQuality;
	}

	/**
	 * @return the isfree
	 */
	public String getIsfree() {
		return isfree;
	}

	/**
	 * @param isfree the isfree to set
	 */
	public void setIsfree(String isfree) {
		this.isfree = isfree;
	}

	/**
	 * @return the orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return the resultType
	 */
	public String getResultType() {
		return resultType;
	}

	/**
	 * @param resultType the resultType to set
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
}