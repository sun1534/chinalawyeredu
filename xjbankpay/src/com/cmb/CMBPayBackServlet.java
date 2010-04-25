/**
 * CMBPayServlet.java
 */
package com.cmb;

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
public class CMBPayBackServlet extends HttpServlet {
	public void init()

	{

	}

	private DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 
	 * 
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Succeed： 取值Y(成功)或N(失败)；
		// CoNo: 商户号，6位长数字，由银行在商户开户时确定；
		// BillNo: 定单号(由支付命令送来)；
		// Amount: 实际支付金额(由支付命令送来)；
		// Date: 交易日期(由支付命令送来)；
		// Msg:
		// 银行通知用户的支付结果消息。信息的前38个字符格式为：4位分行号＋6位商户号＋8位银行接受交易的日期＋20位银行流水号；可以利用交易日期＋银行流水号＋定单号对该定单进行结帐处理；
		// MerchantPara：商户自己的参数；
		// Signature: 银行用自己的Private Key对通知命令的签名。

		String Succeed = req.getParameter("Succeed");
		String CoNo = req.getParameter("CoNo");
		String BillNo = req.getParameter("BillNo");
		String Amount = req.getParameter("Amount");
		String Date = req.getParameter("Date");
		String Msg = req.getParameter("Msg");
		String MerchantPara = req.getParameter("MerchantPara");
		String Signature = req.getParameter("Signature");

		System.out.println("Succeed::" + Succeed);
		System.out.println("CoNo::" + CoNo);
		System.out.println("BillNo::" + BillNo);
		System.out.println("Amount::" + Amount);
		System.out.println("Date::" + Date);
		System.out.println("Msg::" + Msg);
		System.out.println("MerchantPara::" + MerchantPara);
		System.out.println("Signature::" + Signature);

		String q=req.getQueryString();
		System.out.println("字符串:"+q);
		
		String params = "Succeed=" + Succeed + "&CoNo=" + CoNo + "&BillNo=" + BillNo + "&Amount=" + Amount + "&Date="
				+ Date + "&Msg=" + Msg + "&MerchantPara=" + MerchantPara + "&Signature=" + Signature;
		try {
			
			
			
			cmb.netpayment.Security s = new cmb.netpayment.Security(
					"/home/koojj/tomcat6/webapps/pay/WEB-INF/classes/com/cmb/public.key");
			byte[] bytes = q.getBytes("gb2312");
			boolean b = s.checkInfoFromBank(bytes);
			System.out.println("签名结果:::" + b);

		} catch (Exception e) {
			e.printStackTrace();
		}
		//将页面转向到提交的notifyurl
		resp.sendRedirect(MerchantPara+"?orderid="+BillNo+"&tranStat="+(Succeed.equalsIgnoreCase("Y")?"1":"2"));

	}
}
