package com.sxit.wait.action;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.sxit.common.action.AbstractAction;
import com.sxit.wait.model.TwatWait;

/**
 *
 * <p>功能： 删除急件</p>
 * <p>作者： zrb</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2005-04-29</p>
 * @版本： V1.0
 * @修改：
 */

public class DeleteWaitAction  extends AbstractAction {
  private TwatWait wait;
  private int waitid;
  private java.util.List waitlist;
  public DeleteWaitAction() {
    rights="wat1,4";
    wait=new TwatWait();
    nextpage="listWait1.action";
  }
  protected String go() throws org.hibernate.HibernateException {
    waitlist = getQuery()
        .setInteger("waitid",waitid)
        .list();
   try{
     if(waitlist==null&&waitlist.size()!=1){
       message="未找到该记录！";
       return "message";
     }else{
       wait=(TwatWait)waitlist.get(0);
        getSession().delete(wait);
        getSession().flush();
        message = "删除急件成功！";
        return "message";
     }
    }catch(HibernateException e)
    {
     e.printStackTrace();
     message = "删除急件失败！";
        return "message";
    }
   }
   public TwatWait getWait() {
           return wait;
   }
  public int getWaitid() {
    return waitid;
  }
  public void setWaitid(int waitid) {
    this.waitid = waitid;
  }
  public java.util.List getWaitlist() {
    return waitlist;
  }
  public void setWaitlist(java.util.List waitlist) {
    this.waitlist = waitlist;
  }
  private Query getQuery() throws HibernateException {
    Query query = getSession().createQuery("from TwatWait as wait where waitid=:waitid");
    return query;
  }
}

