package com.changpeng.lessons.action.ajax;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Teacher;

public class GetTeacherByIdAction extends AbstractAction {

	private static Log _LOG = LogFactory.getLog(GetTeacherByIdAction.class);
	private int teacherid;

	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}

	private String teachername;
	private int teachertype;
	
	private int exist;

	/**
	 * @return the exist
	 */
	public int getExist() {
		return exist;
	}

	
	@Override
	protected String go() throws Exception {

		try {

			Teacher teacher = (Teacher) basicService.get(Teacher.class, teacherid);
			if (teacher != null){
				exist = 1;
				teachername=teacher.getUsername();
				teachertype=teacher.getTeacherType();
			}
		} catch (Exception e) {
			exist = 0;
			_LOG.error("根据id获取课程数据失败:", e);
		}

		return SUCCESS;

	}

	/**
	 * @return the teachername
	 */
	public String getTeachername() {
		return teachername;
	}

	/**
	 * @return the teachertype
	 */
	public int getTeachertype() {
		return teachertype;
	}

	
}
