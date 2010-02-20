/**
 * VideoLookAction.java
 */

package com.changpeng.jifen.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.jifen.service.LawyerlessonxfService;
import com.changpeng.jifen.service.LxnetrecsService;
import com.changpeng.jifen.util.CommonDatas;
import com.changpeng.jifen.util.JifenTime;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lxnetrecs;
import com.changpeng.models.SysUnionparams;

/**
 * <pre>
 *  
 * 看视频,获取课程名称并获取这个人现在看的分数
 * 如果这个人对这个课程已经获得了满分了，则能看，但是不计入分数
 * </pre>
 * 
 * @author 华锋 2008-5-5 下午12:46:19
 * 
 */
public class VideoLookPreAction extends AbstractAction {

	private int lessonid;
	private float videotimeout = 0;

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	public int getLessonid() {
		return this.lessonid;
	}

	private int userid;

	public int getUserid() {
		return this.userid;
	}

	private boolean islastyear;

	public boolean getIslastyear() {
		return this.islastyear;
	}

	/**
	 * 积分年度
	 */
	private int jifenyear;

	public int getJifenyear() {
		return this.jifenyear;
	}

	// 当前年度
	private int nowyear;
	// 去年年度
	private int lastyear;

	public int getNowyear() {
		return this.nowyear;
	}

	public int getLastyear() {
		return this.lastyear;
	}

	private boolean shouldselect;

	/**
	 * 看去年的积分是否ok
	 * 
	 * @return
	 */
	public boolean getShouldselect() {
		return shouldselect;
	}

	@Override
	public String go() throws Exception {
//		this.videotimeout = Float.parseFloat(com.changpeng.common.CommonDatas.SysParameter.get("videotimeout")
//				.toString());
//		this.userid = this.getLoginUser().getLawyerid();
//
//		SysUnionparams params = (SysUnionparams) basicService.get(SysUnionparams.class, this.getLoginUser()
//				.getDirectunion());
//		totalfen = params.getDabiaofen();
//		JifenTime jifentime = CommonDatas.getJifenTime(0, params.getNianshen());
//		nowyear = jifentime.getNianshenyear();
//		this.lastyear = nowyear - 1;
//
//		LxnetrecsService lxnetrecsService = (LxnetrecsService) getBean("lxnetrecsService");
//		BasicService basicService = (BasicService) getBean("basicService");
//		this.lessons = (Lessons) basicService.get(Lessons.class, lessonid);
//		if (this.lessons == null) {
//			this.message = "系统有误,请在在线课程里,选择课程点击观看";
//			this.nextPage = "javascript:window.close()";
//			return "message";
//		} else if (this.lessons.getOnlinefile() == null || this.lessons.getOnlinefile().equals("")) {
//			this.message = "该课程视频文件为空,请返回";
//			this.nextPage = "javascript:window.close()";
//			return "message";
//		}
//
//		this.lxnetrecs = (Lxnetrecs) lxnetrecsService.getLxnetrecs(lessonid, userid);
//
//		debug("this.lxnetrecs==" + this.lxnetrecs);
//
//		LawyerlessonxfService xfservice = (LawyerlessonxfService) this.getBean("lawyerlessonxfService");
//		Lawyerlessonxf xuefen = xfservice.getXuefen(lessonid, this.userid, 0);
//		// 满分了，不管是通过什么方式的
//		if (xuefen != null) {
//			debug("xuefen.getPxxf().floatValue() == lessons.getXuefen().floatValue()"
//					+ (xuefen.getPxxf().floatValue() == lessons.getXuefen().floatValue()));
//			debug(xuefen.getPxxf() + "===" + lessons.getXuefen());
//		}
//		if (xuefen != null && xuefen.getIsfull()) {
//			settime = false;
//		}
//
//		// 如果参加了现场培训，就不
//		if (xuefen != null && xuefen.getLearnmode() == 1) {
//			settime = false;
//			localelesson = true;
//		}
//
//		if (xuefen != null) {
//			jifenyear = xuefen.getTheyear();
//			islastyear = xuefen.getIslastyear() == 1 ? true : false;
//		} else {
//			// 如果所获得的分数小于达标分，则提示是否需要设置学分到去年
//			if (yearfen < totalfen) {
//				shouldselect = true;
//			} else {
//				shouldselect = false;
//				jifenyear = nowyear;
//			}
//		}
//
//		if (lxnetrecs == null)
//			lxnetrecs = new Lxnetrecs();

		return SUCCESS;

	}

	private boolean localelesson;

	public boolean getLocalelesson() {
		return this.localelesson;
	}

	// 是否还需要计时
	private boolean settime = true;

	public boolean getSettime() {
		return this.settime;
	}

	private Lxnetrecs lxnetrecs;

	public Lxnetrecs getLxnetrecs() {
		return this.lxnetrecs;
	}

	private float totalfen;
	private float yearfen;

	public float getTotalfen() {
		return this.totalfen;
	}

	public float getYearfen() {
		return this.yearfen;
	}

	private Lessons lessons;

	public Lessons getLessons() {
		return this.lessons;
	}

	public float getVideotimeout() {
		return videotimeout;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}
}