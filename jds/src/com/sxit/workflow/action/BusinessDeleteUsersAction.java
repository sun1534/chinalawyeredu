package com.sxit.workflow.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sxit.common.action.AbstractAction;
import com.sxit.workflow.model.*;

/**
 *
 * <p>功能： 删除多个用户角色</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2004-10-11</p>
 * @版本： V1.0
 * @修改：
 */

public class BusinessDeleteUsersAction
    extends AbstractAction {

  private TwflBusinessUser businessuser;

  private int businessid;
  private String[] check;

  public BusinessDeleteUsersAction() {
    rights="wfl2,4";
    businessuser = new TwflBusinessUser();

  }

  protected String go() throws HibernateException {
    nextpage = "businessViewUsers.action?businessid="+businessid;
    try {
      int num =this.getDelete(check,businessid);
      message = "删除" + num + "个业务用户成功！";
      return "message";
    }
    catch (HibernateException e) {
      e.printStackTrace();
      message = "删除多个业务用户失败！";
      return "message";
    }
  }

  public TwflBusinessUser getBusinessuser() {
    return businessuser;
  }

  public void setBusinessuser(TwflBusinessUser businessuser) {
    this.businessuser = businessuser;
  }

  public int getBusinessid() {
    return businessid;
  }

  public void setBusinessid(int businessid) {
    this.businessid = businessid;
  }

  public String[] getCheck() {
    return check;
  }

  public void setCheck(String[] check) {
    this.check = check;
  }


  private int getDelete(String[] check,int businessid) throws HibernateException {
    Session session = getSession();
            Transaction tx = session.beginTransaction();
            String hqlDelete = "delete from TwflBusinessUser where businessid =:businessid and userid in (:userids)";
            int deletedEntities = session.createQuery( hqlDelete )
                                .setInteger("businessid", businessid)
                                .setParameterList ("userids",check)
                                .executeUpdate();
            tx.commit();
        session.close();
    return deletedEntities;
  }

}

