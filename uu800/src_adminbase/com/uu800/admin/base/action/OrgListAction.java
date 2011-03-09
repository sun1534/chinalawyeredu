package com.uu800.admin.base.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.MatchMode;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.*;


/**
 *
 * @功能： 列表组织管理
 * @作者： zrb
 * @公司： 深圳信科
 * @日期： 2009-07-13
 * @版本： V1.0
 * @修改：
 */

public class OrgListAction extends AbstractAdminAction  {
	private List orglist;
	private String orgname;
    private OrgService service;
	public OrgListAction() {
		service = (OrgService)this.getBean("orgService");
	}
	@Override
	public String execute() {
		DetachedCriteria dc = DetachedCriteria.forClass(TsysOrg.class);
		if(orgname!=null&&!"".equals(orgname))
		{
			dc.add(Restrictions.like("orgname", orgname,MatchMode.ANYWHERE));
		}
		
		dc.addOrder(Order.desc("orgid"));
//		page= service.findPageOnPageNo(dc,page);
		page = service.findPageOnPageNo(dc, pageSize, pageNo);
		orglist = page.getItems();
	    return SUCCESS;
	}
		
	public List getOrglist() {
		return orglist;
	}

	public void setOrgname(String orgname) {

		this.orgname = orgname;
	}

	public String getOrgname() {
		return this.orgname;
	}
}
