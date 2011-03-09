package com.uu800.admin.base.action;

import org.hibernate.HibernateException;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.*;
import com.uu800.admin.base.entity.TsysLogs;


/**
 *
 * <p>功能： 查看组织管理</p>
 * <p>作者： zrb</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-07-13</p>
 * @版本： V1.0
 * @修改：
 */

public class OrgViewAction extends AbstractAdminAction {
	private TsysOrg org;
    private OrgService service;
    private long orgid;
	public OrgViewAction() {
		service = (OrgService)this.getBean("orgService");
	   org = new TsysOrg();
	}

	@Override
	public String execute() {
           nextPage="orgList.action?pageNo="+pageNo;
           org=(TsysOrg)service.get(TsysOrg.class,orgid);
           if(org==null){
             message="未找到此组织管理";
//             log=new TsysLogs("查看组织:组织ID为 "+orgid,1,false);
             return "sysmsg";
           }
//           log=new TsysLogs("查看组织:组织ID为 "+orgid+" 组织名称为 "+org.getOrgname(),1,true);
           
           return SUCCESS;
	}
	public TsysOrg getOrg() {
		return org;
	}
        public void setOrgid(long orgid) {

          this.orgid = orgid;
        }
        public long getOrgid() {
          return this.orgid;
        }

}
