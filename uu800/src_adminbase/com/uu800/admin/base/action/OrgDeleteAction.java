package com.uu800.admin.base.action;


import org.hibernate.HibernateException;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.*;
import com.uu800.admin.base.entity.TsysLogs;




/**
 *
 * <p>功能： 删除组织管理</p>
 * <p>作者： zrb</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-07-13</p>
 * @版本： V1.0
 * @修改：
 */

public class OrgDeleteAction extends AbstractAdminAction {

	private TsysOrg org;
    private long orgid;
    private OrgService service;

	public OrgDeleteAction() {
		service = (OrgService)this.getBean("orgService");
	}

	@Override
	public String execute() {
		if(orgid==0)
		{
			message="未找到组织";
//			log=new TsysLogs("删除组织信息:组织ID为"+orgid,5,false);   
			return "sysmsg";
		}
		
		if(orgid==1)
		{
			message="该组织信息不可删除";
//			log=new TsysLogs("删除组织信息:组织ID为"+orgid,5,false);   
			return "sysmsg";
		}
		
		org=(TsysOrg)service.get(TsysOrg.class,orgid);
		if(org==null){
        	message="未找到此组织管理";
//        	log=new TsysLogs("删除组织信息:组织ID为"+orgid,5,false);    		
        	return "sysmsg";
		}		
//		log=new TsysLogs("删除职位信息:职位ID为"+orgid+" 职位名称为"+org.getOrgname(),5,true);
		
		service.delete(org);
		nextPage="orgManage.action";
		message="删除成功！";
		return SUCCESS;
	}

	public TsysOrg getOrg() {
         if (org==null)
            org = (TsysOrg) get("org");
          return org;
	}

	public void setOrgid(long orgid) {

		this.orgid = orgid;
	}

	public long getOrgid() {
		return this.orgid;
	}

}
