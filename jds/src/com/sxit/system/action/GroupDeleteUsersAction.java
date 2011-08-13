package com.sxit.system.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;

/**
 *
 * <p>功能： 删除多个用户角色</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2004-10-11</p>
 * @版本： V1.0
 * @修改：
 */

public class GroupDeleteUsersAction
    extends AbstractAction {

  private TsysGroupUser groupuser;

  private int groupid;
  private String[] check;

  public GroupDeleteUsersAction() {
    rights="sys3,4";
    groupuser = new TsysGroupUser();

  }

  protected String go() throws HibernateException {
    nextpage = "groupViewUsers.action?groupid="+groupid;
    try {
      int num =this.getDelete(check,groupid);
      message = "删除" + num + "个角色成功！";
      return "message";
    }
    catch (HibernateException e) {
      e.printStackTrace();
      message = "删除多个角色失败！";
      return "message";
    }
  }

  public TsysGroupUser getGroupuser() {
    return groupuser;
  }

  public void setGroupuser(TsysGroupUser groupuser) {
    this.groupuser = groupuser;
  }

  public int getGroupid() {
    return groupid;
  }

  public void setGroupid(int groupid) {
    this.groupid = groupid;
  }

  public String[] getCheck() {
    return check;
  }

  public void setCheck(String[] check) {
    this.check = check;
  }


  private int getDelete(String[] check,int groupid) throws HibernateException {
    Session session = getSession();
            Transaction tx = session.beginTransaction();
            String hqlDelete = "delete from TsysGroupUser where groupid =:groupid and userid in (:userids)";
            int deletedEntities = session.createQuery( hqlDelete )
                                .setInteger("groupid", groupid)
                                .setParameterList ("userids",check)
                                .executeUpdate();
            tx.commit();
        session.close();
    return deletedEntities;
  }

}

