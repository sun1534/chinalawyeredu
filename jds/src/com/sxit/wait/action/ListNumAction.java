package com.sxit.wait.action;

import java.util.List;

import org.hibernate.Query;

import com.sxit.common.action.AbstractAction;
import com.sxit.common.action.AbstractListAction;
import com.sxit.wait.model.TwatWait;
import com.opensymphony.xwork2.ActionSupport;
import com.sxit.system.model.TsysUser;
import com.opensymphony.xwork2.ActionContext;
import com.sxit.common.component.HibernateSession;
import org.hibernate.Session;
import org.hibernate.HibernateException;

/**
 *
 * <p>功能： 显示待办件条数</p>
 * <p>作者： zrb</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2005-04-29</p>
 * @版本： V1.0
 * @修改：
 */

public class ListNumAction
    extends ActionSupport {
          protected Long recordsize;
          private HibernateSession session;
  public ListNumAction() {
  }
  public String execute() throws org.hibernate.HibernateException {
    TsysUser  curuser = (TsysUser) get("curuser");
    if(curuser==null)
    {
      recordsize=0l;
      return SUCCESS;
    }
    recordsize =(Long) getQuery().setLong("userid",curuser.getUserid()).iterate().next();
    session.disposeSession();
    return SUCCESS;
  }
  private Query getQuery() throws org.hibernate.HibernateException {
    Query query = getSession().createQuery("select count(*) from TwatWait where status=0 and userid=:userid");
    return query;
  }
  protected Session getSession() throws HibernateException {
    return session.getSession();
  }
  protected Object get(String name) {
    return ActionContext.getContext().getSession().get(name);
  }
  public void setHibernateSession(HibernateSession session) {
    this.session = session;
  }
  public Long getRecordsize() {
    return recordsize;
  }
  public void set_dc(String _dc) {
  }
}

