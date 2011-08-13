package com.changpeng.address.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.address.model.*;
import com.changpeng.customer.util.NewCustomerUtil;


/**
 *
 * <p>功能： 创建客户通讯录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-09</p>
 * @版本： V1.0
 * @修改：
 */

public class AddressCreateAction extends AbstractAction {

	private TusrAddress address;
	private long oprid;
	private int oprflag;
	

	public long getOprid() {
		return oprid;
	}

	public int getOprflag() {
		return oprflag;
	}

	public void setOprid(long oprid) {
		this.oprid = oprid;
	}

	public void setOprflag(int oprflag) {
		this.oprflag = oprflag;
	}

	public AddressCreateAction() {
          // rights="usr1,2";
		address = new TusrAddress();
	}

	public String go() throws HibernateException {
		address.setTsysUser(curuser);
		address.setOprid(oprid);
		address.setOprflag(oprflag);
		
		int customerid = NewCustomerUtil.getCustomerByService(getSession(), (int) oprid, oprflag);
		address.setCustomerid(customerid);
		
		address.setCreatetime(new java.util.Date());
		getSession().save(address);
		set("address", address);
                nextpage="addressList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TusrAddress getAddress() {
		return address;
	}
        public String input() throws Exception {
            return "input";
    }
}
