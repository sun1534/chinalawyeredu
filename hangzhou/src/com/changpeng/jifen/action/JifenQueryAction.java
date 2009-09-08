/**
 * JifenQueryAction.java
 */

package com.changpeng.jifen.action;

import java.text.DateFormat;
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
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.system.SysUser;

/**
 * 积分查询，都查自己的律师积分
 * 
 * @author 华锋 2008-5-5 上午12:08:59
 * 
 */
public class JifenQueryAction extends AbstractListAction {

	private static final DateFormat dftime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat dfdate = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final int START_YEAR = 2006;

	public JifenQueryAction() { 
		// debug("fromwhere========"+fromwhere);
		//		
		// if(this.fromwhere==null||"".equals(fromwhere)){
		// this.rightCode="jifenchaxun";
		// }

		Calendar calendar = Calendar.getInstance();
		int nowmonth = calendar.get(Calendar.MONTH) + 1;
		int nowday = calendar.get(Calendar.DATE);
		int nowyear = calendar.get(Calendar.YEAR);

		String nianshen = CommonDatas.SysParameter.get("nianshen").toString();
		int index = nianshen.indexOf("-");
		int shenmonth = Integer.parseInt(nianshen.substring(0, index));
		int shenday = Integer.parseInt(nianshen.substring(index + 1));
		// 今天是5.5，年審時間是5.4號，那應該算2008年的了,否则就算是2007年的
		if (nianshen.equals("12-31") || nowmonth * 100 + nowday > shenmonth * 100 + shenday) {
			this.year = nowyear;
		} else {
			this.year = nowyear - 1;
		}
		for (int i = START_YEAR; i <= year; i++) {
			years.put(i, i);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractListAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// SysUser lawer = ;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lawyerlessonxf.class);

		// 根据查询的年来查,默认为当前时间所在的积分年
		BasicService basicService = (BasicService) getBean("basicService");
		if (userid == 0) {
			this.lawer = getLoginUser();
			detachedCriteria.add(Restrictions.eq("lawer.userid", getLoginUser().getUserid()));
		} else {
			this.lawer = (SysUser) basicService.get(SysUser.class, userid);
			// detachedCriteria.add(Restrictions.eq("lawer", lawer));
			detachedCriteria.add(Restrictions.eq("lawer.userid", userid));
		}

		String nianshen = CommonDatas.SysParameter.get("nianshen").toString();
		Date temp = null;
		if (!nianshen.equals("12-31")) {
			// 起始时间是现在的时间基础上加1
			temp = dfdate.parse(year + "-" + nianshen);

			// 年审时间的基础上加1天的毫秒值
			long time = temp.getTime() + 24 * 60 * 60 * 1000;
			// 得到这个时间

			temp.setTime(time);

			this.from = dfdate.format(temp);
			this.end = (year + 1) + "-" + nianshen;
		} else {
			temp = dfdate.parse(year + "-01-01");
//			long time = temp.getTime() + 24 * 60 * 60 * 1000;
//			temp.setTime(time);
			this.from = dfdate.format(temp);
			this.end = (year) + "-" + nianshen;
		}

		// Date _from = dftime.parse(from + " 0:0:0");
		Date _from = temp;
		Date _end = dftime.parse(end + " 23:59:59");

		detachedCriteria.add(Restrictions.between("lastupdate", _from, _end));
		detachedCriteria.addOrder(Order.desc("lastupdate"));

		this.page = basicService.findPageByCriteria(detachedCriteria, Integer.MAX_VALUE, pageNo);

		// 统计这个人的积分情况
		// 这里也会打印出超多sql出来
		// detachedCriteria = DetachedCriteria.forClass(Lawyerlessonxf.class);
		if (userid == 0) {
			userid = getLoginUser().getUserid();
			// detachedCriteria.setProjection(Projections.projectionList().add(Projections.sum("pxxf")).add(Projections.groupProperty("learnmode")))
			// .add(Restrictions.eq("lawer.userid",
			// getLoginUser().getUserid())).add(Restrictions.between("lastupdate",
			// _from, _end));
		}
		// else {
		// detachedCriteria.setProjection(Projections.projectionList().add(Projections.sum("pxxf")).add(Projections.groupProperty("learnmode")))
		// .add(Restrictions.eq("lawer.userid",
		// userid)).add(Restrictions.between("lastupdate", _from, _end));
		// }
		String adminsql = "select sum(a.pxxf),a.learnmode from lawyerlessonxf a,sys_user b where a.userid=b.userid and b.userid="
				+ userid
				+ " and (UNIX_TIMESTAMP(a.lastupdate) between "
				+ _from.getTime()
				/ 1000
				+ " and "
				+ _end.getTime() / 1000 + ") group by a.learnmode";

		// tongjilist = basicService.findAllByCriteria(detachedCriteria);
		List tongjilist = basicService.findBySqlQuery(adminsql);
		int tongjilength = tongjilist == null ? 0 : tongjilist.size();
		for (int i = 0; i < tongjilength; i++) {
			Object[] obj = (Object[]) tongjilist.get(i);
			if (obj[1].equals("现场培训")) {
				this.localecnt = ((Double) obj[0]).doubleValue();
			} else if (obj[1].equals("在线视频")) {
				this.video = ((Double) obj[0]).doubleValue();
			} else if (obj[1].equals("文本课件")) {
				this.wenbenkejian = ((Double) obj[0]).doubleValue();
			} else if (obj[1].equals("补登积分")) {
				this.budeng = ((Double) obj[0]).doubleValue();
			}
		}

		// TODO Auto-generated method stub
		return SUCCESS;
	}

	private double video;
	private double localecnt;
	private double budeng;
	private double wenbenkejian;

	public double getVideo() {
		return this.video;
	}

	public double getLocalecnt() {
		return this.localecnt;
	}

	public double getBudeng() {
		return this.budeng;
	}

	public double getWenbenkejian() {
		return this.wenbenkejian;
	}

	private int year;

	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return this.year;
	}

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

	private Map years = new HashMap();

	public Map getYears() {
		return this.years;
	}

	private int userid;

	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	private SysUser lawer;

	/**
	 * @return the lawer
	 */
	public SysUser getLawer() {
		return lawer;
	}

}