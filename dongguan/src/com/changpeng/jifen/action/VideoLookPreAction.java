/**
 * VideoLookAction.java
 */

package com.changpeng.jifen.action;

import java.util.Calendar;

import com.changpeng.common.BasicService;
import com.changpeng.common.CommonDatas;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.jifen.service.LawyerlessonxfService;
import com.changpeng.jifen.service.LxnetrecsService;
import com.changpeng.jifen.util.NumberUtil;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lxnetrecs;

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
	private int startYear = 2008;

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

	private void lastyear() {
		Calendar calendar = Calendar.getInstance();
		int nowmonth = calendar.get(Calendar.MONTH) + 1;
		int nowday = calendar.get(Calendar.DATE);
		int _nowyear = calendar.get(Calendar.YEAR);

		String nianshen = CommonDatas.SysParameter.get("nianshen").toString();
		int index = nianshen.indexOf("-");
		int shenmonth = Integer.parseInt(nianshen.substring(0, index));
		int shenday = Integer.parseInt(nianshen.substring(index + 1));
		// 今天是5.5，年審時間是5.4號，那應該算2008年的了,否则就算是2007年的

		if (nowmonth * 100 + nowday > shenmonth * 100 + shenday) {
			nowyear = _nowyear;
		} else {
			nowyear = _nowyear - 1;
		}
		lastyear = nowyear - 1;
		if (lastyear < startYear)
			lastyear = startYear;
	}

	private float totalfen;
	private float yearfen;

	public float getTotalfen() {
		return this.totalfen;
	}

	public float getYearfen() {
		return this.yearfen;
	}

	@Override
	public String go() throws Exception {
		lastyear();

		this.videotimeout = Float.parseFloat(com.changpeng.common.CommonDatas.SysParameter.get("videotimeout").toString());
		this.userid = this.getLoginUser().getUserid();
		LawyerlessonxfService xfService = (LawyerlessonxfService) getBean("lawyerlessonxfService");

		totalfen = Integer.parseInt(CommonDatas.SysParameter.get("dabiaofen").toString());
		yearfen = xfService.getYearTotal(this.getLoginUser().getUserid(), lastyear);

		LxnetrecsService lxnetrecsService = (LxnetrecsService) getBean("lxnetrecsService");
		BasicService basicService = (BasicService) getBean("basicService");
		this.lessons = (Lessons) basicService.get(Lessons.class, lessonid);
		if (this.lessons == null) {
			this.message = "系统有误,请在在线课程里,选择课程点击观看";
			this.nextPage = "javascript:window.close()";
			return "message";
		}
		// this.lessontitle = lesson.getTitle();
		// this.lessonurl = lesson.getOnlinefile();

		this.lxnetrecs = (Lxnetrecs) lxnetrecsService.getLxnetrecs(lessonid, getLoginUser().getUserid());

		debug("this.lxnetrecs==" + this.lxnetrecs);

		LawyerlessonxfService xfservice = (LawyerlessonxfService) this.getBean("lawyerlessonxfService");
		Lawyerlessonxf xuefen = xfservice.getXuefen(lessonid, this.userid, null);
		// 满分了，不管是通过什么方式的
		//
		//
		//		
		// debug("xuefen=========" + xuefen);
		if (xuefen != null) {
			debug("xuefen.getPxxf().floatValue() == lessons.getXuefen().floatValue()"
					+ (xuefen.getPxxf().floatValue() == lessons.getXuefen().floatValue()));
			debug(xuefen.getPxxf() + "===" + lessons.getXuefen());
		}

		float kechengxuefen = lessons.getXuefen().floatValue();

		float dazhexuefen = kechengxuefen;

		if (lessons.getFenshuoff() != null && !"".equals(lessons.getFenshuoff())) {// 获得的学分要打折
			int zhekou = Integer.parseInt(lessons.getFenshuoff());
			dazhexuefen = Float.parseFloat(NumberUtil.toMoney((kechengxuefen * zhekou) / 100));

		}

		// 如果在线视频打了折扣的话，判断是否和折扣后是一样的不
		if (xuefen != null
				&& ((xuefen.getPxxf().floatValue() == dazhexuefen && xuefen.getLearnmode().equals("在线视频")) || (xuefen.getPxxf().floatValue() == kechengxuefen && !xuefen
						.getLearnmode().equals("在线视频")))) {
			// 不计时了
			settime = false;
		}

		if (xuefen != null) {
			jifenyear = xuefen.getTheyear();
			islastyear=xuefen.getIslastyear()==1?true:false;
		} else {
			// 如果所获得的分数小于达标分，则提示是否需要设置学分到去年
			if (yearfen < totalfen) {
				shouldselect = true;
			} else {
				shouldselect = false;
				jifenyear=nowyear;
			}
		}

		// 如果参加了现场培训，不管怎样，都不设置积分了
		if (xuefen != null && xuefen.getLearnmode().equals("现场培训")) {
			settime = false;

			localelesson = true;
		}

		if (lxnetrecs == null)
			lxnetrecs = new Lxnetrecs();

		return SUCCESS;

	}
private boolean islastyear;
public boolean getIslastyear(){
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

	private boolean localelesson;

	public boolean getLocalelesson() {
		return this.localelesson;
	}

	// 是否还需要计时
	private boolean settime = true;

	public boolean getSettime() {
		return this.settime;
	}

	// private String lessontitle;
	//
	// public String getLessontitle() {
	// return this.lessontitle;
	// }

	private Lxnetrecs lxnetrecs;

	public Lxnetrecs getLxnetrecs() {
		return this.lxnetrecs;
	}

	/*
	 * 视频的url
	 */
	// private String lessonurl;
	//
	// public String getLessonurl() {
	// return this.lessonurl;
	// }
	private int netrecsid;

	public int getNetrecsid() {
		return this.netrecsid;
	}

	private Lessons lessons;

	public Lessons getLessons() {
		return this.lessons;
	}

	public float getVideotimeout() {
		return videotimeout;
	}
}