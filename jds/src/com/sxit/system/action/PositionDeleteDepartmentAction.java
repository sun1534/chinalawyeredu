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

public class PositionDeleteDepartmentAction
    extends AbstractAction {

  private String departmentid;
  private int positionid;

  public PositionDeleteDepartmentAction() {
     rights="sys4,4";

  }

  protected String go() throws HibernateException {

    nextpage = "positionViewDepartments.action?positionid=" + positionid;
    getDelete(departmentid, positionid);
    message="删除用户角色成功";
    return SUCCESS;
  }

  public void setDepartmentid(String departmentid) {
    this.departmentid = departmentid;
  }

  public void setPositionid(int positionid) {
    this.positionid = positionid;
  }

  private boolean getDelete(String departmentid, int positionid) throws HibernateException {
    String hqlDelete =
        "delete from TsysDepartmentPosition where positionid =:positionid and departmentid =:departmentid";
    getSession().createQuery(hqlDelete)
        .setInteger("positionid", positionid)
        .setString("departmentid", departmentid)
        .executeUpdate();
    return true;
  }
}
