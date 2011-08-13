package com.sxit.member.action;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sxit.common.action.AbstractAction;
import com.sxit.member.model.*;

/**
 *
 * <p>功能： 删除多个会员登录</p>
 * <p>作者： 罗裴</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-04-24</p>
 * @版本： V1.0
 * @修改：
 */

public class MemberDeletesAction extends AbstractAction {
  private Integer[] check;
  public MemberDeletesAction() {
    rights="mem1,8";
    nextpage="memberList.action?pagenumber="+pagenumber;
  }

  protected String go() throws HibernateException {
    try {
      int num = this.getDelete(check);
      message = "删除" + num + "个会员成功！";
      return "message";
    }
    catch (HibernateException e) {
      e.printStackTrace();
      message = "删除多个会员失败！";
      return "message";
    }
  }

  private int getDelete(Integer[] check) throws HibernateException {
    Session session = getSession();
    String hqlDelete = "delete from TmemMember where memberid in (:memberids)";
    int deletedEntities = session.createQuery(hqlDelete)
        .setParameterList("memberids", check)
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
