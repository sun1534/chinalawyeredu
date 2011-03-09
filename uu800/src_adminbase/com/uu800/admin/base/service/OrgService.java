package com.uu800.admin.base.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.uu800.webbase.BasicService;
import com.uu800.webbase.util.TreeInfo;
import com.uu800.admin.base.dao.OrgDAO;
import com.uu800.admin.base.entity.TsysOrg;

public class OrgService  extends BasicService 
{
    private OrgDAO orgDAO;
	/**
	 * @param OrgDAO
	 */
    @Resource
	public void setOrgDAO(OrgDAO orgDAO) {
		this.orgDAO = orgDAO;
		super.basicDao = orgDAO;
	}

	/**
	 * 批量删除
	 */
	@Transactional
	public int deletes(Object[] ids)
	{
		String hql = "delete from TsysOrg where orgid in (:ids)";
		return this.orgDAO.deletes(hql,ids);
	}
	
	/**
	 * 
	 * @return
	 */
	public List getOrgList(){
		DetachedCriteria dc=DetachedCriteria.forClass(TsysOrg.class);
		dc.add(Restrictions.ne("orgid",0L));
		return basicDao.findByCriteria(dc);
	}
	
	
	/**
	 * 取组织JS
	 * @return LinkedHashMap
	 */	
	public String getOrgTree() {
		
	
		List<TreeInfo> list = new ArrayList();
		List orglist = this.orgDAO.getOrgList();
		
		TreeInfo  tree = new TreeInfo(0,-1,"Root","javascript: void(0);","orgmain");		 
		list.add(tree);
		
		for(Object obj:orglist)
		{	
			 Object[] str=(Object[]) obj;
			 java.math.BigDecimal bd0 =(java.math.BigDecimal)str[0];
			 int orgid = bd0.intValue();			 
			 java.math.BigDecimal bd1 =(java.math.BigDecimal)str[1];
			 int parentid = bd1.intValue();
			 String orgname =(String) str[2];
			 tree = new TreeInfo(orgid,parentid,orgname,"orgView.action?orgid="+orgid,"orgmain");
			 list.add(tree);
		}		
	
		return TreeInfo.createTreeInfo(list);
	}

}
