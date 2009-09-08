package com.changpeng.lessons.action;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.CommonDatas;
import com.changpeng.common.EducationLocation;
import com.changpeng.common.LessonSync;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.jifen.util.NumberUtil;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.Lessons;

public class LessonsOnlineListAction extends AbstractListAction {
	private int userid;
	private String from;
	private String end;

	
	private byte lessontype=(byte)-1;
	private String teachers="";

	public byte getLessontype() {
		return lessontype;
	}

	public void setLessontype(byte lessontype) {
		this.lessontype = lessontype;
	}

	public String getTeachers() {
		return teachers;
	}

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

	public String getFromAddr() {
		return fromAddr;
	}

	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
	}

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

	/**
	 * 从哪个地方共享过来的
	 */
	private String fromAddr = "-1";

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

		if (nianshen.equals("12-31")||nowmonth * 100 + nowday > shenmonth * 100 + shenday) {
			nowselectyear = nowyear;
		} else {
			nowselectyear = nowyear - 1;
		}
		for (int i = START_YEAR; i <= nowselectyear; i++) {
			years.put(i, i);
		}

		this.rightCode = "online";
	}

	@Override
	protected String go() throws Exception {
		if(com.changpeng.common.CommonDatas.AllLawyers.isEmpty()){
			
			LessonSync sync=new LessonSync();
			EducationLocation.getLocationEdu();
			
		}
		
		this.userid = getLoginUser().getUserid();
		BasicService service = (BasicService) this.getBean("basicService");

		String nianshen = CommonDatas.SysParameter.get("nianshen").toString();

		// 起始时间是现在的时间基础上加1
		Date temp = null;
		if (!nianshen.equals("12-31")) {
			// 起始时间是现在的时间基础上加1
			temp = dfdate.parse(nowselectyear + "-" + nianshen);

			// 年审时间的基础上加1天的毫秒值
			long time = temp.getTime() + 24 * 60 * 60 * 1000;
			// 得到这个时间

			temp.setTime(time);

			this.from = dfdate.format(temp);
			this.end = (nowselectyear + 1) + "-" + nianshen;
		} else {
			temp = dfdate.parse(nowselectyear + "-01-01");
//			long time = temp.getTime() + 24 * 60 * 60 * 1000;
//			temp.setTime(time);
			this.from = dfdate.format(temp);
			this.end = (nowselectyear) + "-" + nianshen;
		}

		// Date _from = dftime.parse(from + " 0:0:0");
		// Date _from = temp;
		// Date _end = dftime.parse(end + " 23:59:59");

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lessons.class);
		
		detachedCriteria.add(Restrictions.eq("deleteflag", "N"));
		
		detachedCriteria.add(Restrictions.isNotNull("onlinefile"));
		// detachedCriteria.add(Restrictions.between("createtime", _from,
		// _end));
		if (year != -1)
			detachedCriteria.add(Restrictions.between("lessondate", from + " 00:00", end + " 23:59"));
		
		
		if (teachers != null && !"".equals(teachers))
			detachedCriteria.add(Restrictions.like("teachers",teachers, MatchMode.ANYWHERE));
		if (lessontype!= -1)
			detachedCriteria.add(Restrictions.eq("lessontype",lessontype));

		if (title != null && !"".equals(title))
			detachedCriteria.add(Restrictions.like("title", title, MatchMode.ANYWHERE));

		//client端的处理
		if (!com.changpeng.common.Constants.FROMADDR.equals("server")) {
			// 我自己的加上其他地方过来的
			if (this.fromAddr.equals("-1")) {
				Criterion owner = Restrictions.eq("fromAddr", com.changpeng.common.Constants.FROMADDR);

				Criterion notowner = Restrictions.not(owner);
			//	Criterion shared = (Restrictions.eq("shared", (short) 1));
			//	Criterion shared = (Restrictions.like("shared", com.changpeng.common.Constants.FROMADDR));
				Criterion shared = (Restrictions.like("shared", com.changpeng.common.Constants.FROMADDR,MatchMode.ANYWHERE));

				Criterion notownerbutshared = Restrictions.and(notowner, shared);

				Criterion thecriterion = Restrictions.or(owner, notownerbutshared);
				detachedCriteria.add(thecriterion);

				// 显示自己这个地方所有的并且显示从其他地方来的所有的

			} 
//			else if (this.fromAddr.equals((com.changpeng.common.Constants.FROMADDR))) { // 显示自己的并所有的
//				detachedCriteria.add(Restrictions.eq("fromAddr", fromAddr));
//			} 
			
			else {
//				detachedCriteria.add(Restrictions.eq("fromAddr", fromAddr));
//		//		detachedCriteria.add(Restrictions.eq("shared", (short) 1));
//				Criterion shared = (Restrictions.like("shared", com.changpeng.common.Constants.FROMADDR));
//				detachedCriteria.add(shared);
				
//				detachedCriteria.add(Restrictions.eq("fromAddr", fromAddr));
//				//	detachedCriteria.add(Restrictions.eq("shared", (short) 1));
//				//	Criterion shared = (Restrictions.like("shared", com.changpeng.common.Constants.FROMADDR));
//					Criterion shared = (Restrictions.like("shared", com.changpeng.common.Constants.FROMADDR,MatchMode.ANYWHERE));
//					detachedCriteria.add(shared);
				detachedCriteria.add(Restrictions.eq("fromAddr", fromAddr));
//				Criterion shared = (Restrictions.like("shared", com.changpeng.common.Constants.FROMADDR,MatchMode.ANYWHERE));
//				Criterion owner= Restrictions.eq("fromAddr", com.changpeng.common.Constants.FROMADDR);
//				detachedCriteria.add(Restrictions.or(shared, owner));
			}
		} 
		//server端的处理，不考虑是否共享，全部列出来
		else {

			if (!this.fromAddr.equals("-1")) {
				detachedCriteria.add(Restrictions.eq("fromAddr", fromAddr));
				// 显示自己这个地方所有的并且显示从其他地方来的所有的

			} 
		}

