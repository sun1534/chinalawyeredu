/**
 * 
 */
package com.sxit.useraction.action;

import java.text.DateFormat;
import java.util.List;

import com.sxit.common.action.AbstractListAction;
import com.sxit.useraction.service.UseractionService;

/**
 * 
 * 错误apn分布,从stat_apn_error表里拿数据
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class ErrorApnsction extends AbstractListAction {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private String date;


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
	
	private String reqapnni;
	private String errcode;



	/**
	 * @return the reqapnni
	 */
	public String getReqapnni() {
		return reqapnni;
	}

	/**
	 * @param reqapnni the reqapnni to set
	 */
	public void setReqapnni(String reqapnni) {
		this.reqapnni = reqapnni;
	}

	/**
	 * @return the errcode
	 */
	public String getErrcode() {
		return errcode;
	}

	/**
	 * @param errcode the errcode to set
	 */
	public void setErrcode(String errcode) {
		this.errcode = errcode;
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
		UseractionService service = (UseractionService) this.getBean("useractionService");
		if(orderfield==null||orderfield.equals(""))
			orderfield="reqapnni";
		
		if (resultType.equals("list")){
			this.page=service.getApnErrors(date, null, null,reqapnni,errcode,getOrderby(),pageNo,pageSize);
			apnerrorslist=page.getItems();
			return SUCCESS;
		}
		else{
			apnerrorslist = service.getApnErrors(date, null, null,reqapnni,errcode,getOrderby());
			return "excel";
		}
	}

	
	
	private List apnerrorslist;

	public List getApnerrorslist() {
		return this.apnerrorslist;
	}
}
