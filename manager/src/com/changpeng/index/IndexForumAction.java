/**
 * 
 */
package com.changpeng.index;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Forum;

/**
 * @author 华锋
 * 
 * 首页的论坛区块的显示
 * 2009-5-2
 */
public class IndexForumAction extends AbstractAction {

	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		BasicService bs=(BasicService)this.getBean("basicService");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Forum.class).add(Restrictions.eq("ismain", true)).add(Restrictions.eq("provinceid",this.getLoginUser().getProvinceid()));
		detachedCriteria.addOrder(Order.desc("lastupdate"));

		PaginationSupport page = bs.findPageByCriteria(detachedCriteria, 6, 1);
		this.forumList = page.getItems();
		return SUCCESS;
	}
	
	private List forumList;
	public List getForumList()
	{
		return this.forumList;
	}

}
