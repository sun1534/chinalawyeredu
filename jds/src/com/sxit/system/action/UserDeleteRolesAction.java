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

public class UserDeleteRolesAction
    extends AbstractAction {

  private TsysUserRole userrole;

  private int userid;
  private String[] check;

  public UserDeleteRolesAction() {
    rights="sys2,4";
    userrole = new TsysUserRole();

  }

  protected String go() throws HibernateException {
    nextpage = "userViewRoles.action?userid="+userid;
    try {
      int num =this.getDelete(check,userid);
      message = "删除" + num + "个角色成功！";
      return "message";
    }
    catch (HibernateException e) {
      e.printStackTrace();
      message = "删除多个角色失败！";
      return "message";
    }
  }

  public TsysUserRole getUserrole() {
    return userrole;
  }

  public void setUserrole(TsysUserRole userrole) {
    this.userrole = userrole;
  }

  public int getUserid() {
    return userid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  public String[] getCheck() {
    return check;
  }

  public void setCheck(String[] check) {
    this.check = check;
  }


  private int getDelete(String[] check,int userid) throws HibernateException {
    Session session = getSession();
            Transaction tx = session.beginTransaction();
            String hqlDelete = "delete from TsysUserRole where userid =:userid and roleid in (:roleids)";
            int deletedEntities = session.createQuery( hqlDelete )
                                .setInteger("userid", userid)
                                .setParameterList ("roleids",check)
                                .executeUpdate();
            tx.commit();
        session.close();
    return deletedEntities;
  }

}

