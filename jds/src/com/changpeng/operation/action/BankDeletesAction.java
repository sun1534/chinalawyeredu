package com.changpeng.operation.action;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sxit.common.action.AbstractAction;
import com.changpeng.operation.model.*;

/**
 *
 * <p>功能： 删除多个委托银行</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-09</p>
 * @版本： V1.0
 * @修改：
 */

public class BankDeletesAction extends AbstractAction {
  private Long[] check;
  public BankDeletesAction() {
    rights="opr1,8";
    nextpage="bankList.action?pagenumber="+pagenumber;
  }

  protected String go() throws HibernateException {
    try {
      int num = this.getDelete(check);
      message = "删除" + num + "个委托银行成功！";
      return "message";
    }
    catch (HibernateException e) {
      e.printStackTrace();
      message = "删除多个委托银行失败！";
      return "message";
    }
  }

  private int getDelete(Long[] check) throws HibernateException {
    Session session = getSession();
    String hqlDelete = "delete from ToprBank where bankid in (:bankids)";
    int deletedEntities = session.createQuery(hqlDelete)
        .setParameterList("bankids", check)
        .executeUpdate();
    return deletedEntities;
  }

  public Long[] getCheck() {
    return check;
  }

  public void setCheck(Long[] check) {
    this.check = check;
  }

}
