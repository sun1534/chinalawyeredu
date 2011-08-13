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

public class RoleAddUsersAction
    extends AbstractAction {

  private List userlist;
  private String[] check;
  private int roleid;

  public RoleAddUsersAction() {
     rights="sys4,4";
  }

  protected String go() throws HibernateException {
    nextpage = "roleViewUsers.action?roleid="+roleid;
    if ("save".equals(flag)) {
      getAdd(check,roleid);
      message = "新增用户角色成功！";
      return "message";
    }
    else {
      TsysUserRole roleuser =null;
      List useridlist=new ArrayList();
      useridlist.add(Long.valueOf(-1));
      TsysRole role=(TsysRole)getSession().get(TsysRole.class,Integer.valueOf(roleid));
      Set roleusers=role.getTsysUserRoles();
      for(Object obj:roleusers.toArray())
      {
        roleuser=(TsysUserRole)obj;
        useridlist.add(roleuser.getComp_id().getTsysUser().getUserid());
      }
      userlist = getQuery()
          .setParameterList("roleuserids",useridlist)
          .setCacheable(false)
          .list();

      return "input";
    }
  }

  public int getRoleid() {
    return roleid;
  }

  public void setRoleid(int roleid) {
    this.roleid = roleid;
  }

  private Query getQuery() throws org.hibernate.HibernateException {
    Query query = getSession().createQuery("from TsysUser user where userid not in (:roleuserids) order by username");
    return query;
  }

  private boolean getAdd(String[] check,int roleid) throws HibernateException {
//    String checkid;
//    checkid = "";
    TsysUserRole roleuser =null;
    TsysUserRolePK roleuserpk = new TsysUserRolePK();
    TsysRole role = new TsysRole();
    role.setRoleid(new Integer(roleid));
    TsysUser user = null;
    getSession().flush();
    for (int i = 0; i < check.length; i++) {
      roleuser = new TsysUserRole();
      user = new TsysUser();
      user.setUserid(new Long(check[i]));
      roleuserpk.setTsysUser(user);
      roleuserpk.setTsysRole(role);
      roleuser.setComp_id(roleuserpk);
      getSession().save(roleuser);
      getSession().flush();
    }
    return true;
  }

  public List getUserlist() {
    return userlist;
  }
  public void setUserlist(List userlist) {
    this.userlist = userlist;
  }

  public String[] getCheck() {
    return check;
  }
  public void setCheck(String[] check) {
    this.check = check;
  }

}

