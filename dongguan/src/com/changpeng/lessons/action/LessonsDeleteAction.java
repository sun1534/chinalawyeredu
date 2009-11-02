package com.changpeng.lessons.action;

import java.io.*;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;

import com.changpeng.models.*;




public class LessonsDeleteAction extends AbstractAction{
	private int lessonid;

	public void setLessonid(int lessonid) {
		this.lessonid=lessonid;
	}
	public LessonsDeleteAction(){
		this.rightCode="kcguanli";	
	}
	@Override
	protected String go() throws Exception {
		BasicService service=(BasicService)this.getBean("basicService");
		
		Lessons lesson=(Lessons)service.get(Lessons.class, lessonid);
		String attach=lesson.getAttach();
		if(attach!=null){
			String file[]=attach.split(",");
			for(String str:file){
				if(str!=null&&!"".equals(str.trim())){
					try{
						File _file=new File(ServletActionContext.getServletContext().getRealPath("uploads")+"/"+str);
						debug("删除附件:"+str);
						FileUtils.forceDelete(_file);
					}catch(IOException e){
						debug("删除附件失败："+e);
					}	
				}
			}
		}
	
		service.delete(lesson);
		this.message="课程删除成功";
		this.nextPage="lessonsList.pl";
		return SUCCESS;
	}
}
