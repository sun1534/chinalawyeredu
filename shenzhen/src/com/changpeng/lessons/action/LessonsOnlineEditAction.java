package com.changpeng.lessons.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.common.util.StringUtil;
import com.changpeng.models.Lessons;

public class LessonsOnlineEditAction extends AbstractAction{
	private int lessonid;
	private Lessons lesson;
	private BasicService service;
	private String datestart;
	private String hmstart;
	public String getDatestart() {
		return datestart;
	}
	public void setDatestart(String datestart) {
		this.datestart = datestart;
	}
	public String getHmstart() {
		return hmstart;
	}
	public void setHmstart(String hmstart) {
		this.hmstart = hmstart;
	}
	public Lessons getLesson(){
		if(lesson==null)
			lesson= (Lessons)get("lesson");
		return lesson;
		
	}
	public void setLessonid(int lessonid) {
		this.lessonid=lessonid;
	}
	
	public LessonsOnlineEditAction(){
		service=(BasicService)this.getBean("basicService");
		this.rightCode="kcguanli";	
	}
	@Override
	protected String go() throws Exception {
		lesson.setLessondate(datestart+" "+hmstart);
		
		if(lesson.getOnlinefile()==null||lesson.getOnlinefile().trim().equals(""))
			lesson.setOnlinefile(null);

		service.update(lesson);	
		this.nextPage="lessonsList.pl";
		this.message="在线课程信息修改成功";
		return SUCCESS;
	}
	public String input() throws ServiceException{
		Lessons lesson=(Lessons) service.get(Lessons.class, lessonid);
		datestart=StringUtil.getDateFromTime(lesson.getLessondate());
		hmstart=StringUtil.getHmFromTime(lesson.getLessondate());
		set("lesson", lesson);
		return INPUT;
	}
}