package com.changpeng.help.action;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sxit.common.action.AbstractAction;
import com.changpeng.help.model.*;

/**
 *
 * <p>功能： 删除多个问题与建议</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-18</p>
 * @版本： V1.0
 * @修改：
 */

public class SuggestDeletesAction extends AbstractAction {
  private Long[] check;
  public SuggestDeletesAction() {
    rights="hlp1,8";
    nextpage="suggestList.action?pagenumber="+pagenumber;
  }

  protected String go() throws HibernateException {
    try {
      int num = this.getDelete(check);
      message = "删除" + num + "个问题与建议成功！";
      return "message";
    }
    catch (HibernateException e) {
      e.printStackTrace();
      message = "删除多个问题与建议失败！";
      return "message";
    }
  }

  private int getDelete(Long[] check) throws HibernateException {
    Session session = getSession();
    String hqlDelete = "delete from ThlpSuggest where suggestid in (:suggestids)";
    int deletedEntities = session.createQuery(hqlDelete)
        .setParameterList("suggestids", check)
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
