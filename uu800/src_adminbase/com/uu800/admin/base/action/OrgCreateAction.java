package com.uu800.admin.base.action;


import org.hibernate.HibernateException;

import com.opensymphony.xwork2.Preparable;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.*;
import com.uu800.admin.base.entity.TsysLogs;


/**
 *
 * @功能： 创建组织管理</p>
 * @作者： zrb
 * @公司： 深圳信科
 * @日期： 2009-07-13
 * @版本： V1.0
 * @修改：
 */

public class OrgCreateAction extends AbstractAdminAction implements  Preparable{

	private TsysOrg org;
	private TsysOrg parent;
	private long orgid;
    private OrgService service;

	public OrgCreateAction() {
		service = (OrgService)this.getBean("orgService");
		org = new TsysOrg();
	}

	@Override
	public String execute() {
		//org.setCreatetime(new java.sql.Date(System.currentTimeMillis()));
		
//		log=new TsysLogs("新增组织: 组织名称为"+org.getOrgenname(),2,true);
		
		org.setParentOrg(parent);
		org.setCreateuser(getUserinfo().getId());
		org.setCreatetime(new java.sql.Date(System.currentTimeMillis()));
		org.setModifyuser(getUserinfo().getId());
		org.setModifytime(new java.sql.Date(System.currentTimeMillis()));
		
		service.save(org);
		nextPage="orgManage.action?orgid="+org.getOrgid();
		message="保存成功！";
		return SUCCESS;
	}
	public void prepare() throws Exception {
		parent=(TsysOrg)service.get(TsysOrg.class,orgid);
	}
	public TsysOrg getOrg() {
		return org;
	}
	@Override
	public String input() throws Exception {
		return "input";
    }

	public void setOrgid(long orgid) {
		this.orgid = orgid;
	}

	public TsysOrg getParent() {
		return parent;
	}
}
