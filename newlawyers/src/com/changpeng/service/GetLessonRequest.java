/**
 * GetLessonRequest.java
 */

package com.changpeng.service;

import java.text.DateFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.context.Globals;
import com.changpeng.models.Lessons;

/**
 * @author 华锋 2008-5-12 下午03:12:21
 * 
 */
public class GetLessonRequest extends ElearningRequests {
	private static final Log LOG = LogFactory.getLog(GetLessonRequest.class);
	private int groupid;
private static DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public GetLessonRequest(int groupid) {
		this.groupid = groupid;
	}

	public String requestService(org.dom4j.Element rootElement) {
		StringBuilder result = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		StringBuilder lessontemp = new StringBuilder();
		result.append("<response>");
		try {
			BasicService userservice = (BasicService) Globals.getBean("basicService");
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lessons.class);
			// 该律协的课程
			detachedCriteria.add(Restrictions.eq("groupid", groupid));
			// 不是在线课程
			detachedCriteria.add(Restrictions.ne("lessonstyle", 2));

			List list = userservice.findAllByCriteria(detachedCriteria);
			int lessonsize = list == null ? 0 : list.size();
			temp.append("<respcode>").append(lessonsize).append("</respcode>");
			temp.append("<respmsg>").append("有" + lessonsize + "个课程").append("</respmsg>");
			lessontemp.append("<lessons>");
			for (int i = 0; i < lessonsize; i++) {
				Lessons lesson = (Lessons) list.get(i);
				lessontemp.append("<lesson>");
				// 0:内容编辑,1:培训课程,2:往期课程,3:课程结束
				String lessonstate = "培训课程";
				if (lesson.getLessonstate() == 0)
					lessonstate = "内容编辑";
				else if (lesson.getLessonstate() == 1)
					lessonstate = "培训课程";
				else if (lesson.getLessonstate() == 2)
					lessonstate = "往期课程";
				else if (lesson.getLessonstate() == 3)
					lessonstate = "课程结束";

				lessontemp.append("<lessonid>").append(lesson.getLessonid()).append("</lessonid>");
				lessontemp.append("<title>").append(lesson.getTitle()).append("</title>");
				lessontemp.append("<lessontype>").append(lessonstate).append("</lessontype>");
				lessontemp.append("<lessondate>").append(lesson.getLessondate()==null?df.format(new java.util.Date()):df.format(lesson.getLessondate())).append("</lessondate>");
				lessontemp.append("<reqminute>0</reqminute>");

				lessontemp.append("</lesson>");

			}
			lessontemp.append("</lessons>");
		} catch (Exception e) {
			LOG.error("获取课程信息异常:",e);
			result.append("<respcode>").append(-1).append("</respcode>");
			result.append("<respmsg>获取课程信息异常:").append(e.getMessage()).append("</respmsg>");
		}
		result.append(temp);
		result.append(lessontemp);

		result.append("</response>");
		return result.toString();
	}
}