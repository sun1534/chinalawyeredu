/**
 * LawerChangeCardNoAction.java
 */
package com.changpeng.system.action.ajax;

import java.text.DateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysUserService;

/**
 * 密码重置
 * 
 * @author 华锋 2008-5-5 下午05:34:22
 * 
 */
public class LawerChangeCardNoAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(LawerChangeCardNoAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		try {
			BasicService basicService = (BasicService) getBean("basicService");
			debug("======basicService::" + basicService);

			SysUserService userservice = (SysUserService) this.getBean("sysUserService");

			if (theact != null && theact.equals("clear")) {
				SysUser newlawyer = (SysUser) basicService.get(SysUser.class, userid);
				newlawyer.setCarddate(null);
				newlawyer.setSystemno(null);
				newlawyer.setCardno(null);
				basicService.update(newlawyer);
				cardno="";
				carddate="";
				systemno="";
				changeok="true";
			} else {

				SysUser lawyer = userservice.getSysUserBySystemno(systemno);
				SysUser thelawyer = (SysUser) basicService.get(SysUser.class, userid);
				if(lawyer!=null&&lawyer.getSystemno().equals(thelawyer.getSystemno())){
					cardno=lawyer.getCardno();
					carddate=lawyer.getCarddate();
					changeok = "true";
				}
				else if (lawyer!=null&&!lawyer.getSystemno().equals(thelawyer.getSystemno())) {
					this.username = lawyer.getUsername();
					cardno="";
					carddate="";
					systemno="";
					changeok = "repeat";
				} else {

//					SysUser newlawyer = (SysUser) basicService.get(SysUser.class, userid);
//					String oldcardno = null;
//					if (lawyer != null) {
//
//						oldcardno = lawyer.getCardno();
//						lawyer.setCarddate(null);
//						lawyer.setSystemno(null);
//						lawyer.setCardno(null);
//
//						basicService.update(lawyer);
//						System.out.println(lawyer.getSystemno() + "的编号已经重新分配给了律师::" + newlawyer.getUsername());
//					}

//					if (oldcardno != null && !"".equals(oldcardno))
//						cardno = oldcardno;

					// if(lawer.getCardno()!=null&&this.cardno.equals(lawer.getCardno())){
					// throw new Exception("已经有律师使用了这个卡号");
					// }

					// debug("========="+lawer);
					// debug("==cardno+"+cardno+"===carddate==="+carddate);
					thelawyer.setSystemno(systemno);
					cardno = com.changpeng.common.CommonDatas.AllSystemNos.get(systemno);
					thelawyer.setCardno(cardno);
					if (carddate == null || carddate.equals(""))
						carddate = df.format(new java.util.Date());
					thelawyer.setCarddate(carddate);
					basicService.update(thelawyer);
					changeok = "true";
				}
			}
		} catch (Exception e) {
			LOG.error("会员编号信息修改失败:" + e);
			changeok = "false";
		}

		// TODO Auto-generated method stub
		return SUCCESS;
	}

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private String changeok;

	public String getChangeok() {
		return this.changeok;
	}

	private String theact;
	private String username;
	private String cardno;
	private String carddate;
	private int userid;

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

	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
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

}
