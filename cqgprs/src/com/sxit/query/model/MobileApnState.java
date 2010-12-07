/**
 * MobileApnState.java
 */
package com.sxit.query.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sxit.netquality.models.Cell;
import com.sxit.query.util.RauUtil;

/**
 * @author 华锋 Jun 15, 20107:52:40 PM
 * 
 */
public class MobileApnState {

	private static DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void toWebString() {
		webstringList.clear();
		if (status == null || status.equalsIgnoreCase("noattach")) {
			// 2010-05-06 10:38:26
			// 13808304084在SGSN9无信息
			webstringList.add(df.format(new Date()));
			webstringList.add(mobile + "在" + sgsnid + "无信息");
		} else if (status.trim().toLowerCase().indexOf("idle") != -1) {
			// 2010-05-06 10:38:26
			// 13808304084在SGSN9状态为IDLE
			// IMSI：460022236503637
			// 签约APN：CMWAP，CMNET,CMMM
			// 或者
			// 2010-05-06 10:38:26
			// 13808304084在SGSN9状态为IDLE
			// IMSI：460022236503637
			// 签约APN为：CMWAP，CMNET, SMJT.CQ
			// SMJT,CQ地址分配方式：动态
			// 签约IP地址为：无
			webstringList.add(df.format(new Date()));
			webstringList.add(mobile + "在" + sgsnid + "的状态为:" + status.toUpperCase());
			webstringList.add("IMEI:" + (this.subdata == null ? "无" : this.subdata.getImei()));
			webstringList.add("签约APN:" + this.getSubapnstr());
			webstringList.addAll(hangyestr);
		} else if (status.trim().toLowerCase().indexOf("ready") != -1
				|| status.trim().toLowerCase().indexOf("standby") != -1
				|| status.trim().toLowerCase().indexOf("connected") != -1) {
			// 2010-05-06 10:38:26
			// 13808304084在SGSN9READY
			// IMSI：460022236503637
			// 所在小区：460-00-13080-26223 （汽车北站）
			// 签约APN：CMWAP，CMNET,SMJT.CQ
			// SMJT,CQ地址分配方式：静态
			// 签约IP地址：10.197.73.139
			// 激活APN: SMJT.CQ
			// 所在GGSN：221.177.189.130（GGSN03）
			// 激活IP地址：10.197.73.139
			webstringList.add(df.format(new Date()));

			String apninuse = (activeapn == null ? "无" : activeapn.getApnRequest());
			String ggsninuse = (activeapn == null ? "无" : activeapn.getGgsnInUse());
			String ipinuse = (activeapn == null ? "无" : activeapn.getAddressInUse());

			webstringList.add(mobile + "在" + sgsnid + "的状态为:" + status.toUpperCase());
			webstringList.add("IMEI:" + (this.subdata == null ? "无" : this.subdata.getImei()));

			// 这里要区分华为和爱立信

			webstringList.add("所在小区:" + getSubDataCell());

			webstringList.add("签约APN:" + this.getSubapnstr());
			webstringList.addAll(hangyestr);

			webstringList.add("激活APN:" + apninuse);
			webstringList.add("所在GGSN：" + ggsninuse);
			webstringList.add("激活IP地址：" + ipinuse);
		}

		// System.out.println("webstringList:::::::"+webstringList);
	}

	public String getSubDataCell() {
		try {
			if (this.subdata == null)
				return "无";

			int sgsnidseq = Integer.parseInt(sgsnid.substring(sgsnid.length() - 1));
			String subcell = "";
			if (sgsnidseq < 7) {
				if (this.subdata.getCellcgi() == null || this.subdata.getCellcgi().trim().equals(""))
					subcell = this.subdata.getRai();
				else
					subcell = subdata.getCellcgi();

			} else {

				if (subdata.getIs3g() && subdata.getServicearea() != null && subdata.getServicearea().length() == 13) {
					// 用户所属服务区 = 46000a3094e53
					String lac = subdata.getServicearea().substring(5, 9);
					String cell = subdata.getServicearea().substring(9, 13);
					subcell = "460-00-" + Integer.valueOf(lac, 16) + "-" + Integer.valueOf(cell, 16);

				} else {

					int cellid = 0;
					if (this.subdata.getCellcgi() != null && !this.subdata.getCellcgi().equals("")) {
						// 16进制转换为10进制
						cellid = Integer.valueOf(this.subdata.getCellcgi().toLowerCase().replace("0x", ""), 16);
					}

					String rai = "";
					if (this.subdata.getRai() != null) {
						String temp = subdata.getRai();
						// 46000333801这样的形式，转化为460-00-33380这样的形式
						String first = temp.substring(0, 3);
						String second = temp.substring(3, 5);
						String lac = temp.substring(5, 9);
						int lacint = Integer.valueOf(lac.toLowerCase().replace("0x", ""), 16);
						rai = first + "-" + second + "-" + lacint;
					} else {
						rai = "460-00-0";
					}
					subcell = rai + "-" + cellid;
				}
			}

			String[] s = subcell.split("-");
			if (s.length != 4) {
				System.out.println("小区信息为:::" + s);
				return "小区信息获取异常";
			}
			Cell cell = com.sxit.netquality.service.BasicSetService.ALL_CELLS.get(s[2] + "-" + s[3]);
			if (cell == null)
				return subcell + "(无对应小区信息)";
			return subcell + "(" + cell.getCellname() + ")";
		} catch (Exception e) {
			e.printStackTrace();
			return "小区信息获取异常";
		}
	}

