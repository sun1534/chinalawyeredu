package com.sxit.workflow.action;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.sxit.common.action.AbstractAction;
import com.sxit.workflow.model.TwflProcess;

/**
 *
 * <p>功能： 删除多个流程</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-15</p>
 * @版本： V1.0
 * @修改：
 */

public class ProcessDeletesAction
    extends AbstractAction {
  private TwflProcess process;
  private int processid;
  private String[] check;
  private java.util.List processlist;
  public ProcessDeletesAction() {
    rights="wfl1,4";
    process = new TwflProcess();
    nextpage = "listProcess.action";
  }
  protected String go() throws org.hibernate.HibernateException {
    org.hibernate.Transaction tx = null;
    try {
      tx = getSession().beginTransaction();
      getDelete(check);
      getSession().flush();
      tx.commit();
      message = "删除" + check.length + "个流程成功！";
      return "message";
    }
    catch (HibernateException e) {
      tx.rollback();
      e.printStackTrace();
      message = "删除多个流程失败！";
      return "message";
    }
  }
  public TwflProcess getProcess() {
    return process;
  }
  private boolean getDelete(String[] check) throws HibernateException {
    String checkid;
    checkid = "";
    for (int i = 0; i < check.length; i++) {
      if (i == 0) {
        checkid = check[i];
      }
      else {
        checkid = checkid + "," + check[i];
      }
    }
    getSession().delete("from TwflProcess as process where processid in (" + checkid +
                        ")");
    return true;
  }
  public int getProcessid() {
    return processid;
  }
  public void setProcessid(int processid) {
    this.processid = processid;
  }
  public String[] getCheck() {
    return check;
  }
  public void setCheck(String[] check) {
    this.check = check;
  }
  public java.util.List getProcesslist() {
    return processlist;
  }
  public void setProcesslist(java.util.List processlist) {
    this.processlist = processlist;
  }
}
