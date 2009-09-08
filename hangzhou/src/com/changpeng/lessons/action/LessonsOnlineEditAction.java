package com.changpeng.lessons.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.changpeng.common.BasicService;
import com.changpeng.common.EducationLocation;
import com.changpeng.common.LessonSync;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.common.util.StringUtil;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lessonsoflog;

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
	
	private List sharedlist;
	public void setSharedlist(List list){
		this.sharedlist=list;
	}
	
	public List getSharedlist(){
		return this.sharedlist;
	}
	
	public LessonsOnlineEditAction(){
		service=(BasicService)this.getBean("basicService");
		this.rightCode="kcguanli";	
	}
	@Override
	protected String go() throws Exception {
		lesson.setLessondate(datestart+" "+hmstart);
		
		if(lesson.getOnlinefile()==null||lesson.getOnlinefile().trim().equals(""))
		{
			
			this.message="在线课程的在线文件不能为空,请输入";
			this.nextPage="lessonsList.pl";
			return SUCCESS;
		}
		lesson.setShared(com.changpeng.lessons.util.LessonsUtil.list2str(sharedlist));
		
		List oldlist=(List)get("sharedlist");
		
		//以前共享不为空但现在为空，则取消他的共享
		if(oldlist!=null&&oldlist.size()!=0&&lesson.getShared().equals("0")){
			lesson.setShared("2");
		}
		
		lesson.setOnlineorlocal("online");
		
		service.update(lesson);	
		//记录日志，以同步数据
		service.execute(" delete from Lessonsoflog where lessonid="+lesson.getLessonid());		
		short flag=2;		
		Lessonsoflog log=new Lessonsoflog();
		log.setLessonid(lesson.getLessonid());
		log.setLessonidOfserver(lesson.getLessonidOfserver());
		log.setFlag((Short.valueOf(flag)));
		service.save(log);
//		for(Object o:com.changpeng.common.CommonDatas.AllLawyers.entrySet()){
//			String key=((java.util.Map.Entry)o).getKey().toString();				
//			Lessonsoflog log=new Lessonsoflog(); 
//			log.setLessonid(lesson.getLessonid());
//			log.setLessonidOfserver(lesson.getLessonid());
//			log.setFlag(flag);
//			log.setFromAddr(key);
//			service.save(log);				
//		}
		
		this.nextPage="lessonsList.pl";
		this.message="在线课程信息修改成功";
		return SUCCESS;
	}
	public String input() throws ServiceException{
		if(com.changpeng.common.CommonDatas.AllLawyers.isEmpty()){
			LessonSync sync=new LessonSync();
			EducationLocation.getLocationEdu();
			
		}
		
		Lessons lesson=(Lessons) service.get(Lessons.class, lessonid);
		
		sharedlist=com.changpeng.lessons.util.LessonsUtil.str2list(lesson.getShared());
		
		System.out.println("lesson.getShared()::"+lesson.getShared());
		
		set("sharedlist",sharedlist);
		
		if(lesson.getFromAddr()!=null&&!com.changpeng.common.Constants.FROMADDR.equals(lesson.getFromAddr())){
			this.message="您无权编辑该课程";
			this.nextPage="lessonsList.pl";
			return "message";
		}
		
		datestart=StringUtil.getDateFromTime(lesson.getLessondate());
		hmstart=StringUtil.getHmFromTime(lesson.getLessondate());
		set("lesson", lesson);
		return INPUT;
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