package com.changpeng.lessons.action.ajax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lessons;

public class GetLessonsByIdAction extends AbstractAction {

	private static Log _LOG = LogFactory.getLog(GetLessonsByIdAction.class);
	private int lessonid;

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	private Lessons lessons;
	private int exist;

	/**
	 * @return the exist
	 */
	public int getExist() {
		return exist;
	}

	/**
	 * @return the lessons
	 */
	public Lessons getLessons() {
		return lessons;
	}

	@Override
	protected String go() throws Exception {

		try {

			this.lessons = (Lessons) basicService.get(Lessons.class, lessonid);
			if (lessons != null)
				exist = 1;
		} catch (Exception e) {
			exist = 0;
			_LOG.error("根据id获取课程数据失败:", e);
		}

		return SUCCESS;

	}

}
