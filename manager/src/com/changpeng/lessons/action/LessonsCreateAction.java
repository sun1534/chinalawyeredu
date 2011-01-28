package com.changpeng.lessons.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.lessons.service.LessonsService;
import com.changpeng.lessons.util.LessonsUtil;
import com.changpeng.models.Lessons;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysRole;
import com.changpeng.models.SysUser;
import com.changpeng.models.Teacher;
import com.changpeng.system.service.SysGroupService;

/**
 * 
 * 
 * 这是新增现场课程,如果填的onlinefile不为空的话,就也是在线视频
 * 
 * @author 华锋
 * 
 */
public class LessonsCreateAction extends AbstractAction {
	private Lessons lesson;

	private File[] file;
	private String[] fileFileName;
	
	private File picpreview;
	private String picpreviewFileName;
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
		this.lesson = new Lessons();
		this.datavisible = new DataVisible();
	}

	@Override
	protected String go() throws Exception {

		LessonsService service = (LessonsService) this.getBean("lessonsService");

		lesson.setLessondate(LessonsUtil.str2timestamp(datestart + " " + hmstart));
		if (onlyonline != 0) {
			lesson.setLessonend(lesson.getLessondate());
			if (lesson.getOnlinefile() == null || lesson.getOnlinefile().equals("")) {
				this.message = "在线文件不能为空,请返回";
				return "message";
			}
			// if(onlyonline==2){
			if (lesson.getTeacherid() != 0) {
				Teacher teacher = (Teacher) basicService.get(Teacher.class, lesson.getTeacherid());
				lesson.setTeachers(teacher.getUsername());
				lesson.setTeachertype(teacher.getTeacherType());
			}

		} else {
			lesson.setLessonend(LessonsUtil.str2timestamp(dateend + " " + hmend));
		}

		lesson.setCreateuserid(this.getLoginUser().getUserid());
		lesson.setCreateuser(this.getLoginUser().getUsername());
		lesson.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));

		// 如果登录的对应的grouptype=4或5或者为空的话，则用下面的，否则，谁登陆算谁的
		SysGroup mygroup = this.getLoginUser().getSysGroup();

		if (mygroup == null || mygroup.getGrouptype() > 3) {

			if (datavisible.getProvinceid() != 0) {
				lesson.setProvinceid(datavisible.getProvinceid());
				lesson.setGroupid(lesson.getProvinceid());
			}
			if (datavisible.getCityid() != 0) {
				lesson.setCityid(datavisible.getCityid());
				lesson.setGroupid(lesson.getCityid());
			}
			if (datavisible.getProvinceid() == 0 && datavisible.getCityid() == 0) {
				lesson.setGroupid(mygroup == null ? 0 : mygroup.getGroupid());
			}
		} else {
			lesson.setGroupid(mygroup.getGroupid());
			lesson.setProvinceid(this.getLoginUser().getProvinceid());
			lesson.setCityid(this.getLoginUser().getCityid());
		}

		lesson.setDeleteflag(false);

		if (lesson.getOnlinefile() == null || lesson.getOnlinefile().trim().equals("")) {
			lesson.setOnlinefile(null);
			lesson.setLessonstyle(1);
		} else {
			lesson.setLessonstyle(3); // 既是现场培训的,又是在线的
		}
		if (onlyonline != 0) { // 通过添加在线课程的时候实现
			lesson.setLessonstyle(2);
		}

		String attach = "";
		String extendPath = "/lesson/";
		String toPath = com.changpeng.common.Constants.PHOTO_SAVE_PATH+extendPath;
		FileUtils.forceMkdir(new File(toPath)); // 创建目录
		int i = 0;
		if (fileFileName != null) {
			for (String str : fileFileName) {
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
		
		
		System.out.println(picpreview);
		System.out.println(picpreviewFileName);
		
		if (picpreview != null) {
		
				
					String name = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()) + "pic";
					String ext = getExtention(picpreviewFileName);
					String filename = name + ext;
					try {
						File dest = new File(toPath + filename);
						FileUtils.copyFile(picpreview, dest); // 移动文件
						lesson.setPic(name+ext);
					} catch (IOException e) {
						message = "上传培训资料错误：" + e.getMessage();
						return "message";
					}
		}
	
		
		
		
		lesson.setAttach(attach);
//		if (sharedgroupids == null || sharedgroupids.size() == 0) {
//			sharedgroupids = new ArrayList();
//		}
//		if (mygroup != null)
//			sharedgroupids.add(mygroup.getGroupid());
//		if(!sharedgroupids.contains(lesson.getGroupid())&&lesson.getGroupid()!=0){
//			sharedgroupids.add(lesson.getGroupid());
//		} 
//		if(!sharedgroupids.contains(lesson.getProvinceid())&&lesson.getProvinceid()!=0){
//			sharedgroupids.add(lesson.getProvinceid());
//		}
		
		service.saveLesson(lesson, null, this.getLoginUser());

		// System.out.println("onlyonline=============="+onlyonline);

		if (onlyonline == 0) {
			this.nextPage = "lessonsLocalList.pl?lessonstyle=1";
		} else if (onlyonline == 1) {
			this.nextPage = "lessonsOnlineList.pl?lessonstyle=2";
		} else {
			this.nextPage = "teacherLessonsList.pl";
		}
		this.opResult += "新增课程,名称:" + lesson.getTitle();
		this.message = "课程新增成功";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		SysGroupService groupservice = (SysGroupService) this.getBean("sysGroupService");
		SysGroup mygroup = this.getLoginUser().getSysGroup();
		// 要共享给哪些个课程
//		shouldsharedgroupids = groupservice.getAllsharedunion();
//		if (shouldsharedgroupids != null && shouldsharedgroupids.size() != 0) {
//			// 课程共享的时候，去掉自己
//			if (mygroup != null) {
//				for (Object obj : shouldsharedgroupids) {
//					SysGroup group = (SysGroup) obj;
//					if (group.getGroupid() == mygroup.getGroupid()) {
//						shouldsharedgroupids.remove(group);
//						break;
//					}
//				}
//			}
//		}
		if (mygroup == null || mygroup.getGrouptype() > 3) {
			this.datavisible.getVisibleDatas(this.getLoginUser(), false);
			shouldview = true;
		} else {
			shouldview = false;
		}

		this.lesson.setFenshuoff("100");
		this.lesson.setXuefen(new Float(0.0));
		datestart = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

		hmstart = "14:00";
		dateend = datestart;
		hmend = "15:00";

		if (onlyonline == 0) {
			return "local";
		}
		if (onlyonline == 2 || onlyonline == 1) {// 新增授课律师的课程
			SysUser loginuser = this.getLoginUser();
			SysRole loginrole = loginuser.getSysRole();
			// System.out.println("==============================="+loginrole);
			if (loginrole != null && loginrole.getRoleid() == 100) {// 授课律师登录
				listall = false;
				lesson.setTeacherid(loginuser.getUserid());
			} else {
				BasicService basicservice = (BasicService) this.getBean("basicService");
				DetachedCriteria dc=DetachedCriteria.forClass(Teacher.class);
				if(teachername!=null&&!teachername.equals("")){
					dc.add(Restrictions.like("username",teachername,MatchMode.ANYWHERE));
				}
				teacherList = basicservice.findAllByCriteria(dc);
			}
		}
		return "online";
	}

	private String teachername;
	
	private boolean listall = true;

	public boolean getListall() {
		return this.listall;
	}

	private List teacherList;

	public List getTeacherList() {
		return this.teacherList;
	}

	private static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	/**
	 * 课程的来源,其实也应该是省律协或者市律协，共享的时候，设置共享给所有的省律协以及购买了系统的
	 */
//	private List shouldsharedgroupids;

	/**
	 * @return the sharedgroupids
	 */
//	public List getShouldsharedgroupids() {
//		return shouldsharedgroupids;
//	}

//	private List sharedgroupids;

	/**
	 * @param sharedgroupids
	 *            the sharedgroupids to set
	 */
//	public void setSharedgroupids(List sharedgroupids) {
//		this.sharedgroupids = sharedgroupids;
//	}

	private boolean shouldview;

	public boolean getShouldview() {
		return this.shouldview;
	}

	// onlyonline=1为在线,为0为现场课程
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

	/**
	 * @return the teachername
	 */
	public String getTeachername() {
		return teachername;
	}

	/**
	 * @param teachername the teachername to set
	 */
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	/**
	 * @return the picpreview
	 */
	public File getPicpreview() {
		return picpreview;
	}

	/**
	 * @param picpreview the picpreview to set
	 */
	public void setPicpreview(File picpreview) {
		this.picpreview = picpreview;
	}

	/**
	 * @param picpreviewFileName the picpreviewFileName to set
	 */
	public void setPicpreviewFileName(String picpreviewFileName) {
		this.picpreviewFileName = picpreviewFileName;
	}



}