	public String getWebstring() {
		StringBuffer sb = new StringBuffer();
		for (String s : webstringList) {
			sb.append("<p>").append(s).append("</p>");
		}
		return sb.toString();
	}

	private List<String> webstringList = new ArrayList<String>();

	public List<String> getWebstringList() {
		return this.webstringList;
	}

	private boolean hasexception;
	private String exception;
	private String sgsnid;
	private String mobile;
	private String status;// 状态,未附着,Idle,ready,standby

	private SubscriberData subdata;
	private List<SubscribedPDP> subpdps = new ArrayList<SubscribedPDP>();
	private ActiveApn activeapn;

	private List<String> hangyestr = new ArrayList<String>();

	public String getSubapnstr() {
		hangyestr.clear();
		StringBuffer sb = new StringBuffer();
		System.out.println("subpdps.size()::::::::::::" + subpdps.size());
		for (int i = 0; i < subpdps.size(); i++) {
			SubscribedPDP subpdp = subpdps.get(i);
			// System.out.println("subpdp::::"+subpdp+"==>"+subpdp.getSubapn());
			sb.append(subpdp.getSubapn()).append(",");
			if (subpdp.getSubapn().toLowerCase().indexOf(".cq") != -1) {// 行业apn,华为的都是大写

				if (!subpdp.getAddress().equals("0.0.0.0") && RauUtil.isIp(subpdp.getAddress())) {
					hangyestr.add(subpdp.getSubapn() + "地址分配方式:静态");
					hangyestr.add("签约IP地址:" + subpdp.getAddress());
				} else {
					hangyestr.add(subpdp.getSubapn() + "地址分配方式:动态");
					hangyestr.add("签约IP地址:无");
				}
			}
		}
		int len = sb.length();
		if (len != 0) {
			sb = sb.delete(len - 1, len);
		} else {
			sb.append("无");
		}

		return sb.toString();
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the subdata
	 */
	public SubscriberData getSubdata() {
		return subdata;
	}

	/**
	 * @param subdata
	 *            the subdata to set
	 */
	public void setSubdata(SubscriberData subdata) {
		this.subdata = subdata;
	}

	/**
	 * @return the subpdp
	 */
	public List<SubscribedPDP> getSubpdps() {
		return subpdps;
	}

	/**
	 * @param subpdp
	 *            the subpdp to set
	 */
	public void addSubpdp(SubscribedPDP subpdp) {
		this.subpdps.add(subpdp);
	}

	/**
	 * @return the activeapn
	 */
	public ActiveApn getActiveapn() {
		return activeapn;
	}

	/**
	 * @param activeapn
	 *            the activeapn to set
	 */
	public void setActiveapn(ActiveApn activeapn) {
		this.activeapn = activeapn;
	}

	/**
	 * @return the sgsnid
	 */
	public String getSgsnid() {
		return sgsnid;
	}

	/**
	 * @param sgsnid
	 *            the sgsnid to set
	 */
	public void setSgsnid(String sgsnid) {
		this.sgsnid = sgsnid;
	}

	/**
	 * @return the hasexception
	 */
	public boolean getHasexception() {
		return hasexception;
	}

	/**
	 * @param hasexception
	 *            the hasexception to set
	 */
	public void setHasexception(boolean hasexception) {
		this.hasexception = hasexception;
	}

	/**
	 * @return the exception
	 */
	public String getException() {
		return exception;
	}

	/**
	 * @param exception
	 *            the exception to set
	 */
	public void setException(String exception) {
		this.exception = exception;
	}

	public String getWebexception() {
		if (exception != null && !exception.equals(""))
			return exception.replaceAll("\n", "<br/>");
		return "";
	}
}
