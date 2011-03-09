package com.uu800.admin.base.action;

import org.hibernate.HibernateException;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.service.RoleService;
import com.uu800.admin.base.entity.TsysLogs;

/**
 *
 * <p>功能： 删除多个角色管理</p>
 * <p>作者： zrb</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-05-22</p>
 * @版本： V1.0
 * @修改：
 */

public class RoleDeletesAction extends AbstractAdminAction {
  private Long[] check;
  private RoleService service;
  public RoleDeletesAction() {
    service = (RoleService)this.getBean("roleService");
    nextPage="roleList.action";
  }

  @Override
	public String execute()  {
    try {
      int num = service.deletes(check);
      message = "删除" + num + "个角色管理成功！";
//      log=new TsysLogs("删除多个角色",5,true); 
      return "sysmsg";
    }
    catch (HibernateException e) {
      e.printStackTrace();
      message = "删除多个角色管理失败！";
//      log=new TsysLogs("删除多个角色",5,false); 
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
