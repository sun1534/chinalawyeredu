
package com.changpeng.lessons.action.ajax;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.lessons.service.LessonsService;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lessonscore;

public class RefreshScoreAction extends AbstractAction {
	private int score;
	private int lessonid;

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}
	public int getLessonid(){
		return lessonid;
	}
	public void setScore(int score) {
		this.score = score;
	}


	@Override
	protected String go() throws Exception {
		
		try {
			BasicService service = (BasicService) this.getBean("basicService");
			// 先判断这个人是否已经投了票了
			// 投了的话，提示不能再投了
			LessonsService lessonservice = (LessonsService) this.getBean("lessonsService");
			Lessonscore scoreexist = (Lessonscore) lessonservice.getScorebyLessonUser(this.getLoginUser().getUserid(), lessonid);
			if (scoreexist == null) {
				
				Lessonscore lessonscore = new Lessonscore();
				lessonscore.setLessonid(lessonid);
				lessonscore.setScore(score);
				lessonscore.setScoretime(new java.sql.Timestamp(System.currentTimeMillis()));
				lessonscore.setUserid(getLoginUser().getUserid());
				service.save(lessonscore);
			
				code="0";
			}
			else {
				code = "1";
				this.msg = "该课程您已经投票了,投票时间为:" + scoreexist.getScoretime();
			}
		}
		catch (Exception e) {
			code = "-1";
			this.msg = "投票失败,请联系管理员:" + e.getMessage();
		}
		return SUCCESS;

	}

	private String msg;
	private String code;

	public String getMsg() {
		return this.msg;
	}

	public String getCode() {
		return this.code;
	}
}
