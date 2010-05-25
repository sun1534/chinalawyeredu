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
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.context.Globals;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.Lessons;

/**
 * <pre>
 * </pre>
 * 
 * @author 华锋 2009-2-24-下午01:55:46
 */
public class CommonDatas {
	private static Log LOG = LogFactory.getLog(CommonDatas.class);

	public static Map<Integer, String> LessonType = new LinkedHashMap<Integer, String>();
	public static Map<Integer, Map<Integer,String>> ChildLessonType = new LinkedHashMap<Integer, Map<Integer,String>>();
	public static HashMap<Integer, String> LessonState = new LinkedHashMap<Integer, String>();
	public static HashMap<Integer, String> SharedName = new LinkedHashMap<Integer, String>();
	public static HashMap<Integer, String> TeacherType = new LinkedHashMap<Integer, String>();

	public static Map<Integer, String> AllLessonMap = new LinkedHashMap<Integer, String>();
	public static Map<String,Integer> ALL_LOCAL_LESSONS = new LinkedHashMap<String, Integer>();

	static {
//		LessonType.put(1, "律师实务");
//		LessonType.put(2, "新法讲坛");
//		LessonType.put(3, "涉外法务");
//		LessonType.put(4, "思想政治教育");
//		LessonType.put(5, "岗前培训");
//		LessonType.put(6, "专题论坛");
//		LessonType.put(7, "其他");
		
//		LessonType.put(1, "律师实务");
//		LessonType.put(2, "民、商实务");
//		LessonType.put(3, "涉外法务");
//		LessonType.put(4, "思想政治教育");
//		LessonType.put(5, "刑事实务");
//		LessonType.put(6, "行政实务");
//		LessonType.put(7, "其他");
		LessonType.put(1, "民商事法律业务");
		LessonType.put(2, "刑事法律业务");
		LessonType.put(3, "行政法律业务");
		LessonType.put(5, "执行法律业务");
		LessonType.put(6, "律师事务所管理");		
		LessonType.put(8, "其他");
		
		Map<Integer,String> map=new HashMap<Integer,String>();
		map.put(101, "民事诉讼");
		map.put(102, "合同担保法律业务");
		map.put(103, "侵权法");
		map.put(104, "物权法");
		map.put(105, "公司证券法律业务");
		map.put(106, "知识产权法律业务");
		map.put(107, "建筑房地产法律业务");
		map.put(108, "破产法律业务");
		map.put(109, "国有资产法律业务");
		map.put(110, "金融保险法律业务");
		map.put(111, "涉外法律业务");
		map.put(112, "其他民商法律业务");
		
		ChildLessonType.put(1, map);
		
		map=new HashMap<Integer,String>();
		map.put(201, "刑法");
		map.put(202, "刑事诉讼");
		ChildLessonType.put(2, map);
		
		map=new HashMap<Integer,String>();
		map.put(301, "行政法");
		map.put(302, "行政诉讼");
		ChildLessonType.put(3, map);
		
		map=new HashMap<Integer,String>();
		map.put(7, "商务礼仪及业务拓展");
		ChildLessonType.put(6, map);
		
		map=new HashMap<Integer,String>();
		map.put(4, "思想政治教育");
		ChildLessonType.put(8, map);

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
				ALL_LOCAL_LESSONS.clear();
				DetachedCriteria dc = DetachedCriteria.forClass(Lessons.class).add(Restrictions.eq("deleteflag",false));

				List list = service.findAllByCriteria(dc);
				int len = list == null ? 0 : list.size();
				for (int i = 0; i < len; i++) {
					Lessons lessons = (Lessons) list.get(i);
//					if(lessons.getLessonstyle()==1||lessons.getLessonstyle()==3)
						ALL_LOCAL_LESSONS.put(lessons.getTitle(), lessons.getLessonid());
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
