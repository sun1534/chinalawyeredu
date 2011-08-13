package com.sxit.wait.action;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.sxit.common.action.AbstractAction;


/**
 *
 * <p>功能： 删除多个办件</p>
 * <p>作者： zrb</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2005-04-29</p>
 * @版本： V1.0
 * @修改：
 */

public class DeleteWaitsAction
    extends AbstractAction {
  private Integer[] check;
  public DeleteWaitsAction() {
    rights = "wat1,4";
    nextpage = "listWait4.action";
  }

  protected String go() throws org.hibernate.HibernateException {
    try {
      int num = this.getDelete(check);
      message = "删除" + num + "个办件成功！";
      return "message";
    }
    catch (HibernateException e) {
      e.printStackTrace();
      message = "删除多个办件失败！";
      return "message";
    }
  }

  private int getDelete(Integer[] check) throws HibernateException {
    Session session = getSession();
    String hqlDelete = "delete from TwatWait where waitid in (:waitids)";
    int deletedEntities = session.createQuery(hqlDelete)
        .setParameterList("waitids", check)
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
