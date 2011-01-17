/**
 * 
 */
package com.changpeng.lessons.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.jdbc.core.JdbcTemplate;

import com.changpeng.common.BasicService;
import com.changpeng.common.context.Globals;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.lessons.service.LessonsService;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lessontype;

/**
 * <pre>
 * </pre>
 * 
 * @author 华锋 2009-2-24-下午01:55:46
 */
public class CommonDatas {
	private static Log LOG = LogFactory.getLog(CommonDatas.class);

	public static Map<Integer, String> LessonType = new LinkedHashMap<Integer, String>();
	public static Map<Integer, String> LessonState = new LinkedHashMap<Integer, String>();
	public static Map<Integer, String> SharedName = new LinkedHashMap<Integer, String>();
	public static Map<Integer, String> TeacherType = new LinkedHashMap<Integer, String>();
	public static Map<Integer, Lessontype> LessonTypeObject = new LinkedHashMap<Integer, Lessontype>();
	public static Map<Integer, Lessons> AllLessons = new LinkedHashMap<Integer, Lessons>();
	public static Map<Integer, String> AllLessonMap = new LinkedHashMap<Integer, String>();
	public static Map<Integer, String> QUALITIES = new LinkedHashMap<Integer, String>();
	public static List<Lessons> LATEST_LESSONS = new ArrayList<Lessons>();
	// 最受欢迎的课程,取6个
	public static List<Lessons> POPULAR_LESSON = new ArrayList<Lessons>();

	public static List<Lessontype> TOP_LEVEL_TYPE = new ArrayList<Lessontype>();
	
	public static List<Lessons> RECOMMAND_LESSONS = new ArrayList<Lessons>();
	static {
		// LessonType.put(1, "律师实务");
		// LessonType.put(2, "民、商实务");
		// LessonType.put(3, "涉外法务");
		// LessonType.put(4, "思想政治教育");
		// LessonType.put(5, "刑事实务");
		// LessonType.put(6, "行政实务");
		// LessonType.put(7, "其他");
		QUALITIES.put(1, "好");
		QUALITIES.put(2, "中");
		QUALITIES.put(3, "差");
		QUALITIES.put(4, "未评");
		// QUALITIES.put(4, "差");
		// QUALITIES.put(5, "较差");

		// LessonType.put(1, "民商事法律业务");
		// LessonType.put(101, "民事诉讼");
		// LessonType.put(102, "合同担保法律业务");
		// LessonType.put(103, "侵权法");
		// LessonType.put(104, "物权法");
		// LessonType.put(105, "公司证券法律业务");
		// LessonType.put(106, "知识产权法律业务");
		// LessonType.put(107, "建筑房地产法律业务");
		// LessonType.put(108, "破产法律业务");
		// LessonType.put(109, "国有资产法律业务");
		// LessonType.put(110, "金融保险法律业务");
		// LessonType.put(111, "涉外法律业务");
		// LessonType.put(113, "劳动关系法律业务");
		// LessonType.put(112, "其他民商法律业务");
		// LessonType.put(2, "刑事法律业务");
		// LessonType.put(201, "刑法");
		// LessonType.put(202, "刑事诉讼");
		// LessonType.put(3, "行政法律业务");
		// LessonType.put(301, "行政法");
		// LessonType.put(302, "行政诉讼");
		// LessonType.put(5, "执行法律业务");
		// LessonType.put(6, "律师事务所管理");
		// LessonType.put(7, "商务礼仪及业务拓展");
		// LessonType.put(8, "其他");
		// LessonType.put(4, "思想政治教育");

		// 改成”律师实务，民、商实务，刑事实务，行政实务，涉外实务，思想政治教育，其它”几个类型
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
	/**
	 * 更新每个课程类型的总数
	 */
	private static String LESSON_TYPE_STATIC_SQL = "update lessontype a inner join (select lessontype,count(lessontype) cnt from lessons where deleteflag=0 and lessonstyle in(2,3) group by lessontype) b	set a.lessoncnt=b.cnt where a.typeid=b.lessontype";

	/**
	 * 更新每个课程的分数，其中3代表10分，2代表8，1代表7分，取小数点2位
	 */
	private static String LESSON_SCORE_STATIC_SQL = "update lessons a inner join(select lessonid,format(sum((case when score = 3 then 10 when score=2 then 8 else 7 end ))/count(lessonid),2) as fenshu from lessonscore group by lessonid)b set a.fenshu=b.fenshu where a.lessonid=b.lessonid";
	/**
	 * 更新每个课程的回复数
	 */
	private static String LESSON_REPLY_STATIC_SQL = "update lessons a inner join(select lessonid,count(lessonid) as replycnt from lessonreply group by lessonid)b set a.replycnt=b.replycnt where a.lessonid=b.lessonid";

	/**
	 * 课程听课数统计
	 */
	private static String LESSON_LISTEN_STATIC_SQL = "update lessons a inner join(select count(lessonid) as listencnt,lessonid from log_lesson_listen group by lessonid)b set a.listencnt=b.listencnt where a.lessonid=b.lessonid";

	/**
	 * 更新老师的课程数
	 */
	private static String TEACHER_LESSON_STATIC_SQL = "update teacher a inner join(select count(lessonid) as cnt,teacherid from lessons group by teacherid)b set a.lessoncount=b.cnt where a.userid=b.teacherid";

	/**
	 * 统计课程的相关字段
	 */
	public static void staticLessons() {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) Globals.getBean("jdbcTemplate");
		jdbcTemplate.execute(LESSON_TYPE_STATIC_SQL);
		jdbcTemplate.execute(LESSON_SCORE_STATIC_SQL);

		jdbcTemplate.execute(LESSON_REPLY_STATIC_SQL);
		jdbcTemplate.execute(LESSON_LISTEN_STATIC_SQL);

		jdbcTemplate.execute(TEACHER_LESSON_STATIC_SQL);

	}

