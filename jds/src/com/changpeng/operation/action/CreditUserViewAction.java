package com.changpeng.operation.action;

import java.util.List;

import org.hibernate.HibernateException;

import com.changpeng.customer.model.TusrCustomerNew;
import com.changpeng.customer.util.NewCustomerUtil;
import com.changpeng.operation.model.ToprCreditcard;
import com.changpeng.operation.model.ToprCredittask;
import com.changpeng.operation.model.ToprRepaylog;
import com.changpeng.operation.util.CreditcardCreateBatch;
import com.sxit.common.action.AbstractAction;

/**
 * 
 * <p>
 * 功能： 查看催收记录
 * </p>
 * <p>
 * 作者： 刘兴华
 * </p>
 * <p>
 * 公司：长鹏软件
 * </p>
 * <p>
 * 日期： 2009-06-09
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */

public class CreditUserViewAction extends AbstractAction {
	private ToprCreditcard creditcard;
	private ToprCredittask credittask;
	private long creditcardid;
	private long credittaskid;
	private List addressList;
	private List logList;
	private String repaytime; // 最后还款时间

	public String getRepaytime() {
		return repaytime;
	}

	public List getAddressList() {
		return addressList;
	}

	public void setAddressList(List addressList) {
		this.addressList = addressList;
	}

	public void setCreditcardid(long creditcardid) {
		this.creditcardid = creditcardid;
	}

	public ToprCreditcard getCreditcard() {
		return creditcard;
	}

	public CreditUserViewAction() {
		rights = "opr4,1";
		// creditcard = new ToprCreditcard();
	}

	public ToprCredittask getCredittask() {
		return credittask;
	}

	public String go() throws HibernateException {
		creditcard = (ToprCreditcard) getSession().get(ToprCreditcard.class, creditcardid);
		credittask = (ToprCredittask) getSession().get(ToprCredittask.class, credittaskid);
		// 获取关联该业务的所有联系人信息
		// addressList=getSession().createQuery(" from TusrAddress where
		// tsysUser.userid="+curuser.getUserid()+" and oprid="+creditcardid+"
		// and oprflag=1").list();

		// 这里要先拿到customerid，然后再根据customerid去取
		// addressList=getSession().createQuery(" from TusrAddress where
		// oprid="+creditcardid+" and oprflag=1").list();

		// int customerid=0;

		// List list1=getSession().createSQLQuery("select customerid from
		// tusr_customer_service where servicetype=1 and
		// serviceid="+creditcardid).list();
		// if(list1!=null&&list1.size()>0){
		// customerid=Integer.parseInt(list1.get(0).toString());
		// }

		int customerid = NewCustomerUtil.getCustomerByService(getSession(), (int) creditcardid, 1);
		LOG.debug("显示这个人的所有联系号码的情况:"+customerid);
		
		if(customerid==0)
		{
			TusrCustomerNew customer=null;
			customerid=CreditcardCreateBatch.addCustomerAndAddress(getSession(), this.curuser, customer, creditcard);
			LOG.debug("重新新增这个人的联系号码情况:"+customerid);
		}
		if(customerid!=0)
			addressList = getSession().createQuery(" from TusrAddress where customerid=" + customerid).list();
		
		logList = getSession().createQuery(
				" from ToprCreditlog where toprCredittask.toprCreditcard.creditcardid=" + creditcardid
						+ "  order by logtime asc").list();

		List<ToprRepaylog> list = getSession().createQuery(
				" from ToprRepaylog where toprCreditcard.creditcardid=" + creditcardid + " order by repaytime desc")
				.list();
		if (list != null && list.size() > 0)
			repaytime = list.get(0).getRepaytime();
		if (creditcard == null) {
			message = "未找到此催收记录";
			return "message";
		}
		set("creditcard", creditcard);
		return SUCCESS;
	}

	public long getCredittaskid() {
		return credittaskid;
	}

	public void setCredittaskid(long credittaskid) {
		this.credittaskid = credittaskid;
	}

	public List getLogList() {
		return logList;
	}

}
