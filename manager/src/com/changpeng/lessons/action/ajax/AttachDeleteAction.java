
package com.changpeng.lessons.action.ajax;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lessons;

public class AttachDeleteAction extends AbstractAction {
	private int attachs;
	private String filename;
	private int lessonid;
	public void setLessonid(int lessonid) {
		this.lessonid=lessonid;
	}
	public int getAttachs() {
		return attachs;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	protected String go() throws Exception {
		BasicService service=(BasicService)this.getBean("basicService");
		Lessons lesson=(Lessons)service.get(Lessons.class, lessonid);
		String attach=lesson.getAttach();
		
		try{
			File _file=new File(ServletActionContext.getServletContext().getRealPath("uploads")+"/"+filename);
			debug("删除附件:"+filename);
			FileUtils.forceDelete(_file);
		}catch(IOException e){
			debug("删除附件失败："+e);
		}
		
		attachs=0;
		if(attach!=null){
			attach=attach.replace(","+filename, "");
			
			String file[]=attach.split(",");
			for(String str:file){
				if(str!=null&&!"".equals(str.trim()))
					attachs++;
			}
		}		
		
		lesson.setAttach(attach);
		service.update(lesson);
		
		return SUCCESS;		
	}
}
