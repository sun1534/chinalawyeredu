/**
 * VideoLookAction.java
 */

package com.changpeng.jifen.action.ajax;

import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.CommonDatas;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.jifen.service.LxnetrecsService;
import com.changpeng.models.Lxnetrecs;

/**
 * <pre>
 * 看视频加分
 * ajax的方式来处理，异步
 * </pre>
 * 
 * @author 华锋 2008-5-5 下午01:21:00
 * 
 */
public class VideoLookAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(VideoLookAction.class);
	private int netrecsid;
	private int lessonid;
	private float allminutes;
	private float lookedminutes;

	private int userid;
	private int jifenyear;

	public void setJifenyear(int year) {
		this.jifenyear = year;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public VideoLookAction() {
		this.needsession = false;
	}

	private static int startYear = 2008;

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
		// lastyear = nowyear - 1;
		// if (lastyear < startYear)
		// lastyear = startYear;
	}

	private int nowyear;

	public String go() throws Exception {
		lastyear();
		try {

			LxnetrecsService lxnetrecsService = (LxnetrecsService) getBean("lxnetrecsService");
			BasicService basicService = (BasicService) getBean("basicService");

			Lxnetrecs lxnetrecs = (Lxnetrecs) basicService.get(Lxnetrecs.class, netrecsid);
			// 新增网上看视频的情况，同时对这个人的积分表进行新增，如果没有这个人对这个课程的积分的话
			if (lxnetrecs == null) {
				lxnetrecs = new Lxnetrecs();
				lxnetrecs.setAllminutes(allminutes);
				lxnetrecs.setLasttime(new java.sql.Timestamp(System.currentTimeMillis()));
				lxnetrecs.setLessonid(lessonid);
				lxnetrecs.setLookedminutes(lookedminutes);
				lxnetrecs.setUserid(userid);
				lxnetrecs.setJifenyear(jifenyear);
				lxnetrecs.setNowyear(nowyear);
				this.huodexuefen = lxnetrecsService.saveLxnetrecs(lxnetrecs);
				this.netrecsid = lxnetrecs.getNetrecsid();

			} else {// 更新这个人的积分情况
				// 如果这个人重新开始看，并lookedminutes小于已经看过的时间的话，则不予理睬
				if (this.lookedminutes < lxnetrecs.getLookedminutes()) {
					this.huodexuefen = 0;
				} else {
					// 更新这个lesson的看视频的情况并更新这个课程的学分情况
					lxnetrecs.setLasttime(new java.sql.Timestamp(System.currentTimeMillis()));
					lxnetrecs.setLookedminutes(lookedminutes);
					this.huodexuefen = lxnetrecsService.updateLxnetrecs(lxnetrecs);
				}
			}
		} catch (Exception e) {

			LOG.error("视频课程有误:::" + e);

			this.huodexuefen = -1;
		}
		return SUCCESS;
	}

	/**
	 * 返回给页面，你获得了多少个学分
	 */
	private float huodexuefen;

	public float getHuodexuefen() {
		return this.huodexuefen;
	}

	/**
	 * @param netrecsid
	 *            the netrecsid to set
	 */
	public void setNetrecsid(int netrecsid) {
		this.netrecsid = netrecsid;
	}

	public int getNetrecsid() {
		return this.netrecsid;
	}

	/**
	 * @param lookedminutes
	 *            the lookedminutes to set
	 */
	public void setLookedminutes(float lookedminutes) {
		this.lookedminutes = lookedminutes;
	}

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	/**
	 * @param allminutes
	 *            the allminutes to set
	 */
	public void setAllminutes(float allminutes) {
		this.allminutes = allminutes;
	}
}
