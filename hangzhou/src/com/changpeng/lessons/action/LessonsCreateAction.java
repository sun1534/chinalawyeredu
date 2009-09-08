package com.changpeng.lessons.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.changpeng.models.Lessons;
import com.changpeng.models.Lessonsoflog;

public class LessonsCreateAction extends AbstractAction {
	private Lessons lesson;

	private File[] file;
	private String[] fileName;
	private String[] contentType;

	private String datestart;
	private String hmstart;
	private String dateend;
	private String hmend;

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

	public Lessons getLesson() {
		return lesson;
	}

	public LessonsCreateAction() {
		this.rightCode = "kcguanli";
		this.lesson = new Lessons();
	}

	private List sharedlist;

	public void setSharedlist(List list) {
		this.sharedlist = list;
	}

	@Override
	protected String go() throws Exception {
		BasicService service = (BasicService) this.getBean("basicService");
		lesson.setLessondate(datestart + " " + hmstart);
		lesson.setLessonend(dateend + " " + hmend);
		lesson.setCreateuser(getLoginUser().getUserid());
		lesson.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));

		lesson.setShared(com.changpeng.lessons.util.LessonsUtil.list2str(sharedlist));

		// lesson.setIsbixiu(true);

//		lesson.setLessonid((int) (System.currentTimeMillis() / 10000));

		if (lesson.getOnlinefile() == null || lesson.getOnlinefile().trim().equals("")) {
			lesson.setOnlinefile(null);
			lesson.setOnlineorlocal("local");
		} else {
			lesson.setOnlineorlocal("all");
		}

		String attach = "";
		String extendPath = "/uploads/";
		String toPath = ServletActionContext.getServletContext().getRealPath("") + extendPath;
		FileUtils.forceMkdir(new File(toPath)); // 创建目录
		int i = 0;
		if (fileName != null) {
			for (String str : fileName) {
				if (str != null && !"".equals(str)) {

					String name = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()) + i;

					String ext = getExtention(str);
					String filename = name + ext;
					try {
						File dest = new File(toPath + filename);
						FileUtils.copyFile(file[i], dest); // 移动文件
					} catch (IOException e) {
						message = "上传培训资料错误：" + e.getMessage();
						return "message";
					}
					attach += "," + filename;
				}
				i++;
				if (i > 5) {
					message = "对不起，您最多可上传5份附件";
					return "message";
				}
			}
		}
		lesson.setAttach(attach);

		lesson.setFromAddr(com.changpeng.common.Constants.FROMADDR);
		service.save(lesson);

		
		if(lesson.getOnlineorlocal().equals("all")){
			Lessonsoflog log=new Lessonsoflog();
			log.setLessonid(lesson.getLessonid());
			log.setLessonidOfserver(lesson.getLessonidOfserver());
			short flag=1;
			log.setFlag((Short.valueOf(flag)));
			service.save(log);
		}
		
		this.nextPage = "lessonsList.pl";
		this.message = "课程新增成功";
		return SUCCESS;
	}

	public String input() {
		if(com.changpeng.common.CommonDatas.AllLawyers.isEmpty()){
			
			LessonSync sync=new LessonSync();
			EducationLocation.getLocationEdu();
			
		}
		
		this.lesson.setFenshuoff("100");
		this.lesson.setXuefen(new Float(0.0));
		this.lesson.setKaoqinshichang(new Float(0.0));
		datestart = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
		this.lesson.setFromAddr(com.changpeng.common.Constants.FROMADDR);
		hmstart = "14:00";
		dateend = datestart;
		hmend = "15:00";
		if (this.type != null && !"".equals(this.type)) {
			return "online";
		} else {
			return "locale";
		}

	}

	private static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
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

	private String type;

	public void setType(String type) {
		this.type = type;
	}

	private Map allLawyers = new LinkedHashMap();

	public Map getAllLawyers() {

		Set set = com.changpeng.common.CommonDatas.AllLawyers.keySet();
		for (Object obj : set) {
			if (!obj.toString().equals(com.changpeng.common.Constants.FROMADDR))
				allLawyers.put(obj, com.changpeng.common.CommonDatas.AllLawyers.get(obj));
		}
		return allLawyers;
	}
}
