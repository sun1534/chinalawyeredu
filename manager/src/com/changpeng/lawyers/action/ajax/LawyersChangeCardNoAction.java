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
				newlawyer.setCardno(null);
				basicService.update(newlawyer);
				cardno = "";
				carddate = "";
				changeok = "true";
			} else {
				Lawyers lawyer =null;
				if(systemno!=null&&!systemno.equals(""))
					lawyer= lawyersService.getLawyerBySystemno(systemno);
				Lawyers thelawyer = (Lawyers) basicService.get(Lawyers.class, lawyerid);
				// 先要判断这个系统编号是否已经使用

				int groupid = this.getLoginUser().getSysGroup() == null ? 0 : this.getLoginUser().getSysGroup()
						.getGroupid();
				
				int cityid=lawyer.getDirectunion();
				int provinceid=lawyer.getProvinceunion();
				
				if(!(cityid==8078||(cityid==11002750||provinceid==23))){
					
					//判断卡号是否已使用,判断编号是否已使用
					Lawyers cardnolawyer=null;
					if(cardno!=null&&!cardno.equals(""))
					 cardnolawyer=lawyersService.getLawyerByCardno(cardno);
					if(lawyer!=null&&!lawyer.getSystemno().equals(thelawyer.getSystemno())){//系统编号重复
						changeok = "systemnoexist";
						lawyername=lawyer.getLawyername();
					}
					else if(cardnolawyer!=null&&!cardnolawyer.getSystemno().equals(thelawyer.getCardno())){//卡号重复
						changeok = "cardnoexist";
						lawyername=cardnolawyer.getLawyername();
					}else{
						thelawyer.setSystemno(systemno);
						thelawyer.setCardno(cardno);
						if (carddate == null || carddate.equals(""))
							carddate = df.format(new java.util.Date());
						Date cd = parse(carddate);
						thelawyer.setCarddate(cd);
						basicService.update(thelawyer);
						changeok = "true";
					}
				}

				else {//杭州的这样判断，他要能自动去获取数据
					if (lawyer != null && lawyer.getSystemno().equals(thelawyer.getSystemno())) {
						cardno = lawyer.getCardno();
						System.out.println("cardno 111:::::::" + cardno);
						// carddate=lawyer.getCarddate();
						thelawyer.setSystemno(systemno);
						System.out.println("systemno 111:::::::" + systemno);
						cardno = com.changpeng.common.CommonDatas.AllSystemNos.get(systemno);
						
						LOG.debug("cardno:::::::" + cardno);
						System.out.println("cardno:::::::" + cardno);
						if (cardno == null || cardno.equals("")) {
							changeok = "systemnoerror";
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
							changeok = "systemnoerror";
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
