package com.changpeng.address.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.address.model.*;
import com.changpeng.operation.util.CreditcardUpdateBatch;

/**
 * 
 * <p>
 * 功能： 编辑客户通讯录
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

//		String md5val = com.sxit.common.util.md5.MD5(address.getUsername() + address.getPhone() + address.getHomeaddr()
//				 + address.getCustomerid());
		String md5val = com.sxit.common.util.md5.MD5((address.getUsername()==null?"":address.getUsername()) + (address.getPhone()==null?"":address.getPhone()) + (address.getHomeaddr()==null?"":address.getHomeaddr())
				 + address.getCustomerid());
		address.setMd5val(md5val);

		if (CreditcardUpdateBatch.getUsrAddress(getSession(), md5val) == 0){
			getSession().update(address);
			message = "保存成功！";
		}
		else{//修改为和某个数据一样了，也就是重复了，那我就删掉你
			getSession().delete(address);
			message="您修改后的信息和已有的联系人信息有重复，删除";
		}
		return SUCCESS;
	}

	public TusrAddress getAddress() {
		if (address == null)
			address = (TusrAddress) get("address");
		return address;
	}

	public String input() throws Exception {
		address = (TusrAddress) getSession().get(TusrAddress.class, addressid);
		set("address", address);
		return "input";
	}

}
