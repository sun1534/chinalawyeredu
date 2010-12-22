/**
 * HwchrQueryAction.java
 */
package com.sxit.netquality.action;

import java.text.DateFormat;
import java.util.Date;

import com.sxit.common.action.AbstractListAction;
import com.sxit.netquality.service.ChrQueryService;

/**
 * @author 华锋
 * Jul 29, 20108:41:24 PM
 *
 */
public class HwchrQueryAction extends AbstractListAction {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private String start; //查询的启示时间
	private String end;//查询的终止时间

	private String date; //查询的哪1天
	private String msisdn;

	private String imei;

	private String flowid;

	private String cellid="0";

	private String lac="0";//lac放前面
	
	
	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		if (this.date == null || this.date.equals("")) {
			this.date = df.format(new java.util.Date()); //默认当天
		}
		this.start = df.format(new java.util.Date());
		Date enddate = this.getPrevCountDate(7);
		this.end = df.format(enddate);
		
		Date _date = df.parse(date);
		
		ChrQueryService queryservice = (ChrQueryService) this.getBean("chrQueryService");
		
		if(cellid==null||cellid.equals(""))
			cellid="0";
		if(lac==null||lac.equals(""))
			lac="0";
		
		this.page=queryservice.queryChr(_date, msisdn, imei, flowid, cellid, lac, pageNo, pageSize);
	
		
		return SUCCESS;
	}


	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}


	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}


	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}


	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
	}


	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}


	/**
	 * @return the msisdn
	 */
	public String getMsisdn() {
		return msisdn;
	}


	/**
	 * @param msisdn the msisdn to set
	 */
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}


	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}


	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}


	/**
	 * @return the flowid
	 */
	public String getFlowid() {
		return flowid;
	}


	/**
	 * @param flowid the flowid to set
	 */
	public void setFlowid(String flowid) {
		this.flowid = flowid;
	}


	/**
	 * @return the cellid
	 */
	public String getCellid() {
		return cellid;
	}


	/**
	 * @param cellid the cellid to set
	 */
	public void setCellid(String cellid) {
		this.cellid = cellid;
	}


	/**
	 * @return the lac
	 */
	public String getLac() {
		return lac;
	}


	/**
	 * @param lac the lac to set
	 */
	public void setLac(String lac) {
		this.lac = lac;
	}


}
