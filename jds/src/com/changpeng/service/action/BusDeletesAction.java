package com.changpeng.service.action;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sxit.common.action.AbstractAction;
import com.changpeng.service.model.*;

/**
 *
 * <p>功能： 删除多个车辆管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-08-28</p>
 * @版本： V1.0
 * @修改：
 */

public class BusDeletesAction extends AbstractAction {
  private Long[] check;
  public BusDeletesAction() {
    rights="ser3,8";
    nextpage="busList.action?pagenumber="+pagenumber;
  }

  protected String go() throws HibernateException {
    try {
      int num = this.getDelete(check);
      message = "删除" + num + "个车辆管理成功！";
      return "message";
    }
    catch (HibernateException e) {
      e.printStackTrace();
      message = "删除多个车辆管理失败！";
      return "message";
    }
  }

  private int getDelete(Long[] check) throws HibernateException {
    Session session = getSession();
    String hqlDelete = "delete from TserBus where busid in (:busids)";
    int deletedEntities = session.createQuery(hqlDelete)
        .setParameterList("busids", check)
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
