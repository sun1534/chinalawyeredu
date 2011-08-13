package com.sxit.system.action;

import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;

import java.util.*;

/**
 *
 * <p>功能： 查看用户角色</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2004-10-10</p>
 * @版本： V1.0
 * @修改：
 */

public class PositionViewDepartmentsAction  extends AbstractAction {

  private TsysPosition position;

  private int positionid;
  private Set positiondepartments;

  public PositionViewDepartmentsAction() {
   // rights="opm2,1";
  }

  protected String go() throws org.hibernate.HibernateException {
    position=(TsysPosition)getSession().get(TsysPosition.class,Integer.valueOf(positionid));
    if(position==null)
    {
      nextpage = "listPosition.action";
      message="此职务不存在！";
      return "message";
    }
    else{
      positiondepartments = position.getTsysDepartmentPositions();
      return SUCCESS;
     }
   }
   public TsysPosition getPosition() {
           return position;
   }
  public int getPositionid() {
    return positionid;
  }
  public void setPositionid(int positionid) {
    this.positionid = positionid;
  }
  public Set getPositiondepartments() {
    return positiondepartments;
  }
}
