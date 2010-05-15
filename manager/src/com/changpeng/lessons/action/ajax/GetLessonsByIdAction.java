package com.changpeng.lessons.action.ajax;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lessons;

public class GetLessonsByIdAction extends AbstractAction {

	private static Log _LOG = LogFactory.getLog(GetLessonsByIdAction.class);
	private int lessonid;

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	private String title;
	private String xuefen;
	private String lessondatestr;
	private int exist;

	/**
	 * @return the exist
	 */
	public int getExist() {
		return exist;
	}

	private java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	protected String go() throws Exception {

		try {

			Lessons lessons = (Lessons) basicService.get(Lessons.class, lessonid);
			if (lessons != null){
				exist = 1;
				xuefen=lessons.getXuefen()+"";
				title=lessons.getTitle();
				lessondatestr=lessons.getLessondate()==null?df.format(new Date()):df.format(lessons.getLessondate());
			}
		} catch (Exception e) {
			exist = 0;
			_LOG.error("根据id获取课程数据失败:", e);
		}

		return SUCCESS;

	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the xuefen
	 */
	public String getXuefen() {
		return xuefen;
	}

	/**
	 * @return the lessondatestr
	 */
	public String getLessondatestr() {
		return lessondatestr;
	}

}
