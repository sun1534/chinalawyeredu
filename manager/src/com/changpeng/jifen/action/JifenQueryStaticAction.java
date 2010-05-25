/**
 * JifenQueryAction.java
 */

package com.changpeng.jifen.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.jifen.util.CommonDatas;
import com.changpeng.jifen.util.JifenTime;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.SysGroup;

/**
 * 积分查询，都查自己的律师积分
 * 
 * @author 华锋 2008-5-5 上午12:08:59
 * 
 */
public class JifenQueryStaticAction extends AbstractListAction {

	private int learnmode;
	/**
	 * @return the learnmode
	 */
	public int getLearnmode() {
		return learnmode;
	}

	/**
	 * @param learnmode the learnmode to set
	 */
	public void setLearnmode(int learnmode) {
		this.learnmode = learnmode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractListAction#go()
	 */
	@Override
	protected String go() throws Exception {

		// 根据查询的年来查,默认为当前时间所在的积分年

		jifentime = CommonDatas.getJifenTime(year, "12-31");
		DetachedCriteria detachedCriteria = null;

		detachedCriteria = DetachedCriteria.forClass(Lawyerlessonxf.class);
		if (lawyerno != null&&!lawyerno.equals("")){
			detachedCriteria.add(Restrictions.sqlRestriction("lawyerid in(select lawyerid from lawyers where lawyerno='"+lawyerno+"')"));
		}
		if (lawyername != null&&!lawyername.equals(""))
			detachedCriteria.add(Restrictions.like("lawyername", lawyername, MatchMode.ANYWHERE));
		if (year != 0)
			detachedCriteria.add(Restrictions.eq("theyear", year));
		if (learnmode != 0)
			detachedCriteria.add(Restrictions.eq("learnmode", learnmode));
		
		
		SysGroup group=this.getLoginUser().getSysGroup();
		if(group!=null&&group.getGrouptype()==1){
			detachedCriteria.add(Restrictions.eq("officeid",group.getGroupid()));
		}else if(group!=null&&group.getGrouptype()==2){
			detachedCriteria.add(Restrictions.eq("cityid", group.getGroupid()));
		}else if(group!=null&&group.getGrouptype()==3){
			detachedCriteria.add(Restrictions.eq("provinceid", group.getGroupid()));
		}
		

		detachedCriteria.addOrder(Order.desc("lastupdate"));

		this.page = basicService.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		return SUCCESS;
	}

	private String lawyername;
	private String lawyerno;
	private JifenTime jifentime;
	private int year;

	/**
	 * @return the lawyername
	 */
	public String getLawyername() {
		return lawyername;
	}

	/**
	 * @param lawyername
	 *            the lawyername to set
	 */
	public void setLawyername(String lawyername) {
		this.lawyername = lawyername;
	}

	/**
	 * @return the lawyerno
	 */
	public String getLawyerno() {
		return lawyerno;
	}

	/**
	 * @param lawyerno
	 *            the lawyerno to set
	 */
	public void setLawyerno(String lawyerno) {
		this.lawyerno = lawyerno;
	}

	/**
	 * @return the jifentime
	 */
	public JifenTime getJifentime() {
		return jifentime;
	}

	/**
	 * @param jifentime
	 *            the jifentime to set
	 */
	public void setJifentime(JifenTime jifentime) {
		this.jifentime = jifentime;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

}