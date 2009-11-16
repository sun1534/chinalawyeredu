/**
 * 
 */
package com.sxit.tradeCustomer.action;

import java.text.DateFormat;
import java.util.Date;

import com.sxit.common.action.AbstractListAction;
import com.sxit.useraction.service.UseractionService;

/**
 * 
 * 异常行业用户 （实际上把错误码为33的，APN为*.cq的异常行业用户号码单列）
 * 
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class ExceptionUsersAction extends AbstractListAction {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private String date;
	private String apnni;
	private String resultType = "list";

	/**
	 * @return the resultType
	 */
	public String getResultType() {
		return resultType;
	}

	/**
	 * @param resultType
	 *            the resultType to set
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the apnni
	 */
	public String getApnni() {
		return apnni;
	}

	/**
	 * @param apnni
	 *            the apnni to set
	 */
	public void setApnni(String apnni) {
		this.apnni = apnni;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		if (date == null || date.equals("")) {
			date = df.format(com.sxit.stat.util.StatUtil.getPrevDate());
		}
		Date thedate = null;
		try {
			thedate = df.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
			thedate = com.sxit.stat.util.StatUtil.getPrevDate();
		}
		UseractionService actionservice = (UseractionService) this.getBean("useractionService");

		if (resultType.equals("excel")) {
			pageNo = 1;
			pageSize = Integer.MAX_VALUE;
		}
		this.page = actionservice.getExceptionUsers(apnni, thedate, pageNo, pageSize);

		if (resultType.equals("list"))
			return SUCCESS;
		return "excel";
	}

}
