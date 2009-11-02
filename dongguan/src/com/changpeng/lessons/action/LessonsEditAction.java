package com.changpeng.lessons.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.common.util.StringUtil;

import com.changpeng.models.*;

public class LessonsEditAction extends AbstractAction{
	private int lessonid;
	private Lessons lesson;
	private BasicService service;
	private String datestart;
	private String hmstart;
	private String dateend;
	private String hmend;
	
	private List<String> filelist;
	private boolean hasattach;
	public boolean getHasattach(){
		return hasattach;
	}
	
	private File[] file;
	private String[] fileName;
	private String[] contentType;
	private static String getExtention(String fileName){
    	int pos=fileName.lastIndexOf(".");
    	return fileName.substring(pos);
    }
	
	public void setFile(File[] file) {
		this.file = file;
	}

	public void setFileFileName(String[] fileName) {
		this.fileName = fileName;
	}
	public void setFileContentType(String[] contentType) {
		this.contentType = contentType;
	}
	
	public List<String> getFilelist() {
		return filelist;
	}
	
	public String getDatestart() {
		return datestart;
	}
	public String getHmstart() {
		return hmstart;
	}
	public String getDateend() {
		return dateend;
	}
	public String getHmend() {
		return hmend;
	}
	public Lessons getLesson(){
		if(lesson==null)
			lesson= (Lessons)get("lesson");
		return lesson;
		
	}
	public void setLessonid(int lessonid) {
		this.lessonid=lessonid;
	}
	public LessonsEditAction(){
		service=(BasicService)this.getBean("basicService");
		this.rightCode="kcguanli";	
	}
	@Override
	protected String go() throws Exception {

		lesson.setLessondate(datestart+" "+hmstart);
		lesson.setLessonend(dateend+" "+hmend);
		
		if(lesson.getOnlinefile()==null||lesson.getOnlinefile().trim().equals(""))
			lesson.setOnlinefile(null);
		
		
		String attach=lesson.getAttach();
		String extendPath="/uploads/";
		String toPath=ServletActionContext.getServletContext().getRealPath("")+extendPath;
		FileUtils.forceMkdir(new File(toPath)); //创建目录
		if(attach==null) attach="";
		int index=0;
		if(fileName!=null){
			for(String str:fileName){					
				if(str!=null&&!"".equals(str)){					
					String name=new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date())+index;
					
					String ext=getExtention(str);		
					String filename=name+ext;
					try {					
						File dest=new File(toPath+filename);
						FileUtils.copyFile(file[index], dest); //移动文件			
					}catch(IOException e){
						message = "上传培训资料错误："+e.getMessage();
						return "message";
					}
					attach+=","+filename;
				}			
				index++;
			}
		}
		
		int i=0;
		String file[]=attach.split(",");
		for(String str:file){
			if(str!=null&&!"".equals(str.trim()))
				i++;
		}
		if(i>5){
			message = "对不起，您最多可上传5份附件";
			return "message";
		}
		lesson.setAttach(attach);
		
		service.update(lesson);	
		this.nextPage="lessonsList.pl";
		this.message="课程信息修改成功";
		return SUCCESS;
	}
	public String input() throws ServiceException{
		Lessons lesson=(Lessons) service.get(Lessons.class, lessonid);
		datestart=StringUtil.getDateFromTime(lesson.getLessondate());
		hmstart=StringUtil.getHmFromTime(lesson.getLessondate());
		dateend=StringUtil.getDateFromTime(lesson.getLessonend());
		hmend=StringUtil.getHmFromTime(lesson.getLessonend());
		if(lesson.getLessoncontent()==null||lesson.getLessoncontent().equals("")){
			set("lesson", lesson);
			return "online";
		}

		
		String attach=lesson.getAttach();
		filelist=new java.util.ArrayList<String>();
		if(attach!=null){
			String file[]=attach.split(",");
			for(String str:file){
				if(str!=null&&!"".equals(str.trim()))
					filelist.add(str);
			}
		}
		if(filelist.size()>0)
			hasattach=true;
		
		set("lesson", lesson);
		return INPUT;
	}
	public void setDatestart(String datestart) {
		this.datestart = datestart;
	}
	public void setHmstart(String hmstart) {
		this.hmstart = hmstart;
	}
	public void setDateend(String dateend) {
		this.dateend = dateend;
	}
	public void setHmend(String hmend) {
		this.hmend = hmend;
	}
}
