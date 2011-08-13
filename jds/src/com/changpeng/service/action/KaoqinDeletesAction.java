package com.changpeng.service.action;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sxit.common.action.AbstractAction;
import com.changpeng.service.model.*;

/**
 *
 * <p>功能： 删除多个考勤管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-07</p>
 * @版本： V1.0
 * @修改：
 */

public class KaoqinDeletesAction extends AbstractAction {
  private Long[] check;
  public KaoqinDeletesAction() {
    rights="ser4,8";
    nextpage="kaoqinList.action?pagenumber="+pagenumber;
  }

  protected String go() throws HibernateException {
    try {
      int num = this.getDelete(check);
      message = "删除" + num + "个考勤管理成功！";
      return "message";
    }
    catch (HibernateException e) {
      e.printStackTrace();
      message = "删除多个考勤管理失败！";
      return "message";
    }
  }

  private int getDelete(Long[] check) throws HibernateException {
    Session session = getSession();
    String hqlDelete = "delete from TserKaoqin where kaoqinid in (:kaoqinids)";
    int deletedEntities = session.createQuery(hqlDelete)
        .setParameterList("kaoqinids", check)
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
