/**
 * LawerChangeCardNoAction.java
 */
package com.changpeng.lawyers.action.ajax;

import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.lawyers.service.LawyersService;
import com.changpeng.models.Lawyers;

/**
 * 密码重置
 * 
 * @author 华锋 2008-5-5 下午05:34:22
 * 
 */
public class LawyersChangeCardNoAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(LawyersChangeCardNoAction.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private static Date parse(String d) throws Exception {
		try {
			return df.parse(d);
		} catch (Exception e) {
			LOG.error("发卡日期解析失败", e);
			return df.parse(df.format(new Date()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		try {
			BasicService basicService = (BasicService) getBean("basicService");

			LawyersService lawyersService = (LawyersService) this.getBean("lawyersService");

			if (theact != null && theact.equals("clear")) {
				Lawyers newlawyer = (Lawyers) basicService.get(Lawyers.class, lawyerid);
				newlawyer.setCarddate(null);
				// newlawyer.setSystemno(null);
				newlawyer.setCardno(null);
				basicService.update(newlawyer);
				cardno = "";
				carddate = "";
				// systemno="";
				changeok = "true";
			} else {

				Lawyers lawyer = lawyersService.getLawyerBySystemno(systemno);
				Lawyers thelawyer = (Lawyers) basicService.get(Lawyers.class, lawyerid);
				if (lawyer != null && lawyer.getSystemno().equals(thelawyer.getSystemno())) {
					cardno = lawyer.getCardno();
					// carddate=lawyer.getCarddate();
					thelawyer.setSystemno(systemno);
					cardno = com.changpeng.common.CommonDatas.AllSystemNos.get(systemno);
					LOG.debug("cardno:::::::" + cardno);
					if (cardno == null || cardno.equals("")) {
						changeok = "systemnoexist";
					} else {
						thelawyer.setCardno(cardno);
						if (carddate == null || carddate.equals(""))
							carddate = df.format(new java.util.Date());

						Date cd = parse(carddate);
						thelawyer.setCarddate(cd);
						basicService.update(thelawyer);
						changeok = "true";
					}
				} else if (lawyer != null && !lawyer.getSystemno().equals(thelawyer.getSystemno())) {
					this.lawyername = lawyer.getLawyername();
					cardno = "";
					carddate = "";
					systemno = "";
					changeok = "repeat";
				} else {

					thelawyer.setSystemno(systemno);
					cardno = com.changpeng.common.CommonDatas.AllSystemNos.get(systemno);
					LOG.debug("cardno:::::::" + cardno);
					if (cardno == null || cardno.equals("")) {
						changeok = "systemnoexist";
					} else {
						thelawyer.setCardno(cardno);
						if (carddate == null || carddate.equals(""))
							carddate = df.format(new java.util.Date());

						Date cd = parse(carddate);
						thelawyer.setCarddate(cd);
						basicService.update(thelawyer);
						changeok = "true";
					}
				}
			}
		} catch (Exception e) {
			LOG.error("会员编号信息修改失败:" + e);
			changeok = "false";
		}

		// TODO Auto-generated method stub
		return SUCCESS;
	}

	private String changeok;

	public String getChangeok() {
		return this.changeok;
	}

	private String theact;
	private String lawyername;
	private String cardno;
	private String carddate;
	private int lawyerid;

	/**
	 * @return the cardno
	 */
	public String getCardno() {
		return cardno;
	}

	/**
	 * @param cardno
	 *            the cardno to set
	 */
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	/**
	 * @return the carddate
	 */
	public String getCarddate() {
		return carddate;
	}

	/**
	 * @param carddate
	 *            the carddate to set
	 */
	public void setCarddate(String carddate) {
		this.carddate = carddate;
	}

	private String systemno;

	/**
	 * @return the systemno
	 */
	public String getSystemno() {
		return systemno;
	}

	/**
	 * @param systemno
	 *            the systemno to set
	 */
	public void setSystemno(String systemno) {
		this.systemno = systemno;
	}

	/**
	 * @return the lawyername
	 */
	public String getLawyername() {
		return lawyername;
	}

	/**
	 * @return the theact
	 */
	public String getTheact() {
		return theact;
	}

	/**
	 * @param theact
	 *            the theact to set
	 */
	public void setTheact(String theact) {
		this.theact = theact;
	}

	/**
	 * @return the lawyerid
	 */
	public int getLawyerid() {
		return lawyerid;
	}

	/**
	 * @param lawyerid
	 *            the lawyerid to set
	 */
	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}

}
