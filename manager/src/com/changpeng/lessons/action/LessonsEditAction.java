package com.changpeng.lessons.action;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.lessons.service.LessonsService;
import com.changpeng.lessons.util.LessonsUtil;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lessonshared;
import com.changpeng.models.SysGroup;
import com.changpeng.system.service.SysGroupService;

/**
 * 课程的修改,不考虑共享的情况。设置一个独立的共享功能
 * 
 * @author 华锋
 * 
 */
public class LessonsEditAction extends AbstractAction {
	private int lessonid;
	private Lessons lesson;
	private BasicService service;
	private String datestart;
	private String hmstart;
	private String dateend;
	private String hmend;

	private List<String> filelist;
	private boolean hasattach;

	public boolean getHasattach() {
		return hasattach;
	}

	private File[] file;
	private String[] fileName;

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

	public Lessons getLesson() {
		if (lesson == null)
			lesson = (Lessons) get("lesson");
		return lesson;

	}

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	public LessonsEditAction() {
		service = (BasicService) this.getBean("basicService");
		datavisible = new DataVisible();
	}

	@Override
	protected String go() throws Exception {
		LessonsService service = (LessonsService) this.getBean("lessonsService");
		lesson.setLessondate(LessonsUtil.str2timestamp(datestart + " " + hmstart));
		if (onlyonline == 1) {
			lesson.setLessonend(lesson.getLessondate());
			if (lesson.getOnlinefile() == null || lesson.getOnlinefile().equals("")) {
				this.message = "在线文件不能为空,请返回";
				return "message";
			}

		} else
			lesson.setLessonend(LessonsUtil.str2timestamp(dateend + " " + hmend));

		// 如果登录的对应的grouptype=4或5或者为空的话，则用下面的，否则，谁登陆算谁的
		SysGroup mygroup = this.getLoginUser().getSysGroup();

		if (mygroup == null || mygroup.getGrouptype() > 3) {

			if (datavisible.getProvinceid() != 0) {
				lesson.setProvinceid(datavisible.getProvinceid());
				lesson.setGroupid(lesson.getProvinceid());
			}
			if (datavisible.getCityid() != 0) {
				lesson.setCityid(datavisible.getCityid());
				lesson.setCityid(lesson.getCityid());
			}
		} else {
			lesson.setGroupid(mygroup.getGroupid());
			lesson.setProvinceid(this.getLoginUser().getProvinceid());
			lesson.setCityid(this.getLoginUser().getCityid());
			// 要把自己给加进去啊
			// if(sharedgroupids==null)
			// sharedgroupids=new ArrayList();
			//			
		}

		String attach = lesson.getAttach();
		String extendPath = "/uploads/";
		String toPath = ServletActionContext.getServletContext().getRealPath("") + extendPath;
		FileUtils.forceMkdir(new File(toPath)); // 创建目录
		if (attach == null)
			attach = "";
		int index = 0;
		if (fileName != null) {
			for (String str : fileName) {
				if (str != null && !"".equals(str)) {
					String name = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()) + index;

					String ext = getExtention(str);
					String filename = name + ext;
					try {
						File dest = new File(toPath + filename);
						FileUtils.copyFile(file[index], dest); // 移动文件
					} catch (IOException e) {
						message = "上传培训资料错误：" + e.getMessage();
						return "message";
					}
					attach += "," + filename;
				}
				index++;
			}
		}

		int i = 0;
		String file[] = attach.split(",");
		for (String str : file) {
			if (str != null && !"".equals(str.trim()))
				i++;
		}
		if (i > 5) {
			message = "对不起，您最多可上传5份附件";
			return "message";
		}

		if (sharedgroupids == null || sharedgroupids.size() == 0) {
			sharedgroupids = new ArrayList();
		}
		if (mygroup != null)
			sharedgroupids.add(mygroup.getGroupid());

