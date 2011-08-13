package com.sxit.workflow.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;


/**
 *
 * <p>功能： 列表转向</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-16</p>
 * @版本： V1.0
 * @修改：
 */

public class DirectionListAction extends AbstractListAction  {
        private List directionlist;
        public DirectionListAction() {
          rights="wfl1,1";
        }
        public String go() throws HibernateException {
                directionlist = getQuery()
                          .setMaxResults(maxperpage)
                        .setFirstResult(maxperpage * pagenumber)
                        .setCacheable(true)
                        .list();
                return SUCCESS;
        }
        /**
         * SQL query for Oracle.
         * queryName = "DirectionList.HQL";
         */
        private Query getQuery() throws HibernateException {
                String queryName ;
                queryName="from TwflDirection as direction order by direction.directionid desc";
                Query query = getSession().createQuery(queryName);
                recordsize = query.list().size();
                pagesize = (recordsize - 1) / maxperpage + 1;
                pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
                return query;
        }
        public List getDirectionlist() {
          return directionlist;
        }
}
