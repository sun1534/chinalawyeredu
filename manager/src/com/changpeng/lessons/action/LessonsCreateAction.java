package com.changpeng.lessons.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.lessons.service.LessonsService;
import com.changpeng.lessons.util.LessonsUtil;
import com.changpeng.models.Lessons;
import com.changpeng.models.SysGroup;
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
	private String[] fileName;

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
		if (onlyonline == 1) {
			lesson.setLessonend(lesson.getLessondate());
			if(lesson.getOnlinefile()==null||lesson.getOnlinefile().equals("")){
				this.message="在线文件不能为空,请返回";
				return "message";
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
		if (onlyonline == 1) { // 通过添加在线课程的时候实现
			lesson.setLessonstyle(2);
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
		if (sharedgroupids == null || sharedgroupids.size() == 0) {
			sharedgroupids = new ArrayList();
		}
		if (mygroup != null)
			sharedgroupids.add(mygroup.getGroupid());

	
		
		service.saveLesson(lesson, sharedgroupids, this.getLoginUser());
		
//		System.out.println("onlyonline=============="+onlyonline);
		
		
		if ( onlyonline== 0) {
			this.nextPage = "lessonsLocalList.pl?lessonstyle=1";
		}else
			 this.nextPage = "lessonsOnlineList.pl?lessonstyle=2";
		
		this.message = "课程新增成功";
		return SUCCESS;
	}

	@Override
	public String input() {

		SysGroupService groupservice = (SysGroupService) this.getBean("sysGroupService");
		SysGroup mygroup = this.getLoginUser().getSysGroup();
		// 要共享给哪些个课程
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

	
		if (onlyonline == 0)
			return "local";
		return "online";
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

	private List sharedgroupids;

	/**
	 * @param sharedgroupids
	 *            the sharedgroupids to set
	 */
	public void setSharedgroupids(List sharedgroupids) {
		this.sharedgroupids = sharedgroupids;
	}

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

}
