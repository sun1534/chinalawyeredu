package com.changpeng.customer.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.customer.model.*;




/**
 *
 * <p>功能： 删除客户管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-08-28</p>
 * @版本： V1.0
 * @修改：
 */

public class Customer2DeleteAction extends AbstractAction {

	private TusrCustomer customer;

	public Customer2DeleteAction() {
           rights="usr1,8";
           nextpage="customer2List.action?pagenumber="+pagenumber;
	}
	public String go() throws HibernateException {
                TusrCustomer customer = (TusrCustomer) get("customer");
                getSession().delete(customer);
                message="删除成功！";
		return SUCCESS;
	}

	public TusrCustomer getCustomer() {
         if (customer==null)
            customer = (TusrCustomer) get("customer");
          return customer;
	}
}
