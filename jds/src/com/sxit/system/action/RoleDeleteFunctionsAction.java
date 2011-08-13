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

public class RoleDeleteFunctionsAction
    extends AbstractAction {

  private TsysFunctionRole rolefunction;

  private int roleid;
  private String[] check;

  public RoleDeleteFunctionsAction() {
     rights="sys4,4";
    rolefunction = new TsysFunctionRole();

  }

  protected String go() throws HibernateException {
    nextpage = "roleViewFunctions.action?roleid="+roleid;
    try {
      int num =this.getDelete(check,roleid);
      message = "删除" + num + "个角色成功！";
      return "message";
    }
    catch (HibernateException e) {
      e.printStackTrace();
      message = "删除多个角色失败！";
      return "message";
    }
  }

  public TsysFunctionRole getRolefunction() {
    return rolefunction;
  }

  public void setRolefunction(TsysFunctionRole rolefunction) {
    this.rolefunction = rolefunction;
  }

  public int getRoleid() {
    return roleid;
  }

  public void setRoleid(int roleid) {
    this.roleid = roleid;
  }

  public String[] getCheck() {
    return check;
  }

  public void setCheck(String[] check) {
    this.check = check;
  }


  private int getDelete(String[] check,int roleid) throws HibernateException {
    Session session = getSession();
            Transaction tx = session.beginTransaction();
            String hqlDelete = "delete from TsysFunctionRole where roleid =:roleid and functionid in (:functionids)";
            int deletedEntities = session.createQuery( hqlDelete )
                                .setInteger("roleid", roleid)
                                .setParameterList ("functionids",check)
                                .executeUpdate();
            tx.commit();
        session.close();
    return deletedEntities;
  }

}

