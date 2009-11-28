/**
 * 
 */
package com.sxit.netquality.action;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import com.sxit.common.action.AbstractAction;
import com.sxit.useraction.service.UseractionService;

/**
 * 
 * 
 * 38号错误情况,选定日期之前的n日,都出现38号错误的情况
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class Error38ContinueAction extends AbstractAction {


	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private String date;

	private String startdate;
	public String getStartdate(){
		return this.startdate;
	}
	
	/**
	 * 
	 * 这里的date不能是今天吧
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		Date thedate=null;
		if (date == null || date.equals("")) {
			thedate=com.sxit.stat.util.StatUtil.getPrevDate();
			date = df.format(thedate);
		}else{
//			try{
//				thedate=df.parse(date);
//			}catch(Exception e){
//				thedate=com.sxit.stat.util.StatUtil.getPrevDate();
//				date = df.format(thedate);
//			}
			thedate= com.sxit.stat.util.StatUtil.getDate(date);
		}
		
		UseractionService service=(UseractionService)this.getBean("useractionService");
		//连续n天的38号错误情况号码
		mobilelist=service.getConinueErrorMobileList(thedate, count, 38);
		Date lastdate = com.sxit.stat.util.StatUtil.getPrevCountDate(thedate, count - 1);
		startdate=df.format(lastdate);
		return SUCCESS;
	}
	private List mobilelist;
	private int count=5;

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the mobilelist
	 */
	public List getMobilelist() {
		return mobilelist;
	}
	
}
