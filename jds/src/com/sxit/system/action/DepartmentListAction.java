//$Id: ListRolesAction.java,v 1.5 2003/12/13 13:37:49 gavin Exp $
package com.sxit.system.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;
import com.sxit.system.model.TsysDepartment;

/**
 *
 * @author zrb
 */
public class DepartmentListAction extends AbstractListAction  {
        private List departmentlist;
        private TsysDepartment treeRootNode;
        public DepartmentListAction() {
          rights="sys1,1";
        }
        public String go() throws HibernateException {
                departmentlist = getQuery()
                          .setMaxResults(maxperpage)
                        .setFirstResult(maxperpage * pagenumber)
                        .setCacheable(true)
                        .list();
                    System.out.println(departmentlist.size());
                return SUCCESS;
        }
        /**
         * SQL query for Oracle.
         * queryName = "DepartmentList.HQL";
         */
        private Query getQuery() throws HibernateException {
                String queryName ;
                queryName="from TsysDepartment as department where department!=-1 order by department.departmentid desc";
                Query query = getSession().createQuery(queryName);
                recordsize = query.list().size();
                pagesize = (recordsize - 1) / maxperpage + 1;
                pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
                return query;
        }
        public List getDepartmentlist() {
          return departmentlist;
        }
}
