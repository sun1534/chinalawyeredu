package com.sxit.info.action;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sxit.common.action.AbstractAction;
import com.sxit.info.model.*;

/**
 *
 * <p>功能： 删除多个权限</p>
 * <p>作者： 吴桂荣</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-08-28</p>
 * @版本： V1.0
 * @修改：
 */

public class SetDeletesAction extends AbstractAction {
  private Long[] check;
  public SetDeletesAction() {
    rights="inf5,8";
    nextpage="setList.action?pagenumber="+pagenumber;
  }

  protected String go() throws HibernateException {
    try {
      int num = this.getDelete(check);
      message = "删除" + num + "个权限成功！";
      return "message";
    }
    catch (HibernateException e) {
      e.printStackTrace();
      message = "删除多个权限失败！";
      return "message";
    }
  }

  private int getDelete(Long[] check) throws HibernateException {
    Session session = getSession();
    String hqlDelete = "delete from TinfSet where setid in (:setids)";
    int deletedEntities = session.createQuery(hqlDelete)
        .setParameterList("setids", check)
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
