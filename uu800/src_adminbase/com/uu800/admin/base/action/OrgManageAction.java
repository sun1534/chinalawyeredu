package com.uu800.admin.base.action;

import java.util.List;

import org.hibernate.HibernateException;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.OrgService;

/**
 * 
 * <p>
 * 功能： 组织管理
 * </p>
 * <p>
 * 作者： zrb
 * </p>
 * <p>
 * 公司： 深圳信科
 * </p>
 * <p>
 * 日期： 2009-05-22
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */

public class OrgManageAction extends AbstractAdminAction {
	private List userlist;
	private TsysUser user;
	private String orgtree;
	private String test = "N";
	private long orgid;
	private OrgService service;

	private List orgList;

	public OrgManageAction() {
		orgid = 1;
		service = (OrgService) this.getBean("orgService");
	}

	@Override
	public String execute() {

		this.orgList = service.getOrgList();
		System.out.println("orgList::::::::::::::" + orgList);

		user = (TsysUser) service.get(TsysUser.class, getUserinfo().getId());
		// orgtree = service.getOrgTree();
		return SUCCESS;
	}

	public List getUserlist() {
		return userlist;
	}

	public TsysUser getUser() {
		return user;
	}

	public String getOrgtree() {
		return orgtree;
	}

	public long getOrgid() {
		return orgid;
	}

	public void setOrgid(long orgid) {
		this.orgid = orgid;
	}

	public String getTest() {
		return test + "";
	}

	/**
	 * @return the orgList
	 */
	public List getOrgList() {
		return orgList;
	}
}
