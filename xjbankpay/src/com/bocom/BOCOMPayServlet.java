/**
 * CMBPayServlet.java
 */
package com.bocom;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bocom.netpay.b2cAPI.BOCOMB2CClient;
import com.bocom.netpay.b2cAPI.BOCOMSetting;

/**
 * 
 * 招行的支付接口,接受
 * 
 * @author 华锋 Mar 28, 201011:01:13 PM
 * 
 */
public class BOCOMPayServlet extends HttpServlet {
	public void init()

	{

	}

	private DateFormat dfdate = new java.text.SimpleDateFormat("yyyyMMdd");
	private DateFormat dftime = new java.text.SimpleDateFormat("HHmmss");

	/**
	 * 
	 * 
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("gbk");
		
		BOCOMB2CClient client = new BOCOMB2CClient();
		Date now = new Date();
		String path=BOCOMPayServlet.class.getResource("B2CMerchant.xml").getPath();
		
		System.out.println(path);
		if(!path.startsWith("/"))
		 path = path.substring(1);
		
		int ret = client.initialize(path); // 该代码只需调用一次
		if (ret != 0) { // 初始化失败
			resp.getWriter().println("初始化失败,错误信息：" + client.getLastErr());
			System.out.println("初始化失败,错误信息：" + client.getLastErr());
			resp.flushBuffer();
			return;
		}
		req.getSession().removeAttribute("token");
		String orderId = req.getParameter("orderid");// 订单号
		String money = req.getParameter("amount");// 金额,以元为单位
		String notifyUrl = req.getParameter("notifyurl");// 收到交行返回的url后，将参数送给notifyUrl
		String orderContent = req.getParameter("orderContent");//不超过50个汉字,订单内容说明:商家填写的其他订单信息，在个人客户页面显示
		String orderMono=req.getParameter("orderMono");//不超过50个汉字,,商家备注信息：不在个人客户页面显示的备注，但可在商户管理页面上显示
		System.out.println("orderContent:"+orderContent);
		System.out.println("orderMono:"+orderMono);
		req.getSession().setAttribute("bocom", notifyUrl);
		
		String interfaceVersion = BOCOMDatas.INTERFACE_VERSION;
		req.setAttribute("interfaceVersion", interfaceVersion);
		
		String merID=BOCOMSetting.MerchantID;
		req.setAttribute("merID", merID);
		String orderid = orderId;
		req.setAttribute("orderid", orderid);
		String orderDate = dfdate.format(now);
		req.setAttribute("orderDate", orderDate);
		String orderTime = dftime.format(now);
		req.setAttribute("orderTime", orderTime);
		String tranType = BOCOMDatas.TRANTYPE;
		req.setAttribute("tranType", tranType);
		String amount = com.bankpay.util.NumberUtil.toMoney(Float.parseFloat(money) / 100);
		req.setAttribute("amount", amount);
		String curType = BOCOMDatas.CURTYPE;
		req.setAttribute("curType", curType);
//		String orderContent = dfdate.format(now) + "支付";
		req.setAttribute("orderContent", orderContent);
//		String orderMono = ""; // 不在个人客户页面显示的备注但可在商户管理页面上显示
		req.setAttribute("orderMono", orderMono);
		String phdFlag = BOCOMDatas.PHD_FLAG;
		req.setAttribute("phdFlag", phdFlag);
		String notifyType = BOCOMDatas.NOTIFY_TYPE;
		req.setAttribute("notifyType", notifyType);
		String merURL = BOCOMDatas.NOTIFYURL;
		req.setAttribute("merURL", merURL);
		String goodsURL = BOCOMDatas.NOTIFYURL;
		req.setAttribute("goodsURL", goodsURL);
		String jumpSeconds = BOCOMDatas.JUMP_SECONDS;
		req.setAttribute("jumpSeconds", jumpSeconds);
		String payBatchNo = now.getTime() / 1000 + ""; // 商家可填入自己的批次号，对账使用
		req.setAttribute("payBatchNo", payBatchNo);
		String proxyMerName = ""; // 二级商户编号/或证件号码
		req.setAttribute("proxyMerName", proxyMerName);
		String proxyMerType = ""; // 代理商家证件类型
		req.setAttribute("proxyMerType", proxyMerType);
		String proxyMerCredentials = "";// 代理商家证件号码
		req.setAttribute("proxyMerCredentials", proxyMerCredentials);
		String netType = BOCOMDatas.NETTYPE;
		req.setAttribute("netType", netType);
		
		String sourceMsg = interfaceVersion + "|" + merID + "|" + orderid + "|" + orderDate + "|" + orderTime + "|"
				+ tranType + "|" + amount + "|" + curType + "|" + orderContent + "|" + orderMono + "|" + phdFlag + "|"
				+ notifyType + "|" + merURL + "|" + goodsURL + "|" + jumpSeconds + "|" + payBatchNo + "|"
				+ proxyMerName + "|" + proxyMerType + "|" + proxyMerCredentials + "|" + netType;

		com.bocom.netpay.b2cAPI.NetSignServer nss = new com.bocom.netpay.b2cAPI.NetSignServer();
		String merchantDN = BOCOMSetting.MerchantCertDN;
		
		
		
		nss.NSSetPlainText(sourceMsg.getBytes("GBK"));
		byte bSignMsg[] = nss.NSDetachedSign(merchantDN);
		
		if (nss.getLastErrnum() < 0) {
			resp.getWriter().println("ERROR:商户端签名失败");
			resp.flushBuffer();
			return;
		}
		
		String signMsg = new String(bSignMsg, "GBK");
		
		req.setAttribute("signMsg", signMsg);
		req.setAttribute("payurl", BOCOMSetting.OrderURL);
		req.getSession().setAttribute("bocom"+orderId, notifyUrl);
		//req.getSession().removeAttribute("bocom"+orderId);
		javax.servlet.RequestDispatcher rd = getServletContext().getRequestDispatcher("/bocom/pay2bocom.jsp");
		rd.forward(req, resp);
	}
}
