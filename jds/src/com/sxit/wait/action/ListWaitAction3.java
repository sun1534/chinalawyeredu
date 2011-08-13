package com.sxit.wait.action;

import java.util.List;

import org.hibernate.Query;

import com.sxit.common.action.AbstractAction;
import com.sxit.common.action.AbstractListAction;
import com.sxit.wait.model.TwatWait;

/**
 *
 * <p>功能： 显示急件列表</p>
 * <p>作者： zrb</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2005-04-29</p>
 * @版本： V1.0
 * @修改：
 */

public class ListWaitAction3
    extends AbstractListAction {
  private List waitlist;
  public ListWaitAction3() {
	  this.maxperpage=Integer.MAX_VALUE;
    rights="wat1,1";
  }
  public String go() throws org.hibernate.HibernateException {
     waitlist = getQuery()
         .setLong("userid",curuser.getUserid())
         .setMaxResults(maxperpage)
         .setFirstResult(maxperpage * pagenumber)
         .setCacheable(true)
         .list();
     return SUCCESS;
   }
   private Query getQuery() throws org.hibernate.HibernateException {
	  
	   Query query = getSession().createQuery("from TwatWait where status=0 and docstatus=3 and userid=:userid order by waitid desc");
     recordsize = query.setLong("userid",curuser.getUserid()).list().size();
     pagesize = (recordsize - 1) / maxperpage + 1;
     return query;
   }
   public List getWaitlist() {
     return waitlist;
   }
 }
