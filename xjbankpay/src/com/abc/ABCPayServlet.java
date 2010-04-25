/**
 * CMBPayServlet.java
 */
package com.abc;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitrust.trustpay.client.TrxResponse;
import com.hitrust.trustpay.client.b2c.Order;
import com.hitrust.trustpay.client.b2c.PaymentRequest;

/**
 * 
 * 招行的支付接口,接受
 * 
 * @author 华锋 Mar 28, 201011:01:13 PM
 * 
 */
public class ABCPayServlet extends HttpServlet {
	public void init()

	{

	}

	private DateFormat dfdate = new java.text.SimpleDateFormat("yyyy/MM/dd");
	private DateFormat dftime = new java.text.SimpleDateFormat("HH:mm:ss");

	/**
	 * 
	 * 
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		String orderId = request.getParameter("orderid");// 订单号
		String orderDesc = request.getParameter("orderdesc");// 订单说明
		String money = request.getParameter("amount");// 金额,以元为单位
		String notifyUrl = request.getParameter("notifyurl");// 收到招行返回的url后，将参数送给notifyUrl
		Date date = new Date();
		String tOrderNo = orderId;
		String tOrderDesc = orderDesc;
		String tOrderDate = dfdate.format(date);
		String tOrderTime = dftime.format(date);
		String tOrderAmountStr = money;
		//这个订单网址的意义是啥哦
		String tOrderURL = request.getParameter("OrderURL");
		String tResultNotifyURL = ABCDatas.NOTIFYURL;
		String tMerchantRemarks = notifyUrl;
		double tOrderAmount = Double.parseDouble(tOrderAmountStr);

		// 2、生成订单对象
		Order tOrder = new Order();
		tOrder.setOrderNo(tOrderNo); // 设定订单编号 （必要信息）
		tOrder.setOrderDesc(tOrderDesc); // 设定订单说明
		tOrder.setOrderDate(tOrderDate); // 设定订单日期 （必要信息 - YYYY/MM/DD）
		tOrder.setOrderTime(tOrderTime); // 设定订单时间 （必要信息 - HH:MM:SS）
		tOrder.setOrderAmount(tOrderAmount); // 设定订单金额 （必要信息）
		tOrder.setOrderURL(tOrderURL); // 设定订单网址

		// 3、生成订单订单对象，并将订单明细加入订单中（可选信息）
		// tOrder.addOrderItem(new OrderItem("IP000001", "中国移动IP 卡", 100.00f,
		// 1));
		// tOrder.addOrderItem(new OrderItem("IP000002", "网通IP 卡" , 90.00f, 2));

		// 4、生成支付请求对象
		PaymentRequest tPaymentRequest = new PaymentRequest();
		tPaymentRequest.setOrder(tOrder); // 设定支付请求的订单 （必要信息）
		tPaymentRequest.setProductType(PaymentRequest.PRD_TYPE_ONE); // 设定商品种类
																		// （必要信息）
		// PaymentRequest.PRD_TYPE_ONE：非实体商品，如服务、IP 卡、下载 MP3、...
		// PaymentRequest.PRD_TYPE_TWO：实体商品
		tPaymentRequest.setPaymentType(PaymentRequest.PAY_TYPE_ABC); // 设定支付类型
		// PaymentRequest.PAY_TYPE_ABC：农行卡支付
		// PaymentRequest.PAY_TYPE_INT：国际卡支付
		tPaymentRequest.setNotifyType("0"); // 设定商户通知方式
		// 0：URL 页面通知
		// 1：服务器通知
		tPaymentRequest.setResultNotifyURL(tResultNotifyURL); // 设定支付结果回传网址
																// （必要信息）
		tPaymentRequest.setMerchantRemarks(tMerchantRemarks); // 设定商户备注信息
		tPaymentRequest.setPaymentLinkType(PaymentRequest.PAY_LINK_TYPE_NET); // 设定支付接入方式
		// PaymentRequest.PAY_LINK_TYPE_NET internet 网络接入
		// PaymentRequest.PAY_LINK_TYPE_MOBILE mobile 网络接入
		// PaymentRequest.PAY_LINK_TYPE_TV 数字电视网络接入
		// 5、传送支付请求并取得支付网址
		TrxResponse tTrxResponse = tPaymentRequest.postRequest();
		if (tTrxResponse.isSuccess()) {
			// 6、支付请求提交成功，将客户端导向支付页面
			String paymentUrl = tTrxResponse.getValue("PaymentURL");
			response.sendRedirect(paymentUrl);
		} else {
			// 7、支付请求提交失败，商户自定后续动作

			System.out.println("tTrxResponse.getErrorMessage():" + tTrxResponse.getErrorMessage());
			System.out.println("tTrxResponse.getReturnCode():" + tTrxResponse.getReturnCode());
			
			request.setAttribute("errorMsg", tTrxResponse.getErrorMessage());
			request.setAttribute("errorCode", tTrxResponse.getReturnCode());
			request.setAttribute("error", "error");
			
			javax.servlet.RequestDispatcher rd = getServletContext().getRequestDispatcher("/abc/demo.jsp");
			rd.forward(request, response);

		}
	}
}
