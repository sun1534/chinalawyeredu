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

public class UserDeleteFunctionsAction
    extends AbstractAction {

  private TsysFunctionUser userfunction;

  private int userid;
  private String[] check;

  public UserDeleteFunctionsAction() {
    rights="sys2,4";
    userfunction = new TsysFunctionUser();

  }

  protected String go() throws HibernateException {
    nextpage = "userViewFunctions.action?userid="+userid;
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

  public TsysFunctionUser getUserfunction() {
    return userfunction;
  }

  public void setUserfunction(TsysFunctionUser userfunction) {
    this.userfunction = userfunction;
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
            String hqlDelete = "delete from TsysFunctionUser where userid =:userid and functionid in (:functionids)";
            int deletedEntities = session.createQuery( hqlDelete )
                                .setInteger("userid", userid)
                                .setParameterList ("functionids",check)
                                .executeUpdate();
            tx.commit();
        session.close();
    return deletedEntities;
  }

}

