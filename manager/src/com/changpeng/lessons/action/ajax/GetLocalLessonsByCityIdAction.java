package com.changpeng.lessons.action.ajax;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lessons;

public class GetLocalLessonsByCityIdAction extends AbstractAction {

	private static Log _LOG = LogFactory.getLog(GetLocalLessonsByCityIdAction.class);
	private int cityid;

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	private Map<Integer, String> locallessons = new LinkedHashMap<Integer, String>();

	private java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	@Override
	protected String go() throws Exception {

		try {

			Calendar c = Calendar.getInstance();
			c.add(Calendar.YEAR, -1);
			Timestamp lessondate = new Timestamp(c.getTimeInMillis());
			int directunion = cityid;
			DetachedCriteria dc = DetachedCriteria.forClass(Lessons.class).add(Restrictions.eq("groupid", directunion))
					.add(Restrictions.in("lessonstyle", new Object[] { 1, 3 })).add(
							Restrictions.ge("lessondate", lessondate));

			// 时间是1年之前的

			dc.addOrder(Order.desc("lessonid"));
			List locallessonlist = basicService.findAllByCriteria(dc);
			int len = locallessonlist == null ? 0 : locallessonlist.size();
			for (int i = 0; i < len; i++) {
				Lessons lesson = (Lessons) locallessonlist.get(i);
				locallessons.put(lesson.getLessonid(), lesson.getTitle());
			}

		} catch (Exception e) {

			_LOG.error("获取城市本地课程数据失败:", e);
		}

		return SUCCESS;

	}

	/**
	 * @return the title
	 */
	public Map<Integer,String> getLocallessons() {
		return locallessons;
	}

}
