package com.sxit.workflow.action;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sxit.common.action.AbstractAction;
import com.sxit.workflow.model.*;

/**
 *
 * <p>功能： 删除多个业务</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-11-13</p>
 * @版本： V1.0
 * @修改：
 */

public class BusinessDeletesAction extends AbstractAction {
  private Integer[] check;
  public BusinessDeletesAction() {
    rights="wfl2,8";
    nextpage="businessList.action?pagenumber="+pagenumber;
  }

  protected String go() throws HibernateException {
    try {
      int num = this.getDelete(check);
      message = "删除" + num + "个业务成功！";
      return "message";
    }
    catch (HibernateException e) {
      e.printStackTrace();
      message = "删除多个业务失败！";
      return "message";
    }
  }

  private int getDelete(Integer[] check) throws HibernateException {
    Session session = getSession();
    String hqlDelete = "delete from TwflBusiness where businessid in (:businessids)";
    int deletedEntities = session.createQuery(hqlDelete)
        .setParameterList("businessids", check)
        .executeUpdate();
    return deletedEntities;
  }

  public Integer[] getCheck() {
    return check;
  }

  public void setCheck(Integer[] check) {
    this.check = check;
  }

}
