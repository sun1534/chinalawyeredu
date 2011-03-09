package com.uu800.admin.base.action;

import org.hibernate.HibernateException;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.service.*;
import com.uu800.admin.base.entity.TsysLogs;

/**
 *
 * @功能： 删除多个组织管理
 * @作者： zrb
 * @公司： 深圳信科
 * @日期： 2009-07-13
 * @版本： V1.0
 * @修改：
 */

public class OrgDeletesAction extends AbstractAdminAction {
  private Long[] check;
    private OrgService service;
  public OrgDeletesAction() {
		service = (OrgService)this.getBean("orgService");
    nextPage="orgList.action";
  }

  @Override
	public String execute() {
    try {
      int num = service.deletes(check);
      message = "删除" + num + "个组织管理成功！";
//      log=new TsysLogs("删除多个组织",5,true); 
      return "sysmsg";
    }
    catch (HibernateException e) {
      e.printStackTrace();
      message = "删除多个组织管理失败！";
//      log=new TsysLogs("删除多个组织",5,false); 
      return "sysmsg";
    }
  }

  public Long[] getCheck() {
    return check;
  }

  public void setCheck(Long[] check) {
    this.check = check;
  }

}
