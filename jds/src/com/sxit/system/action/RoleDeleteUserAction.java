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

public class RoleDeleteUserAction
    extends AbstractAction {

  private long userid;
  private int roleid;

  public RoleDeleteUserAction() {
     rights="sys4,4";

  }

  protected String go() throws HibernateException {

    nextpage = "roleViewUsers.action?roleid=" + roleid;
    getDelete(userid, roleid);
    message="删除用户角色成功";
    return SUCCESS;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  public void setRoleid(int roleid) {
    this.roleid = roleid;
  }

  private boolean getDelete(long userid, int roleid) throws HibernateException {
    String hqlDelete =
        "delete from TsysUserRole where roleid =:roleid and userid in (:userid)";
    getSession().createQuery(hqlDelete)
        .setInteger("roleid", roleid)
        .setLong("userid", userid)
        .executeUpdate();
    return true;
  }
}
