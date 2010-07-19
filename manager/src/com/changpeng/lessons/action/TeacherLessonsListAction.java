package com.changpeng.lessons.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Lessons;
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
public class TeacherLessonsListAction extends AbstractListAction {

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

	private int lessontype = 0;

	public TeacherLessonsListAction() {

	}

	private int audioQuality = -1;
	private int videoQuality = -1;

	@Override
	protected String go() throws Exception {

		BasicService basicservice = (BasicService) this.getBean("basicService");

		DetachedCriteria dc = DetachedCriteria.forClass(Lessons.class).add(Restrictions.ne("teacherid", 0));

		SysUser loginuser = this.getLoginUser();
		SysRole loginrole = loginuser.getSysRole();
		if (loginrole!=null&&loginrole.getRoleid() == 100) {// 授课律师登录
			teacherid = loginuser.getUserid();
			listall = false;
		} else {
			teacherList = basicservice.findAll(Teacher.class);
		}
		if (audioQuality != -1) {
			dc.add(Restrictions.eq("audioQuality", audioQuality));
		}
		if (videoQuality != -1) {
			dc.add(Restrictions.eq("videoQuality", videoQuality));
		}
		if (title != null && !title.equals("")) {
			dc.add(Restrictions.like("title", title, MatchMode.ANYWHERE));
		}
		if (lessontype != 0) {
			dc.add(Restrictions.eq("lessontype", lessontype));
		}
if(teacherid!=0)
		dc.add(Restrictions.eq("teacherid", teacherid));

		dc.addOrder(Order.desc("lessonid"));
		this.page = basicservice.findPageByCriteria(dc, pageSize, pageNo);
		com.changpeng.system.util.CommonDatas.getGroups();
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
	 * @return the lessontype
	 */
	public int getLessontype() {
		return lessontype;
	}

	/**
	 * @param lessontype
	 *            the lessontype to set
	 */
	public void setLessontype(int lessontype) {
		this.lessontype = lessontype;
	}

}