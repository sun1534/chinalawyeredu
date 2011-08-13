package com.sxit.workflow.action;

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

public class BusinessDeleteUserAction
    extends AbstractAction {

  private int userid;
  private int businessid;

  public BusinessDeleteUserAction() {
    rights="wfl2,4";

  }

  protected String go() throws HibernateException {

    nextpage = "businessViewUsers.action?businessid=" + businessid;
    getDelete(userid, businessid);
    message="删除业务用户成功";
    return SUCCESS;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  public void setBusinessid(int businessid) {
    this.businessid = businessid;
  }

  private boolean getDelete(int userid, int businessid) throws HibernateException {
    String hqlDelete =
        "delete from TwflBusinessUser where businessid =:businessid and userid in (:userid)";
    getSession().createQuery(hqlDelete)
        .setInteger("businessid", businessid)
        .setInteger("userid", userid)
        .executeUpdate();
    return true;
  }
}
