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

public class UserDeleteRoleAction
    extends AbstractAction {

  private int roleid;
  private int userid;

  public UserDeleteRoleAction() {
    rights = "sys2,4";

  }

  protected String go() throws HibernateException {

    nextpage = "userViewRoles.action?userid=" + userid;
    getDelete(roleid, userid);
    message="删除用户角色成功";
    return SUCCESS;
  }

  public void setRoleid(int roleid) {
    this.roleid = roleid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  private boolean getDelete(int roleid, int userid) throws HibernateException {
    String hqlDelete =
        "delete from TsysUserRole where userid =:userid and roleid in (:roleid)";
    getSession().createQuery(hqlDelete)
        .setInteger("userid", userid)
        .setInteger("roleid", roleid)
        .executeUpdate();
    return true;
  }
}
