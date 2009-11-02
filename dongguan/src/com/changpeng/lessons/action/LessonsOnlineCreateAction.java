
package com.changpeng.lessons.action;

import java.text.SimpleDateFormat;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lessons;

public class LessonsOnlineCreateAction extends AbstractAction {
	private Lessons lesson;
	
//	private File[] file;
//	private String[] fileName;
//	private String[] contentType;
	
	private String datestart;
	private String hmstart;
//	private String dateend;
//	private String hmend;

	public String getDatestart() {
		return datestart;
	}
//
	public String getHmstart() {
		return hmstart;
	}
//
//	public String getDateend() {
//		return dateend;
//	}
//
//	public String getHmend() {
//		return hmend;
//	}
//
	public void setDatestart(String datestart) {
		this.datestart = datestart;
	}
//
	public void setHmstart(String hmstart) {
		this.hmstart = hmstart;
	}
//
//	public void setDateend(String dateend) {
//		this.dateend = dateend;
//	}
//
//	public void setHmend(String hmend) {
//		this.hmend = hmend;
//	}

	public Lessons getLesson() {
		return lesson;
	}

	public LessonsOnlineCreateAction() {
		this.rightCode = "kcguanli";
		this.lesson = new Lessons();
	}

	@Override
	protected String go() throws Exception {
		BasicService service = (BasicService) this.getBean("basicService");
		lesson.setLessondate(datestart + " " + hmstart);
//		lesson.setLessonend(dateend + " " + hmend);
		
//	String	datestart = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
//	String	hmstart = "00:00";
		
	//这个的目的是供查询年份使用
//		lesson.setLessondate(datestart + " " + hmstart);
		lesson.setCreateuser(getLoginUser().getUserid());
		lesson.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		// lesson.setIsbixiu(true);

		lesson.setLessonid((int)(System.currentTimeMillis() / 10000));
		
		if (lesson.getOnlinefile() == null || lesson.getOnlinefile().trim().equals(""))
			lesson.setOnlinefile(null);
		
		
//		String attach="";
//		String extendPath="/uploads/";
//		String toPath=ServletActionContext.getServletContext().getRealPath("")+extendPath;
//		FileUtils.forceMkdir(new File(toPath)); //创建目录
//		int i=0;
//		if(fileName!=null){
//			for(String str:fileName){			
//				if(str!=null&&!"".equals(str)){
//					
//					String name=new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date())+i;
//					
//					String ext=getExtention(str);		
//					String filename=name+ext;
//					try {					
//						File dest=new File(toPath+filename);
//						FileUtils.copyFile(file[i], dest); //移动文件			
//					}catch(IOException e){
//						message = "上传培训资料错误："+e.getMessage();
//						return "message";
//					}
//					attach+=","+filename;
//				}			
//				i++;
//				if(i>5){
//					message = "对不起，您最多可上传5份附件";
//					return "message";
//				}
//			}
//		}
//		lesson.setAttach(attach);
		
		service.save(lesson);
		this.nextPage = "lessonsList.pl";
		this.message = "在线课程新增成功";
		return SUCCESS;
	}
	
	public String input() {
		this.lesson.setFenshuoff("100");
		this.lesson.setXuefen(new Float(0.0));
		this.lesson.setKaoqinshichang(new Float(0.0));
		datestart = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
		hmstart = "14:00";
//		dateend = datestart;
//		hmend = "15:00";

		return INPUT;
		
	}

//	private static String getExtention(String fileName){
//    	int pos=fileName.lastIndexOf(".");
//    	return fileName.substring(pos);
//    }
	
//	public void setFile(File[] file) {
//		this.file = file;
//	}
//
//	public void setFileFileName(String[] fileName) {
//		this.fileName = fileName;
//	}
//	public void setFileContentType(String[] contentType) {
//		this.contentType = contentType;
//	}
	

}
