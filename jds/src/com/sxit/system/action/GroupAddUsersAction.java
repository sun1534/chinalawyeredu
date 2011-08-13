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

public class GroupAddUsersAction
    extends AbstractAction {

  private List userlist;
  private String[] check;
  private int groupid;

  public GroupAddUsersAction() {
    rights="sys3,4";
  }

  protected String go() throws HibernateException {
    nextpage = "groupViewUsers.action?groupid="+groupid;
    if ("save".equals(flag)) {
      getAdd(check,groupid);
      message = "新增用户角色成功！";
      return "message";
    }
    else {
      TsysGroupUser groupuser =null;
      List useridlist=new ArrayList();
      useridlist.add(Long.valueOf(-1));
      TsysGroup group=(TsysGroup)getSession().get(TsysGroup.class,Integer.valueOf(groupid));
      Set groupusers=group.getTsysGroupUsers();
      for(Object obj:groupusers.toArray())
      {
        groupuser=(TsysGroupUser)obj;
        useridlist.add(groupuser.getComp_id().getTsysUser().getUserid());
      }
      userlist = getQuery()
          .setParameterList("groupuserids",useridlist)
          .setCacheable(false)
          .list();

      return "input";
    }
  }

  public int getGroupid() {
    return groupid;
  }

  public void setGroupid(int groupid) {
    this.groupid = groupid;
  }

  private Query getQuery() throws org.hibernate.HibernateException {
    Query query = getSession().createQuery("from TsysUser user where userid not in (:groupuserids) order by userid desc");
    return query;
  }

  private boolean getAdd(String[] check,int groupid) throws HibernateException {
//    String checkid;
//    checkid = "";
    TsysGroupUser groupuser =null;
    TsysGroupUserPK groupuserpk = new TsysGroupUserPK();
    TsysGroup group = new TsysGroup();
    group.setGroupid(new Integer(groupid));
    TsysUser user = null;
    getSession().flush();
    for (int i = 0; i < check.length; i++) {
      groupuser = new TsysGroupUser();
      user = new TsysUser();
      user.setUserid(new Long(check[i]));
      groupuserpk.setTsysUser(user);
      groupuserpk.setTsysGroup(group);
      groupuser.setComp_id(groupuserpk);
      getSession().save(groupuser);
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

