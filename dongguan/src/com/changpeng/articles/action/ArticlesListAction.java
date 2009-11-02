/**
 * ForumAction.java
 */

package com.changpeng.articles.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Articles;

/**
 * 
 * 论坛,所有的回复都针对主题帖来
 * 
 * @author 华锋 2008-5-5 下午06:20:11
 * 
 */
public class ArticlesListAction extends AbstractListAction {

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
		if (type != 0)
			detachedCriteria.add(Restrictions.eq("type", type));
		detachedCriteria.addOrder(Order.desc("createtime"));

		this.page = basic.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		// TODO Auto-generated method stub
		return SUCCESS;
	}

}
