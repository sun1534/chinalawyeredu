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

public class UserAddGroupsAction
    extends AbstractAction {

  private List grouplist;
  private String[] check;
  private int userid;

  public UserAddGroupsAction() {
    rights="sys2,4";
  }

  protected String go() throws HibernateException {
    nextpage = "userViewGroups.action?userid="+userid;
    if ("save".equals(flag)) {
      getAdd(check,userid);
      message = "新增用户角色成功！";
      return "message";
    }
    else {
      TsysGroupUser userrole =null;
      List groupidlist=new ArrayList();
      groupidlist.add(Integer.valueOf(-1));
      TsysUser user=(TsysUser)getSession().get(TsysUser.class,Long.valueOf(userid));
      Set userroles=user.getTsysGroupUsers();
      for(Object obj:userroles.toArray())
      {
        userrole=(TsysGroupUser)obj;
        groupidlist.add(userrole.getComp_id().getTsysGroup().getGroupid());
      }
      grouplist = getQuery()
          .setParameterList("userroleids",groupidlist)
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
    Query query = getSession().createQuery("from TsysGroup group where groupid not in (:userroleids) order by groupid desc");
    return query;
  }

  private boolean getAdd(String[] check,int userid) throws HibernateException {
//    String checkid;
//    checkid = "";
    TsysGroupUser userrole =null;
    TsysGroupUserPK userrolepk = new TsysGroupUserPK();
    TsysUser user = new TsysUser();
    user.setUserid(new Integer(userid));
    TsysGroup group = null;
    getSession().flush();
    for (int i = 0; i < check.length; i++) {
      userrole = new TsysGroupUser();
      group = new TsysGroup();
      group.setGroupid(new Integer(check[i]));
      userrolepk.setTsysGroup(group);
      userrolepk.setTsysUser(user);
      userrole.setComp_id(userrolepk);
      getSession().save(userrole);
      getSession().flush();
    }
    return true;
  }

  public List getGrouplist() {
    return grouplist;
  }
  public void setGrouplist(List grouplist) {
    this.grouplist = grouplist;
  }

  public String[] getCheck() {
    return check;
  }
  public void setCheck(String[] check) {
    this.check = check;
  }

}

