//$Id: ListRolesAction.java,v 1.5 2003/12/13 13:37:49 gavin Exp $
package com.sxit.system.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;

/**
 *
 * @author zrb
 */
public class GroupListAction extends AbstractListAction  {
        private List grouplist;
        public GroupListAction() {
          rights="sys3,1";
        }
        public String go() throws HibernateException {
                grouplist = getQuery()
                          .setMaxResults(maxperpage)
                        .setFirstResult(maxperpage * pagenumber)
                        .setCacheable(true)
                        .list();
                return SUCCESS;
        }
        /**
         * SQL query for Oracle.
         * queryName = "GroupList.HQL";
         */
        private Query getQuery() throws HibernateException {
                String queryName ;
                queryName="from TsysGroup as group order by group.groupid desc";
                Query query = getSession().createQuery(queryName);
                recordsize = query.list().size();
                pagesize = (recordsize - 1) / maxperpage + 1;
                pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
                return query;
        }
        public List getGrouplist() {
          return grouplist;
        }
}
