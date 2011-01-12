/**
 * JifenQueryAction.java
 */

package com.changpeng.jifen.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.jifen.util.CommonDatas;
import com.changpeng.jifen.util.JifenTime;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.LawyerlessonxfGongzheng;
import com.changpeng.models.LawyerlessonxfShixi;
import com.changpeng.models.Lawyers;
import com.changpeng.models.SysUnionparams;

/**
 * 积分查询，都查自己的律师积分
 * 
 * @author 华锋 2008-5-5 上午12:08:59
 * 
 */
public class JifenQueryAction extends AbstractListAction {

	public JifenQueryAction() {
		this.moduleId=1002;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractListAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// SysUser lawer = ;

		int lawyerid = this.getLoginUser().getLawyerid();
		this.lawyers = this.getLoginUser();
		// 根据查询的年来查,默认为当前时间所在的积分年

		SysUnionparams params = (SysUnionparams) basicService.get(SysUnionparams.class, lawyers.getDirectunion());
		dabiaofen = params.getDabiaofen();
		jifentime = CommonDatas.getJifenTime(nianshenyear, params.getNianshen());
		this.nianshenyear = jifentime.getNianshenyear();
		DetachedCriteria detachedCriteria = null;
		String table = "lawyerlessonxf";
		if (this.lawyers.getLawyertype() == -1) {
			detachedCriteria = DetachedCriteria.forClass(LawyerlessonxfShixi.class);
			table = "lawyerlessonxf_shixi";
		}else if (this.lawyers.getLawyertype() == -2) {
			detachedCriteria = DetachedCriteria.forClass(LawyerlessonxfGongzheng.class);
			table = "Lawyerlessonxf_gongzheng";
		}  
		else
			detachedCriteria = DetachedCriteria.forClass(Lawyerlessonxf.class);
		detachedCriteria.add(Restrictions.eq("lawyerid", lawyerid));
		// detachedCriteria.add(Restrictions.between("lastupdate",
		// jifentime.getStart(), jifentime.getEnd()));
		detachedCriteria.add(Restrictions.eq("theyear", jifentime.getNianshenyear()));

		detachedCriteria.addOrder(Order.desc("lastupdate"));

		this.page = basicService.findPageByCriteria(detachedCriteria, Integer.MAX_VALUE, pageNo);

		// String adminsql="select sum(a.pxxf),a.learnmode from lawyerlessonxf a
		// where a.lawyerid="+lawyerid+" and (UNIX_TIMESTAMP(a.lastupdate)
		// between "+jifentime.getStart().getTime()/1000 + " and " +
		// jifentime.getEnd().getTime()/1000 + ") group by a.learnmode";
		String adminsql = "select sum(a.pxxf),a.learnmode from " + table + " a where a.theyear="
				+ jifentime.getNianshenyear() + " and a.lawyerid=" + lawyerid + " group by a.learnmode";

		List tongjilist = basicService.findBySqlQuery(adminsql);
		int tongjilength = tongjilist == null ? 0 : tongjilist.size();
		for (int i = 0; i < tongjilength; i++) {
			Object[] obj = (Object[]) tongjilist.get(i);
			if (obj[1].toString().equals("1")) {
				this.localecnt = Double.parseDouble(obj[0].toString());
			} else if (obj[1].toString().equals("2")) {
				this.video = Double.parseDouble(obj[0].toString());
			} else if (obj[1].toString().equals("3")) {
				this.wenbenkejian = Double.parseDouble(obj[0].toString());
			} else if (obj[1].toString().equals("4")) {
				this.budeng = Double.parseDouble(obj[0].toString());
			}
		}
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	private Lawyers lawyers;

	public Lawyers getLawyers() {
		return this.lawyers;
	}

	private float dabiaofen;

	private int nianshenyear;
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

	private JifenTime jifentime;

	/**
	 * @return the jifentime
	 */
	public JifenTime getJifentime() {
		return jifentime;
	}

	/**
	 * @return the dabiaofen
	 */
	public float getDabiaofen() {
		return dabiaofen;
	}

	/**
	 * @return the nianshenyear
	 */
	public int getNianshenyear() {
		return nianshenyear;
	}

	/**
	 * @param nianshenyear
	 *            the nianshenyear to set
	 */
	public void setNianshenyear(int nianshenyear) {
		this.nianshenyear = nianshenyear;
	}
}