package com.changpeng.address.action;

import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.address.model.*;
import com.changpeng.customer.util.NewCustomerUtil;
import com.changpeng.operation.util.CreditcardUpdateBatch;

/**
 * 
 * <p>
 * 功能： 创建客户通讯录
 * </p>
 * <p>
 * 作者： 刘兴华
 * </p>
 * <p>
 * 公司： 长鹏软件
 * </p>
 * <p>
 * 日期： 2009-07-09
 * </p>
 * 
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
		String md5val = com.sxit.common.util.md5.MD5((address.getUsername()==null?"":address.getUsername()) + (address.getPhone()==null?"":address.getPhone()) + (address.getHomeaddr()==null?"":address.getHomeaddr())
				 + address.getCustomerid());
		address.setMd5val(md5val);

		address.setCreatetime(new java.util.Date());
		if (CreditcardUpdateBatch.getUsrAddress(getSession(), md5val) == 0){
			getSession().save(address);
			message = "保存成功！";
		}
		else{
			message="联系人信息已经存在，请不要重复新增";
		}
		set("address", address);
		
		nextpage = "addressList.action";
		
		return SUCCESS;
	}

	public TusrAddress getAddress() {
		return address;
	}

	public String input() throws Exception {
		return "input";
	}
}
