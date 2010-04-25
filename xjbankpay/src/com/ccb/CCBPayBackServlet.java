/**
 * CMBPayServlet.java
 */
package com.ccb;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cmb.MerchantCode;

/**
 * 
 * 招行的支付接口,接受
 * 
 * @author 华锋 Mar 28, 201011:01:13 PM
 * 
 */
public class CCBPayBackServlet extends HttpServlet {
	public void init()

	{

	}

	private DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 
	 * 
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// POSID 商户柜台代码 CHAR(9) 从商户传送的信息中获得
		// BRANCHID 分行代码 CHAR(9) 从商户传送的信息中获得
		// ORDERID 定单号 CHAR(30) 从商户传送的信息中获得
		// PAYMENT 付款金额 NUMBER(16,2) 从商户传送的信息中获得
		// CURCODE 币种 CHAR(2) 从商户传送的信息中获得
		// REMARK1 备注一 CHAR(30) 从商户传送的信息中获得
		// REMARK2 备注二 CHAR(30) 从商户传送的信息中获得
		// SUCCESS 成功标志 CHAR(1) 成功时返回Y
		// SIGN 数字签名 CHAR(256)

		String POSID = req.getParameter("POSID");
		String BRANCHID = req.getParameter("BRANCHID");
		String ORDERID = req.getParameter("ORDERID");
		String PAYMENT = req.getParameter("PAYMENT");
		String CURCODE = req.getParameter("CURCODE");
		String REMARK1 = req.getParameter("REMARK1");
		String REMARK2 = req.getParameter("REMARK2");
		String SUCCESS = req.getParameter("SUCCESS");
		String SIGN = req.getParameter("SIGN");

		System.out.println("POSID::" + POSID);
		System.out.println("BRANCHID::" + BRANCHID);
		System.out.println("ORDERID::" + ORDERID);
		System.out.println("PAYMENT::" + PAYMENT);
		System.out.println("CURCODE::" + CURCODE);
		System.out.println("REMARK1" + REMARK1);
		System.out.println("SUCCESS::" + SUCCESS);
		System.out.println("SIGN::" + SIGN);

		String q = req.getQueryString();
		System.out.println("字符串:" + q);

		String signurl = "POSID=" + POSID + "&BRANCHID=" + BRANCHID + "&ORDERID=" + ORDERID + "&PAYMENT=" + PAYMENT
				+ "&CURCODE=" + CURCODE + "&REMARK1＝" + REMARK1 + "&REMARK2=" + REMARK2 + "&SUCCESS=" + SUCCESS;
		System.out.println("字符串signurl:" + signurl);
		try {

			CCBSign.RSASig rsasig = new CCBSign.RSASig();
			String privatekey = "";
			// rsasig.setPrivateKey(privatekey);
			rsasig.setPublicKey(CCBDatas.PUBLICKEY);

			// String strSign=rsasig.generateSigature(signurl);
			// System.out.println("字符串strSign:"+strSign);

			boolean isok = rsasig.verifySigature(SIGN, signurl);

			System.out.println("签名结果:::" + isok);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 将页面转向到提交的notifyurl
		resp.sendRedirect(REMARK1+REMARK2 + "?orderid=" + ORDERID + "&tranStat=" + (SUCCESS.equalsIgnoreCase("Y") ? "1" : "2"));

	}
}
