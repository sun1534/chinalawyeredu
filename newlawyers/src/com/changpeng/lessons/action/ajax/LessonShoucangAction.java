package com.changpeng.lessons.action.ajax;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.lessons.service.LessonsService;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lessonscore;

public class LessonShoucangAction extends AbstractAction {
	private int lessonid;

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	public int getLessonid() {
		return lessonid;
	}

	@Override
	protected String go() throws Exception {

		try {
			BasicService service = (BasicService) this.getBean("basicService");
			// 先判断这个人是否已经投了票了
			// 投了的话，提示不能再投了
			LessonsService lessonservice = (LessonsService) this.getBean("lessonsService");
			int result = lessonservice.shouchangLesson(this.getLoginUser().getLawyerid(), lessonid);

			if (result == 0)
				msg = "课程收藏成功";
			else
				msg = "该课程您已经收藏过了";
		} catch (Exception e) {
			e.printStackTrace();
			this.msg = "收藏该课程失败,请联系管理员";
		}
		return SUCCESS;

	}

	private String msg;

	public String getMsg() {
		return this.msg;
	}

}
