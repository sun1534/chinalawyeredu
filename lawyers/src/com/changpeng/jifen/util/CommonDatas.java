/**
 * 
 */
package com.changpeng.jifen.util;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.context.Globals;
import com.changpeng.models.Lawyerlessonxf;

/**
 * @author 华锋
 * 
 */
public class CommonDatas {

	public static Map<Integer, String> LEARNMODE = new LinkedHashMap<Integer, String>();

	public static Map<Integer, String> LESSONSTYLE = new LinkedHashMap<Integer, String>();

	public static Map<Integer, String> LessonType = new LinkedHashMap<Integer, String>();
	public static Map<Integer, String> LessonState = new LinkedHashMap<Integer, String>();
	public static Map<Integer, String> SharedName = new LinkedHashMap<Integer, String>();

	public static Map<Integer, Boolean> XuefenManfen = new LinkedHashMap<Integer, Boolean>();

	public static Map<Integer, Integer> JifenYears = new LinkedHashMap<Integer, Integer>();

	private static final DateFormat dftime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat dfdate = new java.text.SimpleDateFormat("yyyy-MM-dd");
	static {
		// 1现场培训2在线视频3文本课件4积分补登
		LEARNMODE.put(1, "现场培训");
		LEARNMODE.put(2, "在线视频");
		LEARNMODE.put(3, "文本课件");
		LEARNMODE.put(4, "积分补登");
		// 课程类型,1现场课程2在线培训课程3现场和在线的合并4文本课件5补登的积分

		LESSONSTYLE.put(1, "现场课程");
		LESSONSTYLE.put(2, "在线课程");
		LESSONSTYLE.put(3, "现场在线");
		LESSONSTYLE.put(4, "文本课件");
		LESSONSTYLE.put(5, "补登积分课程");

		LessonType.put(0, "律师实务");
		LessonType.put(1, "新法讲坛");
		LessonType.put(2, "涉外法务");
		LessonType.put(3, "行业专题");
		LessonType.put(4, "岗前培训");
		LessonType.put(5, "专题论坛");
		LessonType.put(6, "其他");

		LessonState.put(0, "内容编辑");
		LessonState.put(1, "培训课程");
		LessonState.put(2, "往期课程");
		LessonState.put(3, "课程结束");
		// 0: 1: 2:取消了共享
		SharedName.put(0, "不共享");
		SharedName.put(1, "共享");
		SharedName.put(2, "被取消");

		for (int i = com.changpeng.common.Constants.START_YER; i <= com.changpeng.common.Constants.START_YER + 10; i++) {
			JifenYears.put(i, i);
		}

	}

	/**
	 * 根据年审时间得到查询的起始终止时间段
	 * 
	 * @param nianshen
	 * @return
	 * @throws Exception
	 */
	public static JifenTime getJifenTime(int year, String nianshen) throws Exception {

		JifenTime time = new JifenTime();
		time.years = JifenYears;

		Calendar calendar = Calendar.getInstance();
		int nowmonth = calendar.get(Calendar.MONTH) + 1;
		int nowday = calendar.get(Calendar.DATE);
		int nowyear = calendar.get(Calendar.YEAR);

		int index = nianshen.indexOf("-");
		int shenmonth = Integer.parseInt(nianshen.substring(0, index));
		int shenday = Integer.parseInt(nianshen.substring(index + 1));
		// 今天是5.5，年審時間是5.4號，那應該算2008年的了,否则就算是2007年的

		if (year == 0) {
			if (nianshen.equals("12-31")||nowmonth * 100 + nowday > shenmonth * 100 + shenday) {
				time.nianshenyear = nowyear;
			} else {
				time.nianshenyear = nowyear - 1;
			}
		} else {
			time.nianshenyear = year;
		}

		if(nianshen.equals("12-31")){
			Date temp = dftime.parse(time.nianshenyear + "-01-01 0:0:0");
			String end = time.nianshenyear + "-" + nianshen;
			time.start=new java.sql.Timestamp(temp.getTime());
			time.end = new java.sql.Timestamp(dftime.parse(end + " 23:59:59").getTime());
		}else{
			
			Date temp = dfdate.parse(time.nianshenyear + "-" + nianshen);
			// 年审时间的基础上加1的毫秒值
			long _time = temp.getTime() + 24 * 60 * 60 * 1000;
			// 得到这个时间
			temp.setTime(_time);
			String end = (time.nianshenyear + 1) + "-" + nianshen;
			time.start = new java.sql.Timestamp(temp.getTime());
			time.end = new java.sql.Timestamp(dftime.parse(end + " 23:59:59").getTime());
		}
		
		
		// Date _from = dftime.parse(from + " 0:0:0");
		

		return time;
	}

	public static void getXuefenQingkuang(int lawyerid) {

		if (System.currentTimeMillis() - huodexuefenInterval > 5 * 60 * 1000) {
			try {

				BasicService basicService = (BasicService) Globals.getBean("basicService");
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lawyerlessonxf.class);
				detachedCriteria.add(Restrictions.eq("lawyerid", lawyerid));// 得到这个人所有的培训记录

				List list = basicService.findAllByCriteria(detachedCriteria);

				int len = list == null ? 0 : list.size();
				for (int i = 0; i < len; i++) {
					Lawyerlessonxf xf = (Lawyerlessonxf) list.get(i);
					XuefenManfen.put(xf.getLessonid(), xf.getIsfull());
				}

			} catch (Exception e) {
				System.out.println("获取用户学分情况异常:" + e);
			}
			huodexuefenInterval = System.currentTimeMillis();
		}
	}

	private static long huodexuefenInterval = 0;

}
