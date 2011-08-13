package com.changpeng.address.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.address.model.*;




/**
 *
 * <p>功能： 编辑客户通讯录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-09</p>
 * @版本： V1.0
 * @修改：
 */

public class AddressEditAction extends AbstractAction {

	private TusrAddress address;
	private long addressid;

	public long getAddressid() {
		return addressid;
	}

	public void setAddressid(long addressid) {
		this.addressid = addressid;
	}

	
	public AddressEditAction() {
         // rights="usr1,4";
	}

	public String go() throws HibernateException {
         getSession().update(address);
		 message="保存成功！";
		return SUCCESS;
	}

	public TusrAddress getAddress() {
         if (address==null)
            address = (TusrAddress) get("address");
          return address;
	}

        public String input() throws Exception {
          address=(TusrAddress)getSession().get(TusrAddress.class, addressid);
          set("address",address);
          return "input";
        }


}
