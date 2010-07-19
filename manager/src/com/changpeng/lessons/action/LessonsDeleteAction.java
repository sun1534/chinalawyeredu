package com.changpeng.lessons.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lessons;

public class LessonsDeleteAction extends AbstractAction {
	private int lessonid;
	private String type = "local";

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
		
		if(lesson.getTeacherid()!=0){
			if(lesson.getTeacherid()!=0){
				String sql="update teacher set lessoncount=lessoncount-1 where userid="+lesson.getTeacherid();
				basicService.executeSql(sql);
			}
		}
		
		// lesson.setDeleteflag(true);
		// service.update(lesson);
		service.delete(lesson);
		this.opResult += "删除课程:" + lesson.getTitle() + ",类型:" + lesson.getLessonstyle();
		this.message = "课程删除成功";
		// this.nextPage = "lessonsList.pl";
		if (type != null && type.equals("online"))
			this.nextPage = "lessonsOnlineList.pl?lessonstyle=2&pageNo=" + pageNo;
		else if (type != null && type.equals("teacher"))
			this.nextPage = "teacherLessonsList.pl?pageNo=" + pageNo;
		else
			this.nextPage = "lessonsLocalList.pl?lessonstyle=1&pageNo=" + pageNo;
		return SUCCESS;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
