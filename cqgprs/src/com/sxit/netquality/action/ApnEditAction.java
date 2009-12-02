/**
 * 
 */
package com.sxit.netquality.action;

import java.util.Date;

import com.sxit.common.action.AbstractListAction;
import com.sxit.netquality.models.Apn;
import com.sxit.netquality.service.BasicSetService;

/**
 * 
 * 修改apn的联系电话等
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class ApnEditAction extends AbstractListAction {

	private String apnni;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		BasicSetService setservice = (BasicSetService) this.getBean("basicSetService");
		setservice.getAllSets();
		setservice.updateApn(apnni, apn.getUsercorp(), apn.getUserphone());

		this.message = "此APN的配置参数信息修改成功";
	
		
		if(from==null||from.equals(""))
		
		    this.nextPage = "../netquality/apnList.action?pageNo=" + pageNo;
		else
			this.nextPage = "../tradeCustomer/tradeCustomerAll.action.action?pageNo=" + pageNo;
			
		return SUCCESS;

	}
	private String from;

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	public String input() throws Exception {

		BasicSetService setservice = (BasicSetService) this.getBean("basicSetService");
		setservice.getAllSets();
		apn=BasicSetService.ALL_APNS.get(apnni);
		set("apn",apn);
		return INPUT;
	}

	private Apn apn;

	public Apn getApn() {
		if (apn == null)
			apn = (Apn) get("apn");
		return apn;
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

}
