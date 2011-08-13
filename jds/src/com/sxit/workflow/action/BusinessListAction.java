package com.sxit.workflow.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;


/**
 *
 * <p>功能： 列表业务</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-11-13</p>
 * @版本： V1.0
 * @修改：
 */

public class BusinessListAction extends AbstractListAction  {
        private List businesslist;
        public BusinessListAction() {
          rights="wfl2,1";
        }
        public String go() throws HibernateException {
                businesslist = getQuery()
                          .setMaxResults(maxperpage)
                        .setFirstResult(maxperpage * pagenumber)
                        .setCacheable(true)
                        .list();
                return SUCCESS;
        }
        private Query getQuery() throws HibernateException {
                String queryName ;
                queryName="from TwflBusiness as business order by business.businessid";
                Query query = getSession().createQuery(queryName);
                recordsize = query.list().size();
                pagesize = (recordsize - 1) / maxperpage + 1;
                pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
                return query;
        }
        public List getBusinesslist() {
          return businesslist;
        }
}
