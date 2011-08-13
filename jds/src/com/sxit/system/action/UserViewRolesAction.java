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

public class UserViewRolesAction  extends AbstractAction {

  private TsysUser user;

  private int userid;
  private Set userroles;

  public UserViewRolesAction() {
   rights="sys2,1";
  }

  protected String go() throws org.hibernate.HibernateException {
    user=(TsysUser)getSession().get(TsysUser.class,Long.valueOf(userid));
    if(user==null)
    {
      nextpage = "listUser.action";
      message="此用户不存在！";
      return "message";
    }
    else{
      userroles = user.getTsysUserRoles();
      return SUCCESS;
     }
   }
   public TsysUser getUser() {
           return user;
   }
  public int getUserid() {
    return userid;
  }
  public void setUserid(int userid) {
    this.userid = userid;
  }
  public Set getUserroles() {
    return userroles;
  }
}
