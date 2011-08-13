package com.sxit.system.action;

import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;
import java.util.List;
import java.util.ArrayList;
import com.sxit.common.util.md5;

/**
 *
 * @author zrb
 */
public class UserCreateAction
    extends AbstractAction {

  private TsysUser user;
  private List<TsysDepartment> departmentlist;

  public UserCreateAction() {
    rights="sys2,2";
    user = new TsysUser();
    departmentlist = new ArrayList<TsysDepartment>();
  }

  public String go() throws HibernateException {
    user.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
    user.setPassword(md5.MD5(user.getPassword()));
    getSession().save(user);
    set("user", user);
    nextpage="userList.action";
    message="保存成功！";
    return SUCCESS;
  }

  public TsysUser getUser() {
    return user;
  }

  public String input() throws Exception {
    TsysDepartment corp = (TsysDepartment) getSession().get(TsysDepartment.class,
        Integer.valueOf(1));
    if (corp != null) {
      selectSet(corp);
    }
    else {
      message = "部门缺少根，请与管理员联系！";
      return "message";
    }
    return "input";
  }
  // 设置下拉选择框 树状迭代
  private String tempstr="";
  public void selectSet(TsysDepartment corp) {
    String diplayname=tempstr+"-";
    corp.setDisplayname(diplayname+corp.getDepartmentname());
    this.departmentlist.add(corp);
    if(!corp.getChildren().isEmpty())
    for (TsysDepartment child : corp.getChildren()) {
      if(child.getDepartmentid()!=1 && child.getStatusid()==1)
      {
      tempstr=diplayname;
      selectSet(child);
      }
    }
   }
 public List getDepartmentlist() {
   return departmentlist;
        }
}
