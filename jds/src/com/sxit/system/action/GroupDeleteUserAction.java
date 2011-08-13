package com.sxit.system.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;

/**
 *
 * <p>功能： 删除用户角色</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2004-10-11</p>
 * @版本： V1.0
 * @修改：
 */

public class GroupDeleteUserAction
    extends AbstractAction {

  private int userid;
  private int groupid;

  public GroupDeleteUserAction() {
    rights="sys3,4";

  }

  protected String go() throws HibernateException {

    nextpage = "groupViewUsers.action?groupid=" + groupid;
    getDelete(userid, groupid);
    message="删除用户角色成功";
    return SUCCESS;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  public void setGroupid(int groupid) {
    this.groupid = groupid;
  }

  private boolean getDelete(int userid, int groupid) throws HibernateException {
    String hqlDelete =
        "delete from TsysGroupUser where groupid =:groupid and userid in (:userid)";
    getSession().createQuery(hqlDelete)
        .setInteger("groupid", groupid)
        .setInteger("userid", userid)
        .executeUpdate();
    return true;
  }
}
