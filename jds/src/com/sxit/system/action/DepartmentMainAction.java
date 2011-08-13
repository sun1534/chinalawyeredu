//$Id: ListRolesAction.java,v 1.5 2003/12/13 13:37:49 gavin Exp $
package com.sxit.system.action;

import java.util.*;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractListAction;
import com.sxit.system.model.TsysDepartment;

/**
 *
 * @author zrb
 */
public class DepartmentMainAction
    extends AbstractListAction {
  private Set departmentlist;

  public String go() throws HibernateException {
    TsysDepartment root = (TsysDepartment) getSession().get(TsysDepartment.class,
        Integer.valueOf( -1));
    if (root == null) {
      message = "未找到根，请与管理员联系！";
      return "message";
    }
    departmentlist = root.getChildren();
    departmentlist.remove(root);
    return SUCCESS;
  }

  public Set getDepartmentlist() {
    return departmentlist;
  }
}
