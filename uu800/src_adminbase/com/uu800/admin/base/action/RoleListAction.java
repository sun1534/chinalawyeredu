package com.uu800.admin.base.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.RoleService;

/**
 * 
 * @author 华锋
 * Mar 5, 20115:58:16 PM
 *
 */

public class RoleListAction extends AbstractAdminAction {
	private List rolelist;
	private long roletype;
	private RoleService service;

	public RoleListAction() {
		service = (RoleService) this.getBean("roleService");
	}

	@Override
	public String execute() {

		TsysUser sysuser = (TsysUser) service.get(TsysUser.class, getUserinfo().getId());

		DetachedCriteria dc = DetachedCriteria.forClass(TsysRole.class);
		if (roletype > 2) {
			roletype = 0;
		}
		dc.add(Restrictions.eq("roletype", roletype));

		dc.addOrder(Order.asc("roleid"));

		page = service.findPageOnPageNo(dc, pageSize, pageNo);

		rolelist = page.getItems();

		return SUCCESS;
	}

	public List getRolelist() {
		return rolelist;
	}

	public long getRoletype() {
		return roletype;
	}

	public void setRoletype(long roletype) {
		this.roletype = roletype;
	}
}
