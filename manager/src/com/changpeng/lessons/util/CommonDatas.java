/**
 * 
 */
package com.changpeng.lessons.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;

import com.changpeng.common.BasicService;
import com.changpeng.common.context.Globals;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.Lessons;
import com.changpeng.models.SysGroup;
import com.changpeng.system.service.SysLoginLogService;

/**
 * <pre>
 * </pre>
 * 
 * @author 华锋 2009-2-24-下午01:55:46
 */
public class CommonDatas {
	private static Log LOG = LogFactory.getLog(CommonDatas.class);

	public static HashMap<Integer, String> LessonType = new LinkedHashMap<Integer, String>();
	public static HashMap<Integer, String> LessonState = new LinkedHashMap<Integer, String>();
	public static HashMap<Integer, String> SharedName = new LinkedHashMap<Integer, String>();
	public static HashMap<Integer, String> TeacherType = new LinkedHashMap<Integer, String>();

	public static Map<Integer, String> AllLessonMap = new LinkedHashMap<Integer, String>();

	static {
//		LessonType.put(1, "律师实务");
//		LessonType.put(2, "新法讲坛");
//		LessonType.put(3, "涉外法务");
//		LessonType.put(4, "思想政治教育");
//		LessonType.put(5, "岗前培训");
//		LessonType.put(6, "专题论坛");
//		LessonType.put(7, "其他");
		
		LessonType.put(1, "律师实务");
		LessonType.put(2, "民、商实务");
		LessonType.put(3, "涉外法务");
		LessonType.put(4, "思想政治教育");
		LessonType.put(5, "刑事实务");
		LessonType.put(6, "行政实务");
		LessonType.put(7, "其他");

		LessonState.put(1, "内容编辑");
		LessonState.put(2, "培训课程");
		LessonState.put(3, "往期课程");
		LessonState.put(4, "课程结束");
		// 0: 1: 2:取消了共享
		SharedName.put(1, "不共享");
		SharedName.put(2, "共享");
		SharedName.put(3, "被取消");

		TeacherType.put(1, "律师");
		TeacherType.put(2, "法学家");
		TeacherType.put(3, "法官");
		TeacherType.put(4, "政府官员");
		TeacherType.put(5, "外籍人士");
		TeacherType.put(6, "其他");
	}

	public static void getAlllessons() {
		long now = System.currentTimeMillis();
		// if (now - lessonInteractive > 60 * 60 * 60 * 1000) { // 每60分钟
		try {
			BasicService service = (BasicService) Globals.getBean("basicService");
			synchronized (AllLessonMap) {
				int size = AllLessonMap.size();
				AllLessonMap.clear();
				DetachedCriteria dc = DetachedCriteria.forClass(Lessons.class);

				List list = service.findAllByCriteria(dc);
				int len = list == null ? 0 : list.size();
				for (int i = 0; i < len; i++) {
					Lessons lessons = (Lessons) list.get(i);
					AllLessonMap.put(lessons.getLessonid(), lessons.getTitle());
				}
				LOG.info("课程更新成功!::前:" + size + "后:" + AllLessonMap.size());
			}
		} catch (ServiceException e) {
			LOG.error("获取所有课程失败" + e);
		}
		lessonInteractive = now;
		// }
	}

	private static long lessonInteractive = 0;
}
