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

public class UserDeleteGroupAction
    extends AbstractAction {

  private int groupid;
  private int userid;

  public UserDeleteGroupAction() {
    rights = "sys2,4";

  }

  protected String go() throws HibernateException {

    nextpage = "userViewGroups.action?userid=" + userid;
    getDelete(groupid, userid);
    message="删除用户角色成功";
    return SUCCESS;
  }

  public void setGroupid(int groupid) {
    this.groupid = groupid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  private boolean getDelete(int groupid, int userid) throws HibernateException {
    String hqlDelete =
        "delete from TsysGroupUser where userid =:userid and groupid =:groupid";
    getSession().createQuery(hqlDelete)
        .setInteger("userid", userid)
        .setInteger("groupid", groupid)
        .executeUpdate();
    return true;
  }
}
