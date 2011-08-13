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

public class RoleDeleteFunctionAction
    extends AbstractAction {

  private String functionid;
  private int roleid;

  public RoleDeleteFunctionAction() {
     rights="sys4,4";

  }

  protected String go() throws HibernateException {

    nextpage = "roleViewFunctions.action?roleid=" + roleid;
    getDelete(functionid, roleid);
    message="删除用户角色成功";
    return SUCCESS;
  }

  public void setFunctionid(String functionid) {
    this.functionid = functionid;
  }

  public void setRoleid(int roleid) {
    this.roleid = roleid;
  }

  private boolean getDelete(String functionid, int roleid) throws HibernateException {
    String hqlDelete =
        "delete from TsysFunctionRole where roleid =:roleid and functionid =:functionid";
    getSession().createQuery(hqlDelete)
        .setInteger("roleid", roleid)
        .setString("functionid", functionid)
        .executeUpdate();
    return true;
  }
}
