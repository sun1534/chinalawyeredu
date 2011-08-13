package com.changpeng.address.action;


import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.address.model.*;




/**
 *
 * <p>功能： 删除客户通讯录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-09</p>
 * @版本： V1.0
 * @修改：
 */

public class AddressDeleteAction extends AbstractAction {
	private long addressid;
	private TusrAddress address;
	private boolean result; //删除结果
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public long getAddressid() {
		return addressid;
	}
	public void setAddressid(long addressid) {
		this.addressid = addressid;
	}
	public AddressDeleteAction() {
          // rights="usr1,8";
	}
	public String go() throws HibernateException {
	    TusrAddress address = (TusrAddress)getSession().get(TusrAddress.class, addressid);
	    getSession().delete(address);
	    result=true;
         //       message="删除成功！";
		return SUCCESS;
	}

}