		lesson.setAttach(attach);
		// service.update(lesson);
		service.updateLesson(lesson, sharedgroupids, this.getLoginUser());
		// this.nextPage = "lessonsList.pl";
		if (onlyonline == 1)
			this.nextPage = "lessonsOnlineList.pl?lessonstyle=2&pageNo=" + pageNo;
		else
			this.nextPage = "lessonsLocalList.pl?lessonstyle=1&pageNo=" + pageNo;
		this.message = "课程信息修改成功";
		
		
		this.opResult+="修改课程,名称:"+lesson.getTitle();
		
		return SUCCESS;
	}

	private String display = "none";

	public String getDisplay() {
		return this.display;
	}

	public String input() throws ServiceException {
		Lessons lesson = (Lessons) service.get(Lessons.class, lessonid);
		SysGroup mygroup = this.getLoginUser().getSysGroup();
		if (lesson.getOnlinefile() != null && !lesson.getOnlinefile().equals(""))
			display = "";
		SysGroupService groupservice = (SysGroupService) this.getBean("sysGroupService");

		shouldsharedgroupids = groupservice.getAllsharedunion();
		if (shouldsharedgroupids != null && shouldsharedgroupids.size() != 0) {
			// 课程共享的时候，去掉自己
			if (mygroup != null) {
				for (Object obj : shouldsharedgroupids) {
					SysGroup group = (SysGroup) obj;
					if (group.getGroupid() == mygroup.getGroupid()) {
						shouldsharedgroupids.remove(group);
						break;
					}
				}
			}
		}

		sharedgroupids = new ArrayList();
		DetachedCriteria dc = DetachedCriteria.forClass(Lessonshared.class);
		dc.createAlias("lessons", "lessons");
		dc.add(Restrictions.eq("lessons.lessonid", lessonid));
		List list = basicService.findAllByCriteria(dc);
		for (int i = 0; list != null && i < list.size(); i++) {
			Lessonshared shared = (Lessonshared) list.get(i);
			// System.out.println("==============="+shared.getGroupid());
			if (mygroup == null || (mygroup != null && shared.getGroupid() != mygroup.getGroupid()))
				sharedgroupids.add(shared.getGroupid());
		}

		String s = df.format(lesson.getLessondate());
		int index = s.indexOf(" ");
		datestart = s.substring(0, index);
		hmstart = s.substring(index + 1);
		// LessonsUtil.timestamp2str(lesson.getLessondate(), datestart,
		// hmstart);

		System.out.println(datestart + ",,," + hmstart);

		if (lesson.getLessondate() != null && !lesson.getLessondate().equals("")) {
			s = df.format(lesson.getLessondate());
			index = s.indexOf(" ");
			dateend = s.substring(0, index);
			hmend = s.substring(index + 1);
		}
		// LessonsUtil.timestamp2str(lesson.getLessonend(), dateend, hmend);

		String attach = lesson.getAttach();
		filelist = new java.util.ArrayList<String>();
		if (attach != null) {
			String file[] = attach.split(",");
			for (String str : file) {
				if (str != null && !"".equals(str.trim()))
					filelist.add(str);
			}
		}
		if (filelist.size() > 0)
			hasattach = true;

		set("lesson", lesson);

		if (onlyonline == 1) {
			return "online";
		}
		return "local";

	}

	private List sharedgroupids = new ArrayList();

	/**
	 * @param sharedgroupids
	 *            the sharedgroupids to set
	 */
	public void setSharedgroupids(List sharedgroupids) {
		this.sharedgroupids = sharedgroupids;
	}

	public List getSharedgroupids() {
		return this.sharedgroupids;
	}

	/**
	 * 课程的来源,其实也应该是省律协或者市律协，共享的时候，设置共享给所有的省律协以及购买了系统的
	 */
	private List shouldsharedgroupids;

	/**
	 * @return the sharedgroupids
	 */
	public List getShouldsharedgroupids() {
		return shouldsharedgroupids;
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

	private int onlyonline;

	/**
	 * @return the onlyonline
	 */
	public int getOnlyonline() {
		return onlyonline;
	}

	/**
	 * @param onlyonline
	 *            the onlyonline to set
	 */
	public void setOnlyonline(int onlyonline) {
		this.onlyonline = onlyonline;
	}

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");

}
