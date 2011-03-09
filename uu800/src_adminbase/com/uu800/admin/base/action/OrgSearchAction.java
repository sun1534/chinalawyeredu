package com.uu800.admin.base.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.MatchMode;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;

import com.uu800.admin.base.service.*;
import java.util.*;


/**
 *
 * @功能： 查询组织管理</p>
 * @作者： zrb
 * @公司： 深圳信科
 * @日期： 2009-07-13
 * @版本： V1.0
 * @修改：
 */


public class OrgSearchAction  extends AbstractAdminAction {
	private List orglist;
	private TsysOrg org;
    private OrgService service;

	public OrgSearchAction() {
		service = (OrgService)this.getBean("orgService");
		org=new TsysOrg();
	}

	@Override
	public String execute()  {

		DetachedCriteria dc = DetachedCriteria.forClass(TsysOrg.class);

		if(org.getPathcode()!=null&&!"".equals(org.getPathcode()))
		{
			dc.add(Restrictions.like("pathcode", org.getPathcode(),MatchMode.ANYWHERE));
		}

		if(org.getOrgtype()!=null&&!"".equals(org.getOrgtype()))
		{
			dc.add(Restrictions.like("orgtype", org.getOrgtype(),MatchMode.ANYWHERE));
		}

		if(org.getOrgcode()!=null&&!"".equals(org.getOrgcode()))
		{
			dc.add(Restrictions.like("orgcode", org.getOrgcode(),MatchMode.ANYWHERE));
		}

		if(org.getOrgshortname()!=null&&!"".equals(org.getOrgshortname()))
		{
			dc.add(Restrictions.like("orgshortname", org.getOrgshortname(),MatchMode.ANYWHERE));
		}

		if(org.getOrgenname()!=null&&!"".equals(org.getOrgenname()))
		{
			dc.add(Restrictions.like("orgenname", org.getOrgenname(),MatchMode.ANYWHERE));
		}

		if(org.getOrgname()!=null&&!"".equals(org.getOrgname()))
		{
			dc.add(Restrictions.like("orgname", org.getOrgname(),MatchMode.ANYWHERE));
		}

		

		if(org.getContactperson()!=null&&!"".equals(org.getContactperson()))
		{
			dc.add(Restrictions.like("contactperson", org.getContactperson(),MatchMode.ANYWHERE));
		}

		if(org.getContactphone()!=null&&!"".equals(org.getContactphone()))
		{
			dc.add(Restrictions.like("contactphone", org.getContactphone(),MatchMode.ANYWHERE));
		}

		if(org.getMobile()!=null&&!"".equals(org.getMobile()))
		{
			dc.add(Restrictions.like("mobile", org.getMobile(),MatchMode.ANYWHERE));
		}

		if(org.getFaxnum()!=null&&!"".equals(org.getFaxnum()))
		{
			dc.add(Restrictions.like("faxnum", org.getFaxnum(),MatchMode.ANYWHERE));
		}

		if(org.getComments()!=null&&!"".equals(org.getComments()))
		{
			dc.add(Restrictions.like("comments", org.getComments(),MatchMode.ANYWHERE));
		}

		orglist = service.findByCriteria(dc, 100); //将结果集一次输出
//		page. = orglist.size();
		return SUCCESS;
   }

   @Override
   public String input() throws Exception {
		return "input";
   }

   public List getOrglist() {
		return orglist;
   }

   public void setOrg(TsysOrg org) {
		this.org=org;
   }

   public TsysOrg getOrg() {
		return org;
   }


}

