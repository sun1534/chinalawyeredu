package com.sxit.member.action;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sxit.common.action.AbstractAction;
import com.sxit.member.model.*;

/**
 *
 * <p>功能： 删除多个简历录入</p>
 * <p>作者： 雷华</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-05-13</p>
 * @版本： V1.0
 * @修改：
 */

public class ResumeDeletesAction extends AbstractAction {
  private Integer[] check;
  public ResumeDeletesAction() {
    rights="mem2,8";
    nextpage="resumeList.action?pagenumber="+pagenumber;
  }

  protected String go() throws HibernateException {
    try {
      int num = this.getDelete(check);
      message = "删除" + num + "个简历录入成功！";
      return "message";
    }
    catch (HibernateException e) {
      e.printStackTrace();
      message = "删除多个简历录入失败！";
      return "message";
    }
  }

  private int getDelete(Integer[] check) throws HibernateException {
    Session session = getSession();
    String hqlDelete = "delete from TmemResume where resumeid in (:resumeids)";
    int deletedEntities = session.createQuery(hqlDelete)
        .setParameterList("resumeids", check)
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
