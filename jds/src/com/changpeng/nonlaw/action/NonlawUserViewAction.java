package com.changpeng.nonlaw.action;

import java.util.List;

import org.hibernate.HibernateException;

import com.changpeng.customer.model.TusrCustomerNew;
import com.changpeng.customer.util.NewCustomerUtil;
import com.changpeng.nonlaw.model.TnlwNonlaw;
import com.changpeng.nonlaw.model.TnlwNonlawtask;
import com.changpeng.nonlaw.util.NonlawCreateBatch;
import com.sxit.common.action.AbstractAction;


/**
 *
 * <p>功能： 查看催收记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司：长鹏软件</p>
 * <p>日期： 2009-06-09</p>
 * @版本： V1.0
 * @修改：
 */

public class NonlawUserViewAction extends AbstractAction {
	private TnlwNonlaw nonlaw;
    private long nonlawid;
    private List addressList;	
    private long nonlawtaskid;
    private List logList;
    private TnlwNonlawtask nonlawtask;
    
    public TnlwNonlawtask getNonlawtask() {
		return nonlawtask;
	}
	public List getLogList() {
		return logList;
	}
	public void setLogList(List logList) {
		this.logList = logList;
	}
	public long getNonlawtaskid() {
		return nonlawtaskid;
	}
	public void setNonlawtaskid(long nonlawtaskid) {
		this.nonlawtaskid = nonlawtaskid;
	}
	public List getAddressList() {
		return addressList;
	}
	public void setAddressList(List addressList) {
		this.addressList = addressList;
	}
	public void setNonlawid(long nonlawid) {
		this.nonlawid = nonlawid;
	}
    public TnlwNonlaw getNonlaw() {
		return nonlaw;
	}
	public NonlawUserViewAction() {
          rights="nlw3,1";
         // creditcard = new ToprCreditcard();
	}

	public String go() throws HibernateException {

		nonlaw=(TnlwNonlaw)getSession().get(TnlwNonlaw.class,nonlawid);
		nonlawtask=(TnlwNonlawtask)getSession().get(TnlwNonlawtask.class, nonlawtaskid);
		logList=getSession().createQuery("from TnlwNonlawlog where tnlwNonlaw.nonlawid="+nonlawid+" order by logtime desc").list();
		//获取关联该业务的所有联系人信息
		int customerid = NewCustomerUtil.getCustomerByService(getSession(), (int) nonlawid, 2);

		if(customerid==0){
			TusrCustomerNew customer=null;
			 customerid = NonlawCreateBatch.addCustomerAndAddress(getSession(), this.curuser, customer, nonlaw);
			 LOG.debug("重新新增这个人的联系号码情况:"+customerid);
		}
		if(customerid!=0)
			addressList = getSession().createQuery(" from TusrAddress where customerid=" + customerid).list();

//		addressList=getSession().createQuery(" from TusrAddress where tsysUser.userid="+curuser.getUserid()+" and oprid="+nonlawid+" and oprflag=2").list();
	       if(nonlaw==null){
	         message="未找到此催收记录";
	         return "message";
	       }
           set("nonlaw", nonlaw);
           return SUCCESS;
	}

}
