//$Id: ListPositionsAction.java,v 1.5 2003/12/13 13:37:49 gavin Exp $
package com.sxit.system.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;

/**
 *
 * @author zrb
 */
public class PositionListAction extends AbstractListAction  {
        private List positionlist;
        public PositionListAction() {
          rights="sys4,1";
        }
        public String go() throws HibernateException {
                positionlist = getQuery()
                          .setMaxResults(maxperpage)
                        .setFirstResult(maxperpage * pagenumber)
                        .setCacheable(true)
                        .list();
                return SUCCESS;
        }
        /**
         * SQL query for Oracle.
         * queryName = "PositionList.HQL";
         */
        private Query getQuery() throws HibernateException {
                String queryName ;
                queryName="from TsysPosition as position order by position.positionid desc";
                Query query = getSession().createQuery(queryName);
                recordsize = query.list().size();
                pagesize = (recordsize - 1) / maxperpage + 1;
                pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
                return query;
        }
        public List getPositionlist() {
          return positionlist;
        }
}
