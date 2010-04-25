/**
 * CMBPayServlet.java
 */
package com.abc;

import java.io.IOException;
import java.text.DateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitrust.trustpay.client.b2c.PaymentResult;

/**
 * 
 * 招行的支付接口,接受
 * 
 * @author 华锋 Mar 28, 201011:01:13 PM
 * 
 */
public class ABCPayBackServlet extends HttpServlet {
	public void init()

	{

	}

	private DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 
	 * 
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		try {
			// 1、取得 MSG 参数，并利用此参数值生成支付结果对象
			PaymentResult tResult = new PaymentResult(request.getParameter("MSG"));

			// 2、判断支付结果状态，进行后续操作
			if (tResult.isSuccess()) {
				// 3、支付成功
				System.out.println("TrxType         = [" + tResult.getValue("TrxType") + "]");
				System.out.println("OrderNo         = [" + tResult.getValue("OrderNo") + "]");
				System.out.println("Amount          = [" + tResult.getValue("Amount") + "]");
				System.out.println("BatchNo         = [" + tResult.getValue("BatchNo") + "]");
				System.out.println("VoucherNo       = [" + tResult.getValue("VoucherNo") + "]");
				System.out.println("HostDate        = [" + tResult.getValue("HostDate") + "]");
				System.out.println("HostTime        = [" + tResult.getValue("HostTime") + "]");
				System.out.println("MerchantRemarks = [" + tResult.getValue("MerchantRemarks") + "]");
				System.out.println("PayType         = [" + tResult.getValue("PayType") + "]");
				System.out.println("NotifyType      = [" + tResult.getValue("NotifyType") + "]");
			} else {
				// 4、支付失败
				System.out.println("ReturnCode   = [" + tResult.getReturnCode() + "]");
				System.out.println("ErrorMessage = [" + tResult.getErrorMessage() + "]");

			}

			// 将页面转向到提交的notifyurl
			response.sendRedirect(tResult.getValue("MerchantRemarks") + "?orderid=" + tResult.getValue("OrderNo")
					+ "&tranStat=" + (tResult.isSuccess() ? "1" : "2"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
