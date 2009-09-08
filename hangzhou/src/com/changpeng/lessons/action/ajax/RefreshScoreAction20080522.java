
package com.changpeng.lessons.action.ajax;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.lessons.service.LessonsService;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lessonscore;

public class RefreshScoreAction20080522 extends AbstractAction {
	private byte score;
	private int lessonid;

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	public void setScore(byte score) {
		this.score = score;
	}

	private int common; // 评价一般票数
	private int preferably; // 评价较好票数
	private int good; // 评价很好票数

	private int commonl; // 评价一般票数显示宽度
	private int preferablyl;
	private int goodl;

	public int getCommon() {
		return common;
	}

	public int getPreferably() {
		return preferably;
	}

	public int getGood() {
		return good;
	}

	public int getCommonl() {
		return commonl;
	}

	public int getPreferablyl() {
		return preferablyl;
	}

	public int getGoodl() {
		return goodl;
	}

	public void setCommon(int common) {
		this.common = common;
	}

	public void setPreferably(int preferably) {
		this.preferably = preferably;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public void setCommonl(int commonl) {
		this.commonl = commonl;
	}

	public void setPreferablyl(int preferablyl) {
		this.preferablyl = preferablyl;
	}

	public void setGoodl(int goodl) {
		this.goodl = goodl;
	}

	@Override
	protected String go() throws Exception {

		debug("投票....................");
		try {
			BasicService service = (BasicService) this.getBean("basicService");
			// 先判断这个人是否已经投了票了
			// 头了的话，提示不能再头了
			LessonsService lessonservice = (LessonsService) this.getBean("lessonsService");
			Lessonscore scoreexist = (Lessonscore) lessonservice.getScorebyLessonUser(this.getLoginUser().getUserid(), lessonid);
			if (scoreexist == null) {

			

				Lessons lesson = (Lessons) service.get(Lessons.class, lessonid);

				Lessonscore lessonscore = new Lessonscore();
				lessonscore.setLessons(lesson);
				lessonscore.setScore(score);
				lessonscore.setScoretime(new java.sql.Timestamp(System.currentTimeMillis()));
				lessonscore.setUserid(getLoginUser().getUserid());
				service.save(lessonscore);

				if (score == 1)
					common++;
				else if (score == 2)
					preferably++;
				else if (score == 3)
					good++;

				int all = common + preferably + good;
				if (all == 0)
					commonl = preferablyl = goodl = 30;
				else {
					commonl = 30 + ( 250*common) / all;
					preferablyl = 30 + ( 250*preferably) / all;
					goodl = 30 + (250*good) / all;
				}
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
