package com.changpeng.customer.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.customer.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看客户管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-08-28</p>
 * @版本： V1.0
 * @修改：
 */

public class CustomerViewAction extends AbstractAction {
	private TusrCustomer customer;
        private long customerid;
	public CustomerViewAction() {
          rights="usr1,1";
	   customer = new TusrCustomer();
	}

	public String go() throws HibernateException {
           nextpage="customerList.action?pagenumber="+pagenumber;
           customer=(TusrCustomer)getSession().get(TusrCustomer.class,customerid);
           if(customer==null){
             message="未找到此客户管理";
             return "message";
           }
           set("customer", customer);
           return SUCCESS;
	}
	public TusrCustomer getCustomer() {
		return customer;
	}
        public void setCustomerid(long customerid) {

          this.customerid = customerid;
        }
        public long getCustomerid() {
          return this.customerid;
        }

}