	public static void updateLessonTypes() {
		
		getPopularlesson();
		
		try {
			BasicService service = (BasicService) Globals.getBean("basicService");
			synchronized (LessonType) {
				LessonType.clear();
				TOP_LEVEL_TYPE.clear();
				LessonTypeObject.clear();

				DetachedCriteria dc = DetachedCriteria.forClass(Lessontype.class);

				List list = service.findAllByCriteria(dc);
				int len = list == null ? 0 : list.size();

				for (int i = 0; i < len; i++) {
					Lessontype type = (Lessontype) list.get(i);

					LessonType.put(type.getTypeid(), type.getTypename());
					LessonTypeObject.put(type.getTypeid(), type);
					if (type.getParentid() >= 0) {

						if (type.getParentid() == 0) {
							TOP_LEVEL_TYPE.add(type);
						}

						for (int j = 0; j < len; j++) {
							Lessontype type1 = (Lessontype) list.get(j);
							if (type1.getParentid() == type.getTypeid() && !type.getChildren().contains(type1)) {
								type.addChild(type1);
								// 下级的数量加到上级
								type.setLessoncnt(type.getLessoncnt() + type1.getLessoncnt());
								// System.out.println(type.getHaschild()+",,"+type1.getTypename()+","+type1.getTypeid());
							}
						}
					}
				}

				LOG.info("课程类别统计信息更新成功");
			}
		} catch (Exception e) {
			LOG.error("设置课程类别数统计失败", e);
		}
	}

	
	
	
	private static void getPopularlesson() {
		// long now = System.currentTimeMillis();
		// if (now - lessonInteractive > 60 * 60 * 60 * 1000) { // 每60分钟
		try {
			BasicService service = (BasicService) Globals.getBean("basicService");
			synchronized (POPULAR_LESSON) {
				

				DetachedCriteria dc = DetachedCriteria.forClass(Lessons.class);
				dc.addOrder(Order.desc("listencnt"));
			
				POPULAR_LESSON.clear();
				List list = service.findPageByCriteria(dc, 6, 1).getItems();
				int len = list == null ? 0 : list.size();
		
				for (int i = 0; i < len; i++) {
					Lessons lessons = (Lessons) list.get(i);
					POPULAR_LESSON.add(lessons);
				
				}
				LOG.info("最受欢迎课程更新成功");
			}
		} catch (ServiceException e) {
			LOG.error("获取最受欢迎课程失败" + e);
		}
		// lessonInteractive = now;
		// }
	}
	
	
	public static void getAlllessons() {
		// long now = System.currentTimeMillis();
		// if (now - lessonInteractive > 60 * 60 * 60 * 1000) { // 每60分钟
		try {
			BasicService service = (BasicService) Globals.getBean("basicService");
			synchronized (AllLessonMap) {
				int size = AllLessonMap.size();

				DetachedCriteria dc = DetachedCriteria.forClass(Lessons.class);
				dc.addOrder(Order.desc("lessonid"));

				AllLessons.clear();
				AllLessonMap.clear();
				LATEST_LESSONS.clear();
				List list = service.findAllByCriteria(dc);
				int len = list == null ? 0 : list.size();
				int latest = 0;
				for (int i = 0; i < len; i++) {
					Lessons lessons = (Lessons) list.get(i);
					if ((lessons.getLessonstyle() == 2 || lessons.getLessonstyle() == 3)) {
						if (latest++ <= 6)
							LATEST_LESSONS.add(lessons);
					}
					AllLessonMap.put(lessons.getLessonid(), lessons.getTitle());
					AllLessons.put(lessons.getLessonid(), lessons);
				}
				LOG.info("课程更新成功!::前:" + size + "后:" + AllLessonMap.size());
			}
			
			LessonsService  lservice = (LessonsService) Globals.getBean("lessonsService");
			List<Integer> list=lservice.getRecommandLessonids(5);
			RECOMMAND_LESSONS.clear();
			for(int i=0;i<list.size();i++){
				RECOMMAND_LESSONS.add(AllLessons.get(list.get(i)));
			}
			
		} catch (ServiceException e) {
			LOG.error("获取所有课程失败" + e);
		}
		// lessonInteractive = now;
		// }
	}

	private static long lessonInteractive = 0;
}
