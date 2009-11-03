/**
 * LessonbaomingAction.java
 */
package com.changpeng.lessons.action.ajax;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.lessons.service.LessonsService;
import com.changpeng.models.Lessonbaoming;
import com.changpeng.models.Lessons;

/**
 * 课程报名
 * @author 华锋
 * 2008-5-18 下午03:09:39
 *
 */
public class LessonBaomingAction extends AbstractAction{

	
	private int lessonid;
	
	private String msg;
	private String code;

	public String getMsg() {
		return this.msg;
	}

	public String getCode() {
		return this.code;
	}

	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		try {
			BasicService service = (BasicService) this.getBean("basicService");
			// 先判断这个人是否已经投了票了
			// 头了的话，提示不能再头了
			LessonsService lessonservice = (LessonsService) this.getBean("lessonsService");
			Lessonbaoming baomingexist = (Lessonbaoming) lessonservice.getBaomingbyLessonUser(this.getLoginUser().getUserid(), lessonid);
			if (baomingexist == null) {

				// 这里为什么会报错 没弄明白

				Lessons lesson = (Lessons) service.get(Lessons.class, lessonid);

				Lessonbaoming baoming = new Lessonbaoming();
				baoming.setLessons(lesson);
			
				baoming.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				baoming.setUserid(getLoginUser().getUserid());
				service.save(baoming);
this.code="0";
			
			}
			else {
				code = "1";
				this.msg = "该课程您已经报名了,报名时间为:" + baomingexist.getCreatetime();
			}
		}
		catch (Exception e) {
			code = "-1";
			this.msg = "报名失败,请联系管理员:" + e.getMessage();
		}
		return SUCCESS;
	}

	/**
	 * @param lessonid the lessonid to set
	 */
	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}
	
	

}
