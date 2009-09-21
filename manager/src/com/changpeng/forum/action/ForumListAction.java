/**
 * ForumAction.java
 */
package com.changpeng.forum.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Forum;
import com.changpeng.models.SysGroup;

/**
 * 
 * 论坛,所有的回复都针对主题帖来
 * 
 * @author 华锋 2008-5-5 下午06:20:11
 * 
 */
public class ForumListAction extends AbstractListAction {

	public ForumListAction() {

	}

	private boolean hasright;
	public boolean getHasright(){
		return hasright;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		SysGroup group=this.getLoginUser().getSysGroup();
		if(group!=null&&group.getGrouptype()==1)
			hasright=false;
		else
			hasright=true;
		
		// TODO Auto-generated method stub
		BasicService basic = (BasicService) getBean("basicService");
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Forum.class);
		detachedCriteria.add(Restrictions.eq("ismain", true));
//		.add(
//				Restrictions.eq("thegroup", this.getLoginUser()
//						.getDirectunion()));
		detachedCriteria.addOrder(Order.desc("lastupdate"));

		this.page = basic
				.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		// TODO Auto-generated method stub
		return SUCCESS;
	}
}