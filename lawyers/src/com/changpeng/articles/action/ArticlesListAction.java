/**
 * ForumAction.java
 */

package com.changpeng.articles.action;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Articles;
import com.changpeng.models.SysGroup;

/**
 * 
 * 管理系统看处看到的通知或者公告
 * 
 * @author 华锋 2008-5-5 下午06:20:11
 * 
 */
public class ArticlesListAction extends AbstractListAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		BasicService basic = (BasicService) getBean("basicService");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Articles.class);
		detachedCriteria.add(Restrictions.eq("type", type));

		if (type == 1) { // 重要通知才搞这个。那个什么帮助就都显示了
		// Criterion province = Restrictions.eq("provinceid",
		// this.getLoginUser().getProvinceid());

			int directunion = this.getLoginUser().getDirectunion();
			SysGroup group = (SysGroup) basic.get(SysGroup.class, directunion);

			detachedCriteria.add(Restrictions.eq("status", 0));
			// 显示我的事务所的通知，显示我的律协的通知，显示我的省律协的通知，显示系统的通只
			// detachedCriteria.add(Restrictions.in("thegroup", new Integer[] {
			// this.getLoginUser().getProvinceunion(),
			// this.getLoginUser().getDirectunion(),
			// this.getLoginUser().getTheoffice(), 0 }));

			Criterion province = Restrictions.in("thegroup", new Integer[] { this.getLoginUser().getProvinceunion(),
					this.getLoginUser().getDirectunion(), this.getLoginUser().getTheoffice(), 0 });
			
			
			Criterion c = Restrictions.eq("provinceid", 0);
			detachedCriteria.add(Restrictions.or(c, province));
			
			detachedCriteria.addOrder(Order.desc("createtime"));
		}

		// 按现实顺序来
		detachedCriteria.addOrder(Order.desc("listorder"));
		detachedCriteria.addOrder(Order.desc("createtime"));

		this.page = basic.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	private int type;

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

}