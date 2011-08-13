package com.sxit.info.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;

/**
 *
 * <p>功能： 列表信息发布</p>
 * <p>作者： 雷华</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-12-07</p>
 * @版本： V1.0
 * @修改：
 */

public class WebViewListAction
    extends AbstractListAction {

    private List newslist;
    private List bulletinlist;
    private List bylawlist;
    private List waitlist;
    public WebViewListAction() {
      rights = "inf,0";
    }

    public String go() throws HibernateException {
        newslist = getQueryNews()
            .setMaxResults(3)
            .setFirstResult(0)
            .setCacheable(true)
            .list();
        bulletinlist = getQueryBulletin()
            .setMaxResults(3)
            .setFirstResult(0)
            .setCacheable(true)
            .list();
        bylawlist = getQueryBylaw()
            .setMaxResults(3)
            .setFirstResult(0)
            .setCacheable(true)
            .list();
        waitlist = getQueryBywait()
            .setLong("userid",curuser.getUserid())
            .setMaxResults(3)
            .setFirstResult(0)
            .setCacheable(true)
            .list();
        return SUCCESS;
    }

    private Query getQueryNews() throws HibernateException {
        String queryName;
        queryName =
            "from TinfInfo as info where info.statusid=0 and info.tinfType.typeid=1 order by info.infoid desc";
        Query query = getSession().createQuery(queryName);
        return query;
    }

    private Query getQueryBylaw() throws HibernateException {
        String queryName;
        queryName =
            "from TinfInfo as info where info.statusid=0 and info.tinfType.typeid=3 order by info.infoid desc";
        Query query = getSession().createQuery(queryName);
        return query;
    }
    
    private Query getQueryBulletin() throws HibernateException {
        String queryName;
        queryName =
            "from TinfInfo as info where info.statusid=0 and info.tinfType.typeid=2 order by info.infoid desc";
        Query query = getSession().createQuery(queryName);
        return query;
    }


    private Query getQueryBywait() throws org.hibernate.HibernateException {
      Query query = getSession().createQuery("from TwatWait where status=0 and tsysUser.userid=:userid order by waitid desc");
      return query;
    }
    public List getWaitlist() {
      return waitlist;
  }
    public List getNewslist() {
        return newslist;
    }

    public List getBulletinlist() {
        return bulletinlist;
    }

    public List getBylawlist() {
        return bylawlist;
    }
}
