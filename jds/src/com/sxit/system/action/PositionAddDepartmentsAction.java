package com.sxit.system.action;

import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;

/**
 *
 * <p>功能： 设置部门职务</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2004-10-11</p>
 * @版本： V1.0
 * @修改：
 */

public class PositionAddDepartmentsAction
    extends AbstractAction {

  private List departmentlist;
  private List userlist;
  private int departmentid;
  private long userid;
  private int positionid;
  private TsysPosition position;

  public PositionAddDepartmentsAction() {
    rights = "sys7,4";
    departmentlist = new ArrayList();
  }

  protected String go() throws HibernateException {

    if ("save".equals(flag)) {
      nextpage = "positionViewDepartments.action?positionid=" + positionid;
      TsysDepartmentPosition depposition = new TsysDepartmentPosition();
      TsysDepartmentPositionPK comp_id = new TsysDepartmentPositionPK();
      TsysDepartment department = new TsysDepartment();
      TsysUser user = new TsysUser();
      department.setDepartmentid(departmentid);
      user.setUserid(userid);
      position = new TsysPosition();
      position.setPositionid(positionid);
      comp_id.setTsysDepartment(department);
      comp_id.setTsysPosition(position);
      depposition.setComp_id(comp_id);
      depposition.setTsysUser(user);

      getSession().save(depposition);
      message = "设定职务人员成功！";
      return "message";
    }
    else {
      TsysDepartmentPosition positiondepartment = null;
      List departmentidlist = new ArrayList();
      departmentidlist.add( -1);
      position = (TsysPosition) getSession().get(TsysPosition.class,
                                                 Integer.valueOf(positionid));
      Set positiondepartments = position.getTsysDepartmentPositions();
      for (Object obj : positiondepartments.toArray()) {
        positiondepartment = (TsysDepartmentPosition) obj;
        departmentidlist.add(positiondepartment.getComp_id().getTsysDepartment().
                             getDepartmentid());
      }
      TsysDepartment corp = (TsysDepartment) getSession().get(TsysDepartment.class,
          Integer.valueOf(1));
      if (corp != null) {
        selectSet(corp);
      }
      else {
        message = "部门缺少根，请与管理员联系！";
        return "message";
      }
      userlist = getQuery2().list();

      return "input";
    }
  }

  public int getPositionid() {
    return positionid;
  }

  public void setPositionid(int positionid) {
    this.positionid = positionid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  public void setDepartmentid(int departmentid) {
    this.departmentid = departmentid;
  }

  public TsysPosition getPosition() {
    return position;
  }

  private Query getQuery2() throws HibernateException {
    String queryName;
    queryName = "from TsysUser as user order by user.username desc";
    Query query = getSession().createQuery(queryName);
    return query;
  }

  public List getDepartmentlist() {
    return departmentlist;
  }

  public List getUserlist() {
    return userlist;
  }

  // 设置下拉选择框 树状迭代
  private String tempstr = "";
  public void selectSet(TsysDepartment corp) {
    String diplayname = tempstr + "-";
    corp.setDisplayname(diplayname + corp.getDepartmentname());
    this.departmentlist.add(corp);
    if (!corp.getChildren().isEmpty()) {
      for (TsysDepartment child : corp.getChildren()) {
        if (child.getDepartmentid() != 1&& child.getStatusid()==1) {
          tempstr = diplayname;
          selectSet(child);
        }
      }
    }
  }
}
