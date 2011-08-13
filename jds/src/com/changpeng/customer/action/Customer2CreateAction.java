package com.changpeng.customer.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.customer.model.*;


/**
 *
 * <p>功能： 创建客户管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-08-28</p>
 * @版本： V1.0
 * @修改：
 */

public class Customer2CreateAction extends AbstractAction {

	private TusrCustomer customer;


	public Customer2CreateAction() {
           rights="usr1,2";
		customer = new TusrCustomer();
	}

	public String go() throws HibernateException {
		customer.setCreatetime(new java.util.Date());
		customer.setCreateuser(curuser.getUserid());
		getSession().save(customer);
		
		set("customer", customer);
                nextpage="customer2List.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TusrCustomer getCustomer() {
		return customer;
	}
        public String input() throws Exception {
            return "input";
    }
}
