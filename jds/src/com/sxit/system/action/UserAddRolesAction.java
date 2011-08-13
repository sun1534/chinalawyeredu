package com.sxit.system.action;

import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;

/**
 *
 * <p>功能： 创建用户角色</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2004-10-11</p>
 * @版本： V1.0
 * @修改：
 */

public class UserAddRolesAction
    extends AbstractAction {

  private List rolelist;
  private String[] check;
  private int userid;

  public UserAddRolesAction() {
    rights="sys2,4";
  }

  protected String go() throws HibernateException {
    nextpage = "userViewRoles.action?userid="+userid;
    if ("save".equals(flag)) {
      getAdd(check,userid);
      message = "新增用户角色成功！";
      return "message";
    }
    else {
      TsysUserRole userrole =null;
      List roleidlist=new ArrayList();
      roleidlist.add(Integer.valueOf(-1));
      TsysUser user=(TsysUser)getSession().get(TsysUser.class,Long.valueOf(userid));
      Set userroles=user.getTsysUserRoles();
      for(Object obj:userroles.toArray())
      {
        userrole=(TsysUserRole)obj;
        roleidlist.add(userrole.getComp_id().getTsysRole().getRoleid());
      }
      rolelist = getQuery()
          .setParameterList("userroleids",roleidlist)
          .setCacheable(false)
          .list();

      return "input";
    }
  }

  public int getUserid() {
    return userid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  private Query getQuery() throws org.hibernate.HibernateException {
    Query query = getSession().createQuery("from TsysRole role where roleid not in (:userroleids) order by roleid desc");
    return query;
  }

  private boolean getAdd(String[] check,int userid) throws HibernateException {
//    String checkid;
//    checkid = "";
    TsysUserRole userrole =null;
    TsysUserRolePK userrolepk = new TsysUserRolePK();
    TsysUser user = new TsysUser();
    user.setUserid(new Integer(userid));
    TsysRole role = null;
    getSession().flush();
    for (int i = 0; i < check.length; i++) {
      userrole = new TsysUserRole();
      role = new TsysRole();
      role.setRoleid(new Integer(check[i]));
      userrolepk.setTsysRole(role);
      userrolepk.setTsysUser(user);
      userrole.setComp_id(userrolepk);
      getSession().save(userrole);
      getSession().flush();
    }
    return true;
  }

  public List getRolelist() {
    return rolelist;
  }
  public void setRolelist(List rolelist) {
    this.rolelist = rolelist;
  }

  public String[] getCheck() {
    return check;
  }
  public void setCheck(String[] check) {
    this.check = check;
  }

}