//		detachedCriteria.addOrder(Order.desc("lessonid"));
		detachedCriteria.addOrder(Order.desc("lessondate"));

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

				float kechengxuefen = lessons.getXuefen().floatValue();
				float dazhexuefen = kechengxuefen;
				if (lessons.getFenshuoff() != null && !"".equals(lessons.getFenshuoff())) {// 获得的学分要打折
					int zhekou = Integer.parseInt(lessons.getFenshuoff());
					dazhexuefen = Float.parseFloat(NumberUtil.toMoney(lessons.getXuefen() * zhekou / 100));

				}
				if (xf != null
						&& ((xf.getPxxf().floatValue() == dazhexuefen && xf.getLearnmode().equals("在线视频")) || (xf.getPxxf().floatValue() == kechengxuefen && !xf
								.getLearnmode().equals("在线视频")))) {
					xf.setIsmanfen(true);
				}

				// if (xf.getPxxf() != null && xf.getPxxf().floatValue() ==
				// zhekouxuefen) {
				// xf.setIsmanfen(true);
				// }
				// lessons.setXuefen(Float.parseFloat(NumberUtil.toMoney(lessons.getXuefen()*100/zhekou)));
				// xf.setPxxf(Float.parseFloat(NumberUtil.toMoney((xf.getPxxf()*100)/zhekou)));
				// 将获得的学分，对比折扣，方便页面上“已满分”的判断
				// }

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

	private String onlineorlocal = "online";
	public String getOnlineorlocal() {
		return onlineorlocal;
	}

	public void setOnlineorlocal(String onlineorlocal) {
		this.onlineorlocal = onlineorlocal;
	}
	public boolean getHavelocal(){
		return com.changpeng.common.Constants.HAVELOCAL;
	}
	
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}