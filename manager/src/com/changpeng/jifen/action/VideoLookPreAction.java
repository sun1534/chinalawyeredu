/**
 * VideoLookAction.java
 */

package com.changpeng.jifen.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lessons;

/**
 * <pre>
 * 
 * 看视频而已
 * </pre>
 * 
 * @author 华锋 2008-5-5 下午12:46:19
 * 
 */
public class VideoLookPreAction extends AbstractAction {

	private int lessonid;
//	private float videotimeout = 0;

	public VideoLookPreAction(){
		this.needsession=false;
	}
	
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

	@Override
	public String go() throws Exception {
//		this.videotimeout = Float.parseFloat(com.changpeng.common.CommonDatas.SysParameter.get("videotimeout")
//				.toString());

//		LxnetrecsService lxnetrecsService = (LxnetrecsService) getBean("lxnetrecsService");
		BasicService basicService = (BasicService) getBean("basicService");
		this.lessons = (Lessons) basicService.get(Lessons.class, lessonid);
		if (this.lessons == null) {
			this.message = "系统有误,请在在线课程里,选择课程点击观看";
			this.nextPage = "javascript:window.close()";
			return "message";
		} else if (this.lessons.getOnlinefile() == null || this.lessons.getOnlinefile().equals("")) {
			this.message = "该课程视频文件为空,请返回";
			this.nextPage = "javascript:window.close()";
			return "message";
		}
		return SUCCESS;
	}

	private Lessons lessons;

	public Lessons getLessons() {
		return this.lessons;
	}

}