package com.sxit.system.action;

import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;

import java.util.*;

/**
 *
 * <p>功能： 查看用户角色</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2004-10-10</p>
 * @版本： V1.0
 * @修改：
 */

public class RoleViewUsersAction  extends AbstractAction {

  private TsysRole role;

  private int roleid;
  private Set roleusers;

  public RoleViewUsersAction() {
   rights="sys4,1";
  }

  protected String go() throws org.hibernate.HibernateException {
    role=(TsysRole)getSession().get(TsysRole.class,Integer.valueOf(roleid));
    if(role==null)
    {
      nextpage = "listRole.action";
      message="此用户不存在！";
      return "message";
    }
    else{
      roleusers = role.getTsysUserRoles();
      return SUCCESS;
     }
   }
   public TsysRole getRole() {
           return role;
   }
  public int getRoleid() {
    return roleid;
  }
  public void setRoleid(int roleid) {
    this.roleid = roleid;
  }
  public Set getRoleusers() {
    return roleusers;
  }
}
