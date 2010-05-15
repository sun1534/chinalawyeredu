/**
 * JifenQueryAction.java
 */

package com.changpeng.jifen.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Jifenbudeng;
import com.changpeng.models.Lawyers;

/**
 * 补登的积分查询
 * 
 * @author 华锋 2008-5-5 上午12:08:59
 * 
 */
public class JifenbudengQueryAction extends AbstractListAction {

	public JifenbudengQueryAction() {

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

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Jifenbudeng.class);

		detachedCriteria.add(Restrictions.eq("lawyerid", lawyerid));
		detachedCriteria.addOrder(Order.desc("budengdate"));
		this.page = basicService.findPageByCriteria(detachedCriteria,this.pageSize, pageNo);

		// TODO Auto-generated method stub
		return SUCCESS;
	}

	private Lawyers lawyers;

	public Lawyers getLawyers() {
		return this.lawyers;
	}
}