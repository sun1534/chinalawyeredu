package com.changpeng.customer.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;


/**
 *
 * <p>功能： 列表客户管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-08-28</p>
 * @版本： V1.0
 * @修改：
 */

public class CustomerListAction extends AbstractListAction  {
        private List customerlist;
        private int customerflag;
        private String username;
        private String company;
        public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		public int getCustomerflag() {
			return customerflag;
		}
		public String getUsername() {
			return username;
		}
		public void setCustomerflag(int customerflag) {
			this.customerflag = customerflag;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public CustomerListAction() {
          rights="usr1,1";
        }
        public String go() throws HibernateException {
                customerlist = getQuery()
                          .setMaxResults(maxperpage)
                        .setFirstResult(maxperpage * pagenumber)
                        .setCacheable(true)
                        .list();
                return SUCCESS;
        }
        private Query getQuery() throws HibernateException {
                String queryName ;
               // queryName="from TusrCustomer as customer where customertype=1 and createuser="+curuser.getUserid()+"";
                queryName="from TusrCustomer as customer where customertype=1";
                if(username!=null&&!"".equals(username))
                	queryName+=" and username like '%"+username+"%'";
                if(company!=null&&!"".equals(company))
                	queryName+=" and company like '%"+company+"%'";
                if(customerflag!=0)
                	queryName+=" and customerflag="+customerflag;
                queryName+=" order by customer.customerid desc";
                
                Query query = getSession().createQuery(queryName);
                recordsize = query.list().size();
                pagesize = (recordsize - 1) / maxperpage + 1;
                pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
                return query;
        }
        public List getCustomerlist() {
          return customerlist;
        }
}
