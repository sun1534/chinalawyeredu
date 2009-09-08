package com.changpeng.lessons.action;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.EducationLocation;
import com.changpeng.common.LessonSync;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.common.util.StringUtil;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lessonsoflog;

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
	
	private List sharedlist;
	public void setSharedlist(List list){
		this.sharedlist=list;
	}
	
	public List getSharedlist(){
		return this.sharedlist;
	}
	
	@Override
	protected String go() throws Exception {

		lesson.setLessondate(datestart+" "+hmstart);
		lesson.setLessonend(dateend+" "+hmend);
		
//		if(lesson.getOnlinefile()==null||lesson.getOnlinefile().trim().equals(""))
//			lesson.setOnlinefile(null);
		if (lesson.getOnlinefile() == null || lesson.getOnlinefile().trim().equals("")){
			lesson.setOnlinefile(null);
		    lesson.setOnlineorlocal("local");
		    lesson.setShared("2");
	 	}else{
			lesson.setOnlineorlocal("all");
			lesson.setShared(com.changpeng.lessons.util.LessonsUtil.list2str(sharedlist));
		}
	    
		
		List oldlist=(List)get("sharedlist");
		
		//以前共享不为空但现在为空，则取消他的共享
		if((oldlist!=null&&oldlist.size()!=0&&lesson.getShared().equals("0"))){
			lesson.setShared("2");
		}
		
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
		
		if(lesson.getOnlineorlocal().equals("all")){
			Lessonsoflog log=new Lessonsoflog();
			log.setLessonid(lesson.getLessonid());
			log.setLessonidOfserver(lesson.getLessonidOfserver());
			short flag=2; //修改
			log.setFlag((Short.valueOf(flag)));
			service.save(log);
		}
		
		this.nextPage="lessonsList.pl";
		this.message="课程信息修改成功";
		return SUCCESS;
	}
	
	private String display="none";
	public String getDisplay(){
		return this.display;
	}
	
	public String input() throws ServiceException{
		
		if(com.changpeng.common.CommonDatas.AllLawyers.isEmpty()){
			LessonSync sync=new LessonSync();
			EducationLocation.getLocationEdu();
			
		}
		
		Lessons lesson=(Lessons) service.get(Lessons.class, lessonid);
		
		if(lesson.getFromAddr()!=null&&!com.changpeng.common.Constants.FROMADDR.equals(lesson.getFromAddr())){
			this.message="课程是共享课程,您无权编辑该课程";
			this.nextPage="lessonsList.pl";
			return "message";
		}

		if(lesson.getOnlinefile()!=null&&!lesson.getOnlinefile().equals(""))
			display="";
		
		sharedlist=com.changpeng.lessons.util.LessonsUtil.str2list(lesson.getShared());
		
		set("sharedlist",sharedlist);
		
		datestart=StringUtil.getDateFromTime(lesson.getLessondate());
		hmstart=StringUtil.getHmFromTime(lesson.getLessondate());
		dateend=StringUtil.getDateFromTime(lesson.getLessonend());
		hmend=StringUtil.getHmFromTime(lesson.getLessonend());
	//	if(lesson.getLessoncontent()==null||lesson.getLessoncontent().equals("")){
		
		//纯粹的在线课程的话，进在线课程列表
		if(lesson.getOnlineorlocal().equals("online")){
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
	
	private Map allLawyers=new LinkedHashMap();
	public Map getAllLawyers(){
		
	  Set set =com.changpeng.common.CommonDatas.AllLawyers.keySet();
	 for(Object obj:set){
		 if(!obj.toString().equals(com.changpeng.common.Constants.FROMADDR))
			 allLawyers.put(obj, com.changpeng.common.CommonDatas.AllLawyers.get(obj));
	 }
	  return allLawyers;
	}
}
