/**
 * JifenQueryAction.java
 */

package com.changpeng.jifen.action;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.jifen.util.CommonDatas;
import com.changpeng.jifen.util.JifenTime;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.Lawyers;
import com.changpeng.models.SysUnionparams;
import com.changpeng.models.SysUser;

/**
 * 积分查询，都查自己的律师积分
 * 
 * @author 华锋 2008-5-5 上午12:08:59
 * 
 */
public class JifenQueryAction extends AbstractListAction {

	private Lawyers lawyers;

	public Lawyers getLawyers() {
		return this.lawyers;
	}

	public JifenQueryAction() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractListAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// SysUser lawer = ;

		lawyers = (Lawyers) basicService.get(Lawyers.class, lawyerid);
		// 根据查询的年来查,默认为当前时间所在的积分年

		SysUnionparams params = (SysUnionparams) basicService.get(SysUnionparams.class, lawyers.getDirectunion());
		dabiaofen = params.getDabiaofen();
		jifentime = CommonDatas.getJifenTime(year, params.getNianshen());
		this.year = jifentime.getNianshenyear();

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lawyerlessonxf.class);
		detachedCriteria.add(Restrictions.eq("lawyerid", lawyerid));
		detachedCriteria.add(Restrictions.between("lastupdate", jifentime.getStart(), jifentime.getEnd()));
		detachedCriteria.addOrder(Order.desc("lastupdate"));

		this.page = basicService.findPageByCriteria(detachedCriteria, Integer.MAX_VALUE, pageNo);

		String adminsql = "select format(sum(a.pxxf),2),a.learnmode from lawyerlessonxf a where a.lawyerid="
				+ this.lawyerid + " and (UNIX_TIMESTAMP(a.lastupdate) between " + jifentime.getStart().getTime() / 1000
				+ " and " + jifentime.getEnd().getTime() / 1000 + ") group by a.learnmode";
//System.out.println(adminsql);
		List tongjilist = basicService.findBySqlQuery(adminsql);
		int tongjilength = tongjilist == null ? 0 : tongjilist.size();
		for (int i = 0; i < tongjilength; i++) {
			Object[] obj = (Object[]) tongjilist.get(i);
//			System.out.println(obj[0]+"=="+obj[1]);
			if (obj[1].toString().equals("1")) {
				this.localecnt = Double.parseDouble(obj[0].toString());
			} else if (obj[1].toString().equals("2")) {
				this.video =  Double.parseDouble(obj[0].toString());
			} else if (obj[1].toString().equals("3")) {
				this.wenbenkejian = Double.parseDouble(obj[0].toString());
			} else if (obj[1].toString().equals("4")) {
				this.budeng =  Double.parseDouble(obj[0].toString());
			}
		}
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	private float dabiaofen;
	private int lawyerid;
	private JifenTime jifentime;
	private int year;
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

	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return this.year;
	}

	/**
	 * @return the jifentime
	 */
	public JifenTime getJifentime() {
		return jifentime;
	}

	/**
	 * @param lawyerid
	 *            the lawyerid to set
	 */
	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}

	public int getLawyerid() {
		return lawyerid;
	}

	/**
	 * @return the dabiaofen
	 */
	public float getDabiaofen() {
		return dabiaofen;
	}
}