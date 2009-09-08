package com.changpeng.lessons.action;

import java.io.*;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;

import com.changpeng.models.*;

public class LessonsDeleteAction extends AbstractAction {
	private int lessonid;

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	public LessonsDeleteAction() {
		this.rightCode = "kcguanli";
	}

	@Override
	protected String go() throws Exception {
		BasicService service = (BasicService) this.getBean("basicService");

		Lessons lesson = (Lessons) service.get(Lessons.class, lessonid);

		if (lesson.getFromAddr() != null && !com.changpeng.common.Constants.FROMADDR.equals(lesson.getFromAddr())) {
			this.message = "属于共享课程,您无权删除该课程";
			this.nextPage = "lessonsList.pl";
			return "message";
		}

	//	if (lesson.getShared() == 1||lesson.getShared() == 2) { // 之前共享过，后来取消了共享，不做删除，只是状态改变下，如果是在线课程的话，设置取消共享
		if (!lesson.getShared().equals("0")) {
			lesson.setDeleteflag("Y");
			lesson.setShared("2");  //取消共享
			service.update(lesson);

			if (!lesson.getOnlineorlocal().equals("local")) {//在线课程的话，更新下
				// 记录日志，以同步数据
				service.execute(" delete from Lessonsoflog where lessonid=" + lesson.getLessonid());
				short flag = 2; //不做删除,只做取消共享的动作
				for (Object o : com.changpeng.common.CommonDatas.AllLawyers.entrySet()) {
					String key = ((java.util.Map.Entry) o).getKey().toString();
					Lessonsoflog log = new Lessonsoflog();
					log.setLessonid(lesson.getLessonid());
					log.setLessonidOfserver(lesson.getLessonidOfserver());
					log.setFlag(flag);
					log.setFromAddr(key);
					service.save(log);
				}
			}

			this.message = "课程删除成功";
			this.nextPage = "lessonsList.pl";
			return SUCCESS;
		} else {

			String attach = lesson.getAttach();
			if (attach != null) {
				String file[] = attach.split(",");
				for (String str : file) {
					if (str != null && !"".equals(str.trim())) {
						try {
							File _file = new File(ServletActionContext.getServletContext().getRealPath("uploads") + "/" + str);
							debug("删除附件:" + str);
							FileUtils.forceDelete(_file);
						} catch (IOException e) {
							debug("删除附件失败：" + e);
						}
					}
				}
			}

			// if(lesson.getOnlinefile()!=null&&!lesson.getOnlinefile().equals("")){
			// //如果为在线课程，记录日志，以同步数据
			
			
			System.out.println(lesson.getOnlineorlocal());
			if (lesson.getOnlineorlocal().equals("all") || lesson.getOnlineorlocal().equals("online")) {
				// 记录日志，以同步数据
				service.execute(" delete from Lessonsoflog where lessonid=" + lesson.getLessonid());
				short flag = 3;
				for (Object o : com.changpeng.common.CommonDatas.AllLawyers.entrySet()) {
					String key = ((java.util.Map.Entry) o).getKey().toString();
					Lessonsoflog log = new Lessonsoflog();
					log.setLessonid(lesson.getLessonid());
					log.setLessonidOfserver(lesson.getLessonid());
					log.setFlag(flag);
					log.setFromAddr(key);
					service.save(log);
				}
			}
			lesson.setDeleteflag("Y");
//			lesson.setShared("2");  //取消共享
			service.update(lesson);
//			service.delete(lesson);
			this.message = "课程删除成功";
			this.nextPage = "lessonsList.pl";
			return SUCCESS;
		}
	}
}
