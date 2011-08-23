package com.changpeng.help.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;

/**
 * 我的站内消息
 * @author sinhoo
 * Sep 7, 2009
 */

public class MsgListAction extends AbstractListAction  {
        private List msglist;
        public MsgListAction() {
          rights="hlp4,1";
        }
        public String go() throws HibernateException {
        	msglist = getQuery()
                          .setMaxResults(maxperpage)
                        .setFirstResult(maxperpage * pagenumber)
                        .setCacheable(true)
                        .list();
                return SUCCESS;
        }
        private Query getQuery() throws HibernateException {
                String queryName ;
                queryName="from ThlpMsg  where touser="+curuser.getUserid()+" order by msgid desc";
                Query query = getSession().createQuery(queryName);

                basicDao.setSession(getSession());
                recordsize=basicDao.getCountOfQuery(queryName);
//                recordsize = query.list().size();
                pagesize = (recordsize - 1) / maxperpage + 1;
                pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
                return query;
        }
		public List getMsglist() {
			return msglist;
		}
        
}
