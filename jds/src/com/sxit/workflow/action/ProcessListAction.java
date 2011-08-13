package com.sxit.workflow.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;


/**
 *
 * <p>功能： 列表流程</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-15</p>
 * @版本： V1.0
 * @修改：
 */

public class ProcessListAction extends AbstractListAction  {
        private List processlist;
        public ProcessListAction() {
          rights="wfl1,1";
        }
        public String go() throws HibernateException {
                processlist = getQuery()
                          .setMaxResults(maxperpage)
                        .setFirstResult(maxperpage * pagenumber)
                        .setCacheable(true)
                        .list();
                return SUCCESS;
        }
        /**
         * SQL query for Oracle.
         * queryName = "ProcessList.HQL";
         */
        private Query getQuery() throws HibernateException {
                String queryName ;
                queryName="from TwflProcess as process order by process.processid desc";
                Query query = getSession().createQuery(queryName);
                recordsize = query.list().size();
                pagesize = (recordsize - 1) / maxperpage + 1;
                pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
                return query;
        }
        public List getProcesslist() {
          return processlist;
        }
}
