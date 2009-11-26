/**
 * 
 */
package com.sxit.netquality.action;

import java.text.DateFormat;
import java.util.Date;

import com.sxit.common.action.AbstractAction;
import com.sxit.useraction.models.ErrorCodeAllStat;
import com.sxit.useraction.service.UseractionService;

/**
 * 
 * SGSN流量分析
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class PdpErrorAllAction extends AbstractAction {

	private static DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat dfhour = new java.text.SimpleDateFormat("yyyy-MM-dd HH:00");
//	private static DateFormat dfsec = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 1是实时 2是按天,也就是最近3天的
	private String flag = "1";
	/**
	 * 哪天
	 */
	private String date;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		UseractionService service = (UseractionService) this.getBean("useractionService");
		Date thedate = null;
		if (date == null) {
			thedate = com.sxit.stat.util.StatUtil.getPrevDate();
			date = df.format(thedate);
		} else {
//			try {
//				thedate = df.parse(date);
//			} catch (Exception e) {
//				thedate = com.sxit.stat.util.StatUtil.getPrevDate();
//				date = df.format(thedate);
//			}
			thedate= com.sxit.stat.util.StatUtil.getDate(date);
		}
		// 实时的方式
		if (flag.equals("1")) {

			Date today = new Date();

			int nowhourstart = UseractionService.getDateHourTime(today);
			int nowhourend = UseractionService.getHourAfterTime(nowhourstart);
			int prehourstart = nowhourstart - 60 * 60;

			long todaystart = com.sxit.stat.util.StatUtil.getTodaydayTime();
			long todayend = com.sxit.stat.util.StatUtil.getOneDayAfter(todaystart);

			date1=df.format(today);
	
			
			errorallstat1 = service.getPdpErrorStatics((int) (todaystart / 1000), (int) (todayend / 1000));
			errorallstat2 = service.getPdpErrorStatics(nowhourstart, nowhourend);
			errorallstat3 = service.getPdpErrorStatics(prehourstart, nowhourstart);

			date2=dfhour.format(new java.sql.Timestamp(nowhourstart*1000L));
			date3=dfhour.format(new java.sql.Timestamp(prehourstart*1000L));
			
			System.out.println(date1+",,,"+date2+",,,"+date3);
			
			
		} else {

			
			date1=date;
	

			long todaystart = com.sxit.stat.util.StatUtil.getDateTime(thedate);
			long todayend = com.sxit.stat.util.StatUtil.getOneDayAfter(todaystart);
			long pretodaystart = todaystart - 24 * 60 * 60 * 1000;
			long pretodaystart2 = pretodaystart - 24 * 60 * 60 * 1000;

			// 前天的
			errorallstat2 = service.getPdpErrorStatics((int) (pretodaystart2 / 1000), (int) (pretodaystart / 1000));
			// 昨天的
			errorallstat3 = service.getPdpErrorStatics((int) (pretodaystart / 1000), (int) (todaystart / 1000));
			// 当天的
			errorallstat1 = service.getPdpErrorStatics((int) (todaystart / 1000), (int) (todayend / 1000));

			date2=df.format(new java.sql.Timestamp(pretodaystart));
			date3=df.format(new java.sql.Timestamp(pretodaystart2));
		}
		if(resultType.equals("list"))
		return SUCCESS;
		return "excel";
	}
	
	private String date1;
	private String date2;
	private String date3;

	private ErrorCodeAllStat errorallstat1;
	private ErrorCodeAllStat errorallstat2;
	private ErrorCodeAllStat errorallstat3;

	/**
	 * @return the errorallstatall
	 */
	public ErrorCodeAllStat getErrorallstat1() {
		return errorallstat1;
	}

	/**
	 * @return the errorallstatnow
	 */
	public ErrorCodeAllStat getErrorallstat2() {
		return errorallstat2;
	}

	/**
	 * @return the errorallstatpre
	 */
	public ErrorCodeAllStat getErrorallstat3() {
		return errorallstat3;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
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
	 * @return the date1
	 */
	public String getDate1() {
		return date1;
	}

	/**
	 * @return the date2
	 */
	public String getDate2() {
		return date2;
	}

	/**
	 * @return the date3
	 */
	public String getDate3() {
		return date3;
	}

}
