/**
 * CMBPayServlet.java
 */
package com.ccb;

import java.io.IOException;
import java.text.DateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bankpay.util.MD5;

/**
 * 
 * 招行的支付接口,接受
 * 
 * @author 华锋 Mar 28, 201011:01:13 PM
 * 
 */
public class CCBPayServlet extends HttpServlet {
	public void init()

	{

	}

	private DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");

	/**
	 * 
	 * 
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String orderId = req.getParameter("orderid");// 订单号
		String money = req.getParameter("amount");// 金额,以元为单位
		String notifyUrl = req.getParameter("notifyurl");// 收到招行返回的url后，将参数送给notifyUrl
		if(notifyUrl.length()>60){
			resp.getWriter().println("通知url长度大于60位，请重新设置");
			resp.getWriter().flush();
			resp.getWriter().close();
			return;
		}

		// 参数 说明
		// Key 商户密钥
		// StrDate 订单日期
		// strBranchID 开户分行号
		// StrCono 商户号
		// StrBillNo 订单号
		// strAmount 订单金额
		// strMerchantPara 商户自定义参数
		// strMerchantUrl 商户接受通知的URL
		// strPayerUserID 付款方用户标识。用来唯一标识商户的一个用户。长度限制为40字符以内。
		// 并不要求商户提供用户的注册名称，但需要保证一个用户对应一个UserID。
		// 商户可以通过某些转换，把用户名转换为一个UserID。比如商户可以把用户注册的“日期+时分秒毫秒”作为UserID。如果还有重复，可再加上用户的IP。
		// 空白表示匿名用户。

		String strBillNo = orderId;
		String strAmount = com.bankpay.util.NumberUtil.toMoney(Float.parseFloat(money) / 100);

		System.out.println("notifyurl:::" + notifyUrl);

		req.setAttribute("BillNo", strBillNo);
		req.setAttribute("REMARK1", notifyUrl);
		req.setAttribute("Amount", strAmount);

		String MERCHANTID = CCBDatas.MERCHANTID;
		String POSID = CCBDatas.POSID;
		String BRANCHID = CCBDatas.BRANCHID;
		String ORDERID = strBillNo;
		String PAYMENT = strAmount;
		String CURCODE =CCBDatas.CURCODE;
		String TXCODE = CCBDatas.TXCODE;
		
		int len=notifyUrl.length();
		String REMARK1="";
		String REMARK2="";
		if(len>30){		
		 REMARK1 = notifyUrl.substring(0,30);
		 REMARK2 = notifyUrl.substring(30);
		
	
		}else{
			 REMARK1 = notifyUrl;
			 REMARK2 = "";
		}
		System.out.println(REMARK1+REMARK2);
//		String md5url = "MERCHANTID=" + MERCHANTID + "&POSID=" + POSID + "&BRANCHID=" + BRANCHID + "&ORDERID=" + ORDERID
//				+ "&PAYMENT=" + PAYMENT + "&CURCODE=" + CURCODE + "&TXCODE=" + TXCODE + "&REMARK1=" + REMARK1
//				+ "&REMARK2=" + REMARK2+"&PUB32="+CCBDatas.KEY;
		
		String md5url = "MERCHANTID=" + MERCHANTID + "&POSID=" + POSID + "&BRANCHID=" + BRANCHID + "&ORDERID=" + ORDERID
		+ "&PAYMENT=" + PAYMENT + "&CURCODE=" + CURCODE + "&TXCODE=" + TXCODE + "&REMARK1=" + REMARK1
		+ "&REMARK2=" + REMARK2;

		System.out.println(md5url);
		String md5 = MD5.md5(md5url).toLowerCase();
		System.out.println(md5);
		req.setAttribute("md5", md5);
		req.setAttribute("REMARK1", REMARK1);
		req.setAttribute("REMARK2", REMARK2);
		javax.servlet.RequestDispatcher rd = getServletContext().getRequestDispatcher("/ccb/pay2ccb.jsp");
		rd.forward(req, resp);

	}
}
