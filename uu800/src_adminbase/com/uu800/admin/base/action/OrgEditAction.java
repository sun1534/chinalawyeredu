package com.uu800.admin.base.action;


import org.hibernate.HibernateException;
import com.opensymphony.xwork2.Preparable;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.*;
import com.uu800.admin.base.entity.TsysLogs;

/**
 *
 * @功能： 编辑组织管理</p>
 * @作者： zrb
 * @公司： 深圳信科
 * @日期： 2009-07-13
 * @版本： V1.0
 * @修改：
 */

public class OrgEditAction extends AbstractAdminAction implements  Preparable{

    private long orgid;
	private TsysOrg org;
    private OrgService service;

	public OrgEditAction() {
		service = (OrgService)this.getBean("orgService");
	}

	@Override
	public String execute() {
//		log=new TsysLogs("编辑组织:组织ID为"+orgid+" 组织名称为"+org.getOrgname(),4,true);
		org.setModifyuser(getUserinfo().getId());
		org.setModifytime(new java.sql.Date(System.currentTimeMillis()));
		service.update(org);      
        nextPage="orgView.action?orgid="+org.getOrgid();
        message="保存成功！";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		return "input";
	}

	public void prepare() throws Exception {
		org=(TsysOrg)service.get(TsysOrg.class,orgid);
	}

	public TsysOrg getOrg() {
		return org;
	}

	public void setOrg(TsysOrg org) {
		this.org=org;
	}

	public void setOrgid(long orgid) {

		this.orgid = orgid;
	}
	public long getOrgid() {
		return this.orgid;
	}


}
