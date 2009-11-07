/**
 * 
 */
package com.sxit.query.action;

import java.text.DateFormat;
import java.util.Date;

import com.sxit.common.action.AbstractListAction;
import com.sxit.query.service.QueryService;

/**
 * 
 * SGSN流量分析
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class QueryByAPNAction extends AbstractListAction {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private String start;
	private String end;

	private String date;

	private String apn;

	/**
	 * @return the apn
	 */
	public String getApn() {
		return apn;
	}

	/**
	 * @param apn
	 *            the apn to set
	 */
	public void setApn(String apn) {
		this.apn = apn;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		if(this.date==null||this.date.equals("")){
			this.date=df.format(new java.util.Date());
		}
		
		QueryService queryservice = (QueryService) this.getBean("queryService");
		this.start = df.format(new java.util.Date());
		Date enddate = this.getPrevCountDate(7);
		this.end = df.format(enddate);
		Date _date = df.parse(date);
		
		if(apn!=null&&!apn.equals(""))
		{
		if (resultType.equals("list")){
			this.page = queryservice.queryCdr(_date, null, null, apn, pageNo, pageSize);
			return SUCCESS;
		}

		else if (resultType.equals("excel")){
			this.page = queryservice.queryCdr(_date, null, null, apn, pageNo, Integer.MAX_VALUE);
			return "excel";
		}
		}
		return SUCCESS;
	}

	// public static void main(String[] args)throws Exception{
	// String start=df.format(new java.util.Date());
	//	
	// Date enddate=com.sxit.stat.util.StatUtil.getPrevCountDate(6);
	// String end=df.format(enddate);
	//		
	// System.out.println(start);
	// System.out.println(end);
	// Date date= df.parse("2009-11-05");
	//		
	// System.out.println(com.sxit.stat.util.StatUtil.getCdrTable(date));
	// System.out.println(com.sxit.stat.util.StatUtil.getYestardayCdrTable());
	// }

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
	 * @return the start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}

}
