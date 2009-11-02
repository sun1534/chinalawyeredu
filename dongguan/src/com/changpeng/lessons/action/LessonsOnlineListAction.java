
package com.changpeng.lessons.action;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.CommonDatas;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.jifen.util.NumberUtil;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.Lessons;

public class LessonsOnlineListAction extends AbstractListAction {
	private int userid;
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

	public int getUserid() {

		return userid;
	}

	private int nowselectyear = 0;
	private int year = -1;

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

	public LessonsOnlineListAction() {

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

		this.rightCode = "online";
	}

	@Override
	protected String go() throws Exception {
		this.userid = getLoginUser().getUserid();
		BasicService service = (BasicService) this.getBean("basicService");

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

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lessons.class);
		detachedCriteria.add(Restrictions.isNotNull("onlinefile"));
		// detachedCriteria.add(Restrictions.between("createtime", _from, _end));
		if (year != -1)
			detachedCriteria.add(Restrictions.between("lessondate", from + " 00:00", end + " 23:59"));
		
		detachedCriteria.addOrder(Order.desc("lessonid"));
		
		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		List list = this.page.getItems();

		// 得到在线课程，同时得到这个人所有培训的课程。然后判断培训课程的lessonid是否在在线课程里，如果在，显示获得的学分和培训的方式
		// 得到lawerlessonxf
		//
		//
		//

		detachedCriteria = DetachedCriteria.forClass(Lawyerlessonxf.class);
		detachedCriteria.add(Restrictions.eq("lawer.userid", this.userid));// 得到这个人所有的培训记录
		List lessonxflist = service.findAllByCriteria(detachedCriteria);
		List xflessonidlist = new ArrayList();
		Map xflessonidmap = new HashMap();
		for (int i = 0; i < lessonxflist.size(); i++) {
			Lawyerlessonxf xf = (Lawyerlessonxf) lessonxflist.get(i);
			if (xf.getLessonid() != null) {// lessonid==null的为补登积分或其他积分
				xflessonidlist.add(xf.getLessonid().intValue());
				xflessonidmap.put(xf.getLessonid().intValue(), xf);
			}
		}

		// List lessonidlist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Lessons lessons = (Lessons) list.get(i);
			if (xflessonidlist.contains(lessons.getLessonid())) {
				Lawyerlessonxf xf = (Lawyerlessonxf) xflessonidmap.get(lessons.getLessonid());
			
				float kechengxuefen=lessons.getXuefen().floatValue();
				float dazhexuefen=kechengxuefen;
				if (lessons.getFenshuoff() != null && !"".equals(lessons.getFenshuoff())) {// 获得的学分要打折
					int zhekou = Integer.parseInt(lessons.getFenshuoff());
					dazhexuefen = Float.parseFloat(NumberUtil.toMoney(lessons.getXuefen() * zhekou / 100));
				
				}	
				if (xf != null && ((xf.getPxxf().floatValue() == dazhexuefen&&xf.getLearnmode().equals("在线视频"))||(xf.getPxxf().floatValue()==kechengxuefen&&!xf.getLearnmode().equals("在线视频")))) {
					xf.setIsmanfen(true);
				}
				
//					if (xf.getPxxf() != null && xf.getPxxf().floatValue() == zhekouxuefen) {
//						xf.setIsmanfen(true);
//					}
					// lessons.setXuefen(Float.parseFloat(NumberUtil.toMoney(lessons.getXuefen()*100/zhekou)));
					// xf.setPxxf(Float.parseFloat(NumberUtil.toMoney((xf.getPxxf()*100)/zhekou)));
					// 将获得的学分，对比折扣，方便页面上“已满分”的判断
//				}
				
				lessons.setUserlessonxf(xf);
			}
			onlineList.add(lessons);
		}

		return SUCCESS;
	}

	private static final DateFormat dftime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat dfdate = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final int START_YEAR = 2007;

	private List onlineList = new ArrayList();

	public List getOnlineList() {
		return this.onlineList;
	}
}