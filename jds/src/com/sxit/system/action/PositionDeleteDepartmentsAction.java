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

public class PositionDeleteDepartmentsAction
    extends AbstractAction {

  private TsysDepartmentPosition positiondepartment;

  private int positionid;
  private String[] check;

  public PositionDeleteDepartmentsAction() {
     rights="sys4,4";
    positiondepartment = new TsysDepartmentPosition();

  }

  protected String go() throws HibernateException {
    nextpage = "positionViewDepartments.action?positionid="+positionid;
    try {
      int num =this.getDelete(check,positionid);
      message = "删除" + num + "个任职记录成功！";
      return "message";
    }
    catch (HibernateException e) {
      e.printStackTrace();
      message = "删除多个任职记录失败！";
      return "message";
    }
  }

  public TsysDepartmentPosition getPositiondepartment() {
    return positiondepartment;
  }

  public void setPositiondepartment(TsysDepartmentPosition positiondepartment) {
    this.positiondepartment = positiondepartment;
  }

  public int getPositionid() {
    return positionid;
  }

  public void setPositionid(int positionid) {
    this.positionid = positionid;
  }

  public String[] getCheck() {
    return check;
  }

  public void setCheck(String[] check) {
    this.check = check;
  }


  private int getDelete(String[] check,int positionid) throws HibernateException {
       String hqlDelete = "delete from TsysDepartmentPosition where positionid =:positionid and departmentid in (:departmentids)";
       int deletedEntities = getSession().createQuery( hqlDelete )
                            .setInteger("positionid", positionid)
                            .setParameterList ("departmentids",check)
                            .executeUpdate();
    return deletedEntities;
  }

}

