package com.changpeng.customer.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.customer.model.*;




/**
 *
 * <p>功能： 编辑客户管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-08-28</p>
 * @版本： V1.0
 * @修改：
 */

public class CustomerEditAction extends AbstractAction {

	private TusrCustomer customer;

	public CustomerEditAction() {
          rights="usr1,4";
	}

	public String go() throws HibernateException {
                getSession().update(customer);
		set("customer", customer);
                nextpage="customerList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TusrCustomer getCustomer() {
         if (customer==null)
            customer = (TusrCustomer) get("customer");
          return customer;
	}

        public String input() throws Exception {
          return "input";
        }


}
