/**
 * 
 */
package com.sxit.useraction.action;

import java.text.DateFormat;
import java.util.Date;

import com.sxit.common.action.AbstractAction;
import com.sxit.useraction.models.ErrorCodeAllStat;
import com.sxit.useraction.service.UseractionService;

/**
 * 
 * pdp失败情况总表 分为实时的以及按天的
 * 
 * 实时的情况，可按照word中的来 按天的话，显示最近3天的数据情况。 因为按天写的话，不好出那个数据
 * 
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class UserPdpErrorAllAction extends AbstractAction {

	private static DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
//	private static DateFormat dfyyyyMMdd = new java.text.SimpleDateFormat("yyyyMMdd");
	private static DateFormat dfhour = new java.text.SimpleDateFormat("yyyy-MM-dd HH:00");
	// private static DateFormat dfsec = new
	// java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
			thedate= com.sxit.stat.util.StatUtil.getDate(date);
		}
		// 实时的方式
		if (flag.equals("1")) {

			Date today = new Date();
//			
			int nowhourstart =(int)( com.sxit.stat.util.StatUtil.getDateHourTime(today)/1000);
			int nowhourend = com.sxit.stat.util.StatUtil.getHourAfterTime(nowhourstart);
			int prehourstart = nowhourstart - 60 * 60;

			long todaystart = com.sxit.stat.util.StatUtil.getTodaydayTime();
			long todayend = com.sxit.stat.util.StatUtil.getOneDayAfter(todaystart);

			date1 = df.format(today);

			errorallstat1 = service.getPdpErrorStatics((int) (todaystart / 1000), (int) (todayend / 1000));
			errorallstat2 = service.getPdpErrorStatics(nowhourstart, nowhourend);
			errorallstat3 = service.getPdpErrorStatics(prehourstart, nowhourstart);

			date2 = dfhour.format(new java.sql.Timestamp(nowhourstart * 1000L));
			date3 = dfhour.format(new java.sql.Timestamp(prehourstart * 1000L));

			System.out.println(date1 + ",,," + date2 + ",,," + date3);

		} else {

			date1 = date;
			String today = df.format(new Date());

			long todaystart = com.sxit.stat.util.StatUtil.getDateTime(thedate);
			long todayend = com.sxit.stat.util.StatUtil.getOneDayAfter(todaystart);
			long pretodaystart = todaystart - 24 * 60 * 60 * 1000;
			long pretodaystart2 = pretodaystart - 24 * 60 * 60 * 1000;
			// 当天的
			date2 = df.format(new java.sql.Timestamp(pretodaystart));
			date3 = df.format(new java.sql.Timestamp(pretodaystart2));

			if (today.equals(date1))
				errorallstat1 = service.getPdpErrorStatics((int) (todaystart / 1000), (int) (todayend / 1000));
			else
				errorallstat1 = service.getPdpErrorStatics(date1.replace("-", ""), 1);

			// 昨天的
			if (today.equals(date2))
				errorallstat2 = service.getPdpErrorStatics((int) (pretodaystart / 1000), (int) (todaystart / 1000));
			else
				errorallstat2 = service.getPdpErrorStatics(date2.replace("-", ""), 1);

			// 前天的
			if (today.equals(date3))
				errorallstat3 = service.getPdpErrorStatics((int) (pretodaystart2 / 1000), (int) (pretodaystart / 1000));
			else
				errorallstat3 = service.getPdpErrorStatics(date3.replace("-", ""), 1);

		}
		if (resultType.equals("list"))
			return SUCCESS;
		else if(resultType.equals("toindex"))
			return "toindex";
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
