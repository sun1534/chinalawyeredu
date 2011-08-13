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

public class UserDeleteFunctionAction
    extends AbstractAction {

  private String functionid;
  private int userid;

  public UserDeleteFunctionAction() {
    rights = "sys2,4";

  }

  protected String go() throws HibernateException {

    nextpage = "userViewFunctions.action?userid=" + userid;
    getDelete(functionid, userid);
    message="删除用户角色成功";
    return SUCCESS;
  }

  public void setFunctionid(String functionid) {
    this.functionid = functionid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  private boolean getDelete(String functionid, int userid) throws HibernateException {
    String hqlDelete =
        "delete from TsysFunctionUser where userid =:userid and functionid =:functionid";
    getSession().createQuery(hqlDelete)
        .setInteger("userid", userid)
        .setString("functionid", functionid)
        .executeUpdate();
    return true;
  }
}
