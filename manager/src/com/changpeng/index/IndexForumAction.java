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
import com.changpeng.models.LawyersOfficeChangeApply;
import com.changpeng.models.SysGroup;

/**
 * @author 华锋
 * 
 * 首页的论坛区块的显示 2009-5-2
 */
public class IndexForumAction extends AbstractAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		BasicService bs = (BasicService) this.getBean("basicService");
		// 只显示本律协的
		// DetachedCriteria detachedCriteria =
		// DetachedCriteria.forClass(Forum.class).add(Restrictions.eq("ismain",
		// true)).add(Restrictions.eq("provinceid",this.getLoginUser().getProvinceid()));
		// DetachedCriteria detachedCriteria =
		// DetachedCriteria.forClass(Forum.class).add(Restrictions.eq("ismain",
		// true)).add(Restrictions.in("thegroup",new
		// Object[]{this.getLoginUser().getOfficeid(),this.getLoginUser().getCityid()}));
		// 只显示本市律协的
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Forum.class).add(Restrictions.eq("ismain", true))
				.add(Restrictions.eq("cityid", this.getLoginUser().getCityid()));
		detachedCriteria.addOrder(Order.desc("lastupdate"));

		PaginationSupport page = bs.findPageByCriteria(detachedCriteria, 5, 1);
		this.forumList = page.getItems();

		detachedCriteria = DetachedCriteria.forClass(LawyersOfficeChangeApply.class);
		SysGroup group = this.getLoginUser().getSysGroup();
		if (group != null && group.getGrouptype() == 1) {
			detachedCriteria.add(Restrictions.eq("oldoffice", group.getGroupid()));
		} else if (group != null && group.getGrouptype() == 2) {
			detachedCriteria.add(Restrictions.eq("oldcity", group.getGroupid()));
		} else if (group != null && group.getGrouptype() == 3) {
			detachedCriteria.add(Restrictions.eq("oldprovince", group.getGroupid()));
		}
		detachedCriteria.addOrder(Order.desc("applyTime"));
		page = bs.findPageByCriteria(detachedCriteria, 5, 1);
		this.officeChangeList = page.getItems();

		if (this.getLoginUser().getSysGroup() != null && this.getLoginUser().getSysGroup().getGrouptype() == 1)
			canhandle = false;
		else
			canhandle = true;

		return SUCCESS;
	}

	private boolean canhandle;

	public boolean getCanhandle() {
		return this.canhandle;
	}

	private List officeChangeList;

	public List getOfficeChangeList() {
		return this.officeChangeList;
	}

	private List forumList;

	public List getForumList() {
		return this.forumList;
	}

}
