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

public class UserEditAction
    extends AbstractAction {

  private TsysUser user;
  private List<TsysDepartment> departmentlist;
  private String password;
  public UserEditAction() {
    rights="sys2,4";
    departmentlist = new ArrayList<TsysDepartment> ();
  }

  public String go() throws HibernateException {
    if(password.length()>5)
    {
      user.setPassword(md5.MD5(password));
    }
    getSession().update(user);
    set("user", user);
    nextpage = "userList.action";
    message="保存成功";
    return SUCCESS;
  }

  public TsysUser getUser() {
    if (user == null) {
      user = (TsysUser) get("user");
    }
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
      if(child.getDepartmentid()!=1&& child.getStatusid()==1)
      {
      tempstr=diplayname;
      selectSet(child);
      }
    }
  }
  public List getDepartmentlist() {
    return departmentlist;
  }
  public void setPassword(String password){
    this.password=password;
  }
}
