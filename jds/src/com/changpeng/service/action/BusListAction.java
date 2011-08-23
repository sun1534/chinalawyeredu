package com.changpeng.service.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;


/**
 *
 * <p>功能： 列表车辆管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-08-28</p>
 * @版本： V1.0
 * @修改：
 */

public class BusListAction extends AbstractListAction  {
        private List buslist;
        public BusListAction() {
          rights="ser3,1";
        }
        public String go() throws HibernateException {
                buslist = getQuery()
                          .setMaxResults(maxperpage)
                        .setFirstResult(maxperpage * pagenumber)
                        .setCacheable(true)
                        .list();
                return SUCCESS;
        }
        private Query getQuery() throws HibernateException {
                String queryName ;
                queryName="from TserBus as bus order by bus.busid desc";
                basicDao.setSession(getSession());
                recordsize=basicDao.getCountOfQuery(queryName);
                Query query = getSession().createQuery(queryName);
//                recordsize = query.list().size();
                pagesize = (recordsize - 1) / maxperpage + 1;
                pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
                return query;
        }
        public List getBuslist() {
          return buslist;
        }
}
