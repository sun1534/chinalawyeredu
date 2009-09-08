package com.changpeng.lessons.action;

import java.io.*;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;

import com.changpeng.models.*;

public class LessonsDeleteAction extends AbstractAction {
	private int lessonid;

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	public LessonsDeleteAction() {
		// this.rightCode = "kcguanli";k
	}

	@Override
	protected String go() throws Exception {
		BasicService service = (BasicService) this.getBean("basicService");

		Lessons lesson = (Lessons) service.get(Lessons.class, lessonid);
		lesson.setDeleteflag(true);
		service.update(lesson);
		
		this.message = "课程删除成功";
	//	this.nextPage = "lessonsList.pl";
		this.nextPage="lessonsOnlineList.pl?lessonstyle=2";
		return SUCCESS;
	}
}
