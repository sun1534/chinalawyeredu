/**
 * OfficebudengApplyAction.java
 */
package com.changpeng.jifen.action;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.jifen.util.JifenTime;
import com.changpeng.models.JifenbudengApply;
import com.changpeng.models.Lessons;
import com.changpeng.models.SysUnionparams;

/**
 * 律师发起积分补登申请
 * 
 * 插入转所申请表
 * 
 * @author 华锋 Feb 27, 20104:43:49 PM
 * 
 */
public class JifenbudengApplyAction extends AbstractAction {

	private JifenbudengApply budengApply;

	public JifenbudengApply getBudengApply() {
		if (budengApply == null)
			budengApply = (JifenbudengApply) this.get("budengApply");
		return budengApply;
	}

	/*
	 * 
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		budengApply.setLawyerid(this.getLoginUser().getLawyerid());
		budengApply.setApplytime(new java.sql.Timestamp(System.currentTimeMillis()));
		budengApply.setLawyername(this.getLoginUser().getLawyername());
		budengApply.setApplyuser(this.getLoginUser().getLawyername());
		budengApply.setApplyuserid(budengApply.getLawyerid());
		budengApply.setLawyerno(this.getLoginUser().getLawyerno());
		budengApply.setProvinceid(this.getLoginUser().getProvinceunion());
		budengApply.setCityid(this.getLoginUser().getDirectunion());
		budengApply.setOfficeid(this.getLoginUser().getTheoffice());
		if (isedit == 0)
			basicService.save(budengApply);
		else {
			budengApply.setRemarks((budengApply.getRemarks() == null ? "" : budengApply.getRemarks() + "|") + "重新提交");
			basicService.update(budengApply);
		}
		this.message = "您的积分补登申请提交成功";

		this.nextPage = "jifenbudengApplyList.pl";

		return SUCCESS;
	}

	public String input() throws Exception {

		String hql = "from JifenbudengApply where status=0 and applyuserid=" + this.getLoginUser().getLawyerid();
		List list = basicService.find(hql);
		if (list != null && list.size() > 0) {
			this.message = "您的积分补登申请已经提交,请等待管理员审批";
			return "message";
		} else {
			budengApply = new JifenbudengApply();
			isedit = 0;
		}
Calendar c=Calendar.getInstance();
c.add(Calendar.YEAR, -1);
		Timestamp lessondate=new Timestamp(c.getTimeInMillis());
		int directunion = this.getLoginUser().getDirectunion();
		DetachedCriteria dc = DetachedCriteria.forClass(Lessons.class).add(Restrictions.eq("groupid", directunion))
				.add(Restrictions.in("lessonstyle", new Object[] { 1, 3 })).add(Restrictions.ge("lessondate", lessondate));
		
		//时间是1年之前的

		dc.addOrder(Order.desc("lessonid"));
		this.locallessonlist = basicService.findAllByCriteria(dc);
		
		com.changpeng.models.SysUnionparams params = (SysUnionparams) basicService.get(SysUnionparams.class,
				directunion);
		if (params != null && params.getNianshen() != null && !params.getNianshen().equals(""))
			jifentime = com.changpeng.jifen.util.CommonDatas.getJifenTime(0, params.getNianshen());
		else
			jifentime = com.changpeng.jifen.util.CommonDatas.getJifenTime(0, "12-31");
		budengApply.setLawyerid(this.getLoginUser().getLawyerid());
		budengApply.setTheyear(jifentime.getNianshenyear());
		set("budengApply", budengApply);
		return INPUT;
	}

	private JifenTime jifentime;

	private List locallessonlist;

	public List getLocallessonlist() {
		return this.locallessonlist;
	}

	private int isedit;

	/**
	 * @return the isedit
	 */
	public int getIsedit() {
		return isedit;
	}

	/**
	 * @param isedit
	 *            the isedit to set
	 */
	public void setIsedit(int isedit) {
		this.isedit = isedit;
	}

	/**
	 * @return the jifentime
	 */
	public JifenTime getJifentime() {
		return jifentime;
	}

}
