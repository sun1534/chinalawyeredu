package com.sxit.system.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * <p>功能： 修改角色权限</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-11</p>
 * @版本： V1.0
 * @修改：
 */

public class PositionChangeDepartmentAction
    extends AbstractAction {

  private int departmentid;
  private int positionid;
  private TsysDepartmentPosition departmentposition;
  private TsysUser user;
  private List userlist;

  public PositionChangeDepartmentAction() {
    rights="sys4,4";

  }

  protected String go() throws HibernateException {
    if ("save".equals(flag)) {
      //departmentposition.setTsysUser(user);
     // departmentposition.
      getSession().update(departmentposition);
      nextpage = "positionViewDepartments.action?positionid=" + departmentposition.getComp_id().getTsysPosition().getPositionid();
      message="保存用户权限成功";
      return "message";
    }else{
      TsysDepartmentPositionPK compid = new TsysDepartmentPositionPK();
      TsysPosition position = (TsysPosition) getSession().get(TsysPosition.class,
          Integer.valueOf(positionid));
      if (position == null) {
        message = "未找到此职务";
        return "message";
      }
      TsysDepartment department = (TsysDepartment) getSession().get(TsysDepartment.class,
          Integer.valueOf(departmentid));
      if (department == null) {
        message = "未找到此部门";
        return "message";
      }
      compid.setTsysPosition(position);
      compid.setTsysDepartment(department);
      departmentposition = (TsysDepartmentPosition) getSession().get(TsysDepartmentPosition.class,
          compid);
      if (departmentposition == null) {
        message = "未找到此用户和此功能的权限";
        return "message";
      }
      userlist = getQuery().list();
      return SUCCESS;
    }
  }
  public void setDepartmentid(int departmentid) {
    this.departmentid = departmentid;
  }
  public void setPositionid(int positionid) {
    this.positionid = positionid;
  }
  public TsysDepartmentPosition getDepartmentposition() {
    return departmentposition;
  }

  public void setDepartmentposition(TsysDepartmentPosition departmentposition) {
    this.departmentposition = departmentposition;
  }
  public void setUser(TsysUser user) {
    this.user = user;
  }
  private Query getQuery() throws HibernateException {
          String queryName ;
          queryName="from TsysUser as user order by user.username";
          Query query = getSession().createQuery(queryName);
          return query;
        }
  public List getUserlist() {
    return userlist;
        }
}
