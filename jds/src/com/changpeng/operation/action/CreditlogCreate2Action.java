package com.changpeng.operation.action;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import com.changpeng.address.model.TusrAddress;
import com.changpeng.operation.model.ToprCreditlog;
import com.changpeng.operation.model.ToprCredittask;
import com.sxit.common.action.AbstractAction;

/**
 * 
 * <p>
 * 功能： 创建催收日志
 * </p>
 * <p>
 * 作者： 刘兴华
 * </p>
 * <p>
 * 公司： 长鹏软件
 * </p>
 * <p>
 * 日期： 2009-06-14
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */

public class CreditlogCreate2Action extends AbstractAction {

	private ToprCreditlog creditlog;
	private TusrAddress address;
	private long addressid;
	private long credittaskid;
	private List tasklist;
	private int bankid;

	/**
	 * @return the bankid
	 */
	public int getBankid() {
		return bankid;
	}

	/**
	 * @param bankid the bankid to set
	 */
	public void setBankid(int bankid) {
		this.bankid = bankid;
	}

	public long getCredittaskid() {
		return credittaskid;
	}

	public void setCredittaskid(long credittaskid) {
		this.credittaskid = credittaskid;
	}

	public CreditlogCreate2Action() {
		rights = "opr4,2";
		creditlog = new ToprCreditlog();
	}

	public String go() throws HibernateException {
		creditlog.setUserid(curuser.getUserid());
		creditlog.setUsername(curuser.getUsername());
		creditlog.setCreatetime(new java.util.Date());
		long credittaskid = creditlog.getToprCredittask().getCredittaskid();
		ToprCredittask toprCredittask = (ToprCredittask) getSession().get(ToprCredittask.class, credittaskid);

		creditlog.setToprCreditcard(toprCredittask.getToprCreditcard());
		getSession().save(creditlog);
		set("creditlog", creditlog);
		nextpage = "creditlogList.action";
		message = "保存成功！";
		return SUCCESS;
	}

	public ToprCreditlog getCreditlog() {
		return creditlog;
	}

	public String input() throws Exception {

		this.address = (TusrAddress) getSession().get(TusrAddress.class, addressid);

		creditlog.setLogtime(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		// 联络类型 联络方式 联络对象 联络结果 催收记录 催收措施
		// 其他地址 广东省深圳市专家公寓B栋东401 持卡人 无法确认 申请寄信:广东省深圳市专家公寓B栋东401 信函催收

		if (address != null) {
			creditlog.setCuishoucuoshi("电话催收");
			creditlog.setContactobj(address.getUsername());
			creditlog.setContacttype(address.getComments());
			creditlog.setContactmanner(address.getHomeaddr() + "(" + address.getPhone() + ")");
			creditlog.setContactresult("");
		}

		return "input";
	}

	public List getTasklist() {
		return tasklist;
	}

	/**
	 * @return the addressid
	 */
	public long getAddressid() {
		return addressid;
	}

	/**
	 * @param addressid
	 *            the addressid to set
	 */
	public void setAddressid(long addressid) {
		this.addressid = addressid;
	}

	/**
	 * @return the address
	 */
	public TusrAddress getAddress() {
		return address;
	}
}
