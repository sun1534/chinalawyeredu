
package com.changpeng.lessons.action;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.CommonDatas;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Lessonbaoming;
import com.changpeng.models.Lessons;

public class LessonsLocalListAction extends AbstractListAction {
	private static final DateFormat dftime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat dfdate = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final int START_YEAR = 2006;
	private long userid;

	private int year = -1;
	private String from;
	private String end;

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}

	public void setYear(int year) {
		if (year != -1)
			this.nowselectyear = year;
		this.year = year;
	}

	public int getYear() {
		return this.year;
	}

	private Map years = new HashMap();

	public Map getYears() {
		return this.years;
	}

	public long getUserid() {
		userid = getLoginUser().getUserid();
		return userid;
	}

	public LessonsLocalListAction() {
		Calendar calendar = Calendar.getInstance();
		int nowmonth = calendar.get(Calendar.MONTH) + 1;
		int nowday = calendar.get(Calendar.DATE);
		int nowyear = calendar.get(Calendar.YEAR);

		String nianshen = CommonDatas.SysParameter.get("nianshen").toString();
		int index = nianshen.indexOf("-");
		int shenmonth = Integer.parseInt(nianshen.substring(0, index));
		int shenday = Integer.parseInt(nianshen.substring(index + 1));
		// 今天是5.5，年審時間是5.4號，那應該算2008年的了,否则就算是2007年的

		if (nowmonth * 100 + nowday > shenmonth * 100 + shenday) {
			nowselectyear = nowyear;
		}
		else {
			nowselectyear = nowyear - 1;
		}
		for (int i = START_YEAR; i <= nowselectyear; i++) {
			years.put(i, i);
		}

		this.rightCode = "locale";
	}

	private int nowselectyear = 0;

	@Override
	protected String go() throws Exception {
		String nianshen = CommonDatas.SysParameter.get("nianshen").toString();

		// 起始时间是现在的时间基础上加1
		Date temp = dfdate.parse(nowselectyear + "-" + nianshen);
		// 年审时间的基础上加1的毫秒值
		long time = temp.getTime() + 24 * 60 * 60 * 1000;
		// 得到这个时间
		temp.setTime(time);
		this.from = dfdate.format(temp);
		this.end = (nowselectyear + 1) + "-" + nianshen;
		// Date _from = dftime.parse(from + " 0:0:0");
		// Date _from = temp;
		// Date _end = dftime.parse(end + " 23:59:59");

		BasicService service = (BasicService) this.getBean("basicService");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lessons.class).add(Restrictions.isNotNull("lessoncontent"));;
		// detachedCriteria.add(Restrictions.isNull("onlinefile"));
		if (year != -1)
			detachedCriteria.add(Restrictions.between("lessondate", from + " 00:00", end + " 23:59"));
		detachedCriteria.addOrder(Order.desc("lessonid"));
		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		detachedCriteria = DetachedCriteria.forClass(Lessonbaoming.class).createAlias("lessons", "lessons").add(
				Restrictions.eq("userid", this.getLoginUser().getUserid()));
		detachedCriteria.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List _baominglist = service.findAllByCriteria(detachedCriteria);
		List baominglist = new ArrayList();
		for (int i = 0; i < _baominglist.size(); i++) {
			Map map = (Map) _baominglist.get(i);
			// debug("=======map"+map);
			Lessons lessons = (Lessons) map.get("lessons");
			baominglist.add(lessons.getLessonid());// 这个人所有已经报名了的课程
		}
		List _list = page.getItems();
		for (int i = 0; i < _list.size(); i++) {
			Lessons lessons = (Lessons) _list.get(i);
			if (baominglist.contains(lessons.getLessonid())) {// 已经报名了
				lessons.setBaominguser(1);
			}
			this.lessonList.add(lessons);
		}
		return SUCCESS;
	}

	private List lessonList = new ArrayList();

	public List getLessonList() {
		return this.lessonList;
	}
}
