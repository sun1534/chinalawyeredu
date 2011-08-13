package com.changpeng.nonlaw.action;

import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.customer.model.TusrCustomerNew;
import com.changpeng.customer.model.TusrCustomerService;
import com.changpeng.customer.util.NewCustomerUtil;
import com.changpeng.nonlaw.model.*;

/**
 * 
 * <p>
 * 功能： 创建催收记录
 * </p>
 * <p>
 * 作者： 刘兴华
 * </p>
 * <p>
 * 公司： 长鹏软件
 * </p>
 * <p>
 * 日期： 2009-06-18
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */

public class NonlawCreateAction extends AbstractAction {

	private TnlwNonlaw nonlaw;

	public NonlawCreateAction() {
		rights = "nlw1,2";
		nonlaw = new TnlwNonlaw();
	}

	public String go() throws HibernateException {
		nonlaw.setCreatetime(new java.util.Date());
		nonlaw.setState(0);

		// 当前逾期本金及利息等
		nonlaw.setCuraccrualfee(nonlaw.getAccrualfee());
		nonlaw.setCurbalancefee(nonlaw.getBalancefee());
		nonlaw.setCuroverfee(nonlaw.getOverfee());
		// nonlaw.setCuroverstat(nonlaw.getOverstat());
		try {
			nonlaw.setCuroverstat(Float.parseFloat(nonlaw.getOverstat()));
		} catch (Exception e) {
			nonlaw.setCuroverstat(0f);
		}

		nonlaw.setTdflag(0);

		getSession().save(nonlaw);

		int nonlawid = (int) nonlaw.getNonlawid();

		TusrCustomerNew customer = NewCustomerUtil.getCustomer(getSession(), nonlaw.getUsername(), nonlaw.getIdcard());
		int customerid = 0;
		boolean exist = false;
		if (customer != null) {
			customerid = customer.getCustomerid();
			exist = true;
		} else {// 新增一个用户,同时将这个要加到那个service里面去
			customer = new TusrCustomerNew();
			// insert into tusr_customer_new(customerid,
			// username,idcard,homeaddr,homephone,mobile1,company,compaddr,compphone,mobile2,createsrc,createsrcid,createtime)
			// select tusrcustomerid.nextval,
			// username,idcard,HOMEADDR,HOMEPHONEOLD,MOBILEOLD,company,COMPANYADDR,COMPANYPHONE,ALTERPHONE,2
			// as createsrc,nonlawid,createtime from tnlw_nonlaw;
			customer.setUsername(nonlaw.getUsername());
			customer.setIdcard(nonlaw.getIdcard());
			customer.setMobile1(nonlaw.getMobileold());
			customer.setHomeaddr(nonlaw.getHomeaddr());
			customer.setHomephone(nonlaw.getHomephoneold());
			customer.setCompphone(nonlaw.getCompanyphone());
			customer.setCompany(nonlaw.getCompany());
			customer.setCompaddr(nonlaw.getCompanyaddr());
			customer.setMobile2(nonlaw.getAlterphone());
			customer.setCreatesrc(2);
			customer.setCreatesrcid(nonlawid);
			customer.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			customer.setCreateuser((int) curuser.getUserid());
			customer.setCustomerflag(2);// 1:VIP 2:一般
			customer.setCustomertype(3);// 1:机构客户 2:个人客户 3当事人客户

			getSession().save(customer);
			customerid = customer.getCustomerid();
		}

		TusrCustomerService service = new TusrCustomerService();
		service.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		service.setCreateuser(curuser.getUsername());
		service.setCreateuserid((int) curuser.getUserid());
		service.setServiceid((int) nonlawid);
		service.setServicetype(2);
		service.setTusrCustomerNew(customer);
		service.setRemarks("");
		getSession().save(service);

		set("nonlaw", nonlaw);
		nextpage = "nonlawList.action";
		message = "保存成功！";
		return SUCCESS;
	}

	public TnlwNonlaw getNonlaw() {
		return nonlaw;
	}

	public String input() throws Exception {
		return "input";
	}
}
