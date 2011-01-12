/**
 * VideoLookAction.java
 */

package com.changpeng.jifen.action.ajax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.jifen.service.LxnetrecsService;
import com.changpeng.models.BasicLawyerlessonxf;
import com.changpeng.models.LogVideoJifen;
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
	// private int netrecsid;
	private int lessonid;
	private float allminutes;
	private float lookedminutes;

	private int userid;
	private int visitid;
	private int jifenyear;

	public void setJifenyear(int year) {
		this.jifenyear = year;
	}

	private int nowyear;

	/**
	 * @param nowyear
	 *            the nowyear to set
	 */
	public void setNowyear(int nowyear) {
		this.nowyear = nowyear;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public VideoLookAction() {
		this.needsession = false;
	}

	public String go() throws Exception {

		try {

			LxnetrecsService lxnetrecsService = (LxnetrecsService) getBean("lxnetrecsService");
			LogVideoJifen videojifen = new LogVideoJifen();
			videojifen.setAllminutes(allminutes);
			videojifen.setLessonid(lessonid);
			videojifen.setLookedminutes(lookedminutes);
//			System.out.println("lookedminutes:::"+lookedminutes);
			videojifen.setLawyerid(userid);
			videojifen.setJifenyear(jifenyear);
			videojifen.setNowyear(nowyear);
			videojifen.setVisitid(visitid);
			BasicLawyerlessonxf xf = lxnetrecsService.saveLxnetrecs(videojifen);

			if (xf != null){
				this.huodexuefen = xf.getPxxf();
				System.out.println(lookedminutes+"==>"+allminutes+"==>"+xf.getIsfull());
			}

		} catch (Exception e) {
//e.printStackTrace();
			LOG.error("视频课程有误:::" , e);

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

	/**
	 * @param visitid
	 *            the visitid to set
	 */
	public void setVisitid(int visitid) {
		this.visitid = visitid;
	}
}
