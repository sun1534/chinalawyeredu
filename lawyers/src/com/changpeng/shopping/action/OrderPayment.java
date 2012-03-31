package com.changpeng.shopping.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chinapay.PrivateKey;
import chinapay.SecureLink;
import com.changpeng.shopping.util.Config;
import com.changpeng.shopping.util.PaymentBean;

public class OrderPayment extends HttpServlet {

	private static final String KEY_CHINAPAY_MERID = "chinapay.merid";
	private static final String KEY_CHINAPAY_MERKEY_FILEPATH = "chinapay.merkey.filepath";
	private static final String PAYMENT_URL = "chinapay.payment.url";
	private static final String VERSION_INPUT_JSP = "/template/versionInput.jsp";
	private static final String CREATE_ORDER_INPUT_JSP = "/template/createOrderInput.jsp";
	private static final String CREATE_ORDER_COMMIT_JSP = "/shopping/paymentcommit.jsp";
	
	/**
	 * Constructor of the object.
	 */
	public OrderPayment() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		System.out.println("bbbbbbbbbbb");
		List errorList = new ArrayList();
		String MerKeyPath = null;
		String pay_url = null;
		try {
			Properties config = Config.getInstance().getProperties();
			MerKeyPath = config.getProperty(KEY_CHINAPAY_MERKEY_FILEPATH);
			pay_url = config.getProperty(PAYMENT_URL);
		} catch (Exception e) {
			errorList.add("errors_properties_init_failed");
		}
		if (!errorList.isEmpty()) {
			req.setAttribute("errors", errorList);
			req.getRequestDispatcher(CREATE_ORDER_INPUT_JSP).forward(req, resp);
			return;
		}
		
		// 支付订单数据准备
		String MerId = req.getParameter("MerId");
		String OrdId = req.getParameter("OrdId");
		//String Version = req.getParameter("Version");
		String Version = "20070129";
		String TransAmt = req.getParameter("TransAmt");// 12
		String CuryId = req.getParameter("CuryId");// 3
		String TransDate = req.getParameter("TransDate");// 8
		String TransType = "0001";// 4
		String BgRetUrl = req.getParameter("BgRetUrl");
		String PageRetUrl = req.getParameter("PageRetUrl");
		String GateId = req.getParameter("GateId");
		String Priv1 = req.getParameter("Priv1");
		/*
		 * try { Priv1 = Base64Util.base64Encoder(Priv1); } catch (Exception e1)
		 * { // TODO Auto-generated catch block e1.printStackTrace(); }
		 */
		String ChkValue = null;
		// 20100304防钓鱼版本还需使用以下ClientIp数据
		String ClientIp = null;
		if (req.getHeader("X-Forwarded-For") == null) {
			System.out.println("X-Forwarded-For is null");
			ClientIp = req.getRemoteAddr();
		} else {
			System.out.println("X-Forwarded-For is not null");
			ClientIp = req.getHeader("X-Forwarded-For");
		}
		// 境外版本还需使用以下6个数据，境内版本不需要
		String CountryId = req.getParameter("CountryId");
		String TransTime = req.getParameter("TransTime");
		String TimeZone = req.getParameter("TimeZone");
		String DSTFlag = req.getParameter("DSTFlag");
		String Priv2 = req.getParameter("Priv2");
		String ExtFlag = "00";
		
		boolean buildOK = false;
		int KeyUsage = 0;
		PrivateKey key = new PrivateKey();
		try {
			buildOK = key.buildKey(MerId, KeyUsage, MerKeyPath);
		} catch (Exception e) {
			e.printStackTrace();
			// e.printStackTrace();
		}
		if (!buildOK) {
			System.out.println("build error!");
			errorList.add("build_key_error!");
		}
		if (!errorList.isEmpty()) {
			req.setAttribute("errors", errorList);
			req.getRequestDispatcher(CREATE_ORDER_COMMIT_JSP).forward(req, resp);
			return;
		}
		
		try {
			SecureLink sl = new SecureLink(key);
		
			if (Version.equalsIgnoreCase("20070129")) {
				// 20070129版本签名方法
				ChkValue = sl.Sign(MerId + OrdId + TransAmt + CuryId
						+ TransDate + TransType + Priv1);
				System.out.println(MerId + OrdId + TransAmt + CuryId
						+ TransDate + TransType + Priv1);
			} else if (Version.equalsIgnoreCase("20100304")) {
				// 防钓鱼版本签名方法
				ChkValue = sl.Sign(MerId + OrdId + TransAmt + CuryId
						+ TransDate + TransType + Priv1 + ClientIp);
				System.out.println(MerId + OrdId + TransAmt + CuryId
						+ TransDate + TransType + Priv1 + ClientIp);
			} else if (Version.equalsIgnoreCase("20080515")) {
				// 境外版本签名方法
				ChkValue = sl.Sign(MerId + OrdId + TransAmt + CuryId
						+ TransDate + TransTime + TransType + CountryId
						+ TimeZone + DSTFlag + ExtFlag + Priv1);
				System.out.println(MerId + OrdId + TransAmt + CuryId
						+ TransDate + TransTime + TransType + CountryId
						+ TimeZone + DSTFlag + ExtFlag + Priv1);
			} else {
				// 早期版本签名方法
				ChkValue = sl.signOrder(MerId, OrdId, TransAmt, CuryId,
						TransDate, TransType);
				System.out.println(MerId);
				System.out.println(OrdId);
				System.out.println(TransAmt);
				System.out.println(TransDate);
				System.out.println(Priv1);
			}
			System.out.println(ChkValue);
		} catch (Exception e) {
			e.printStackTrace();
			// e.printStackTrace();
			System.out.println(e.getMessage());
			errorList.add(e.getMessage());
		}
		if (!errorList.isEmpty()) {
			req.setAttribute("errors", errorList);
			req.getRequestDispatcher(CREATE_ORDER_COMMIT_JSP).forward(req, resp);
			return;
		}
		PaymentBean pay = new PaymentBean();
		pay.setMerId(MerId);
		pay.setOrdId(OrdId);
		pay.setTransAmt(TransAmt);
		pay.setTransDate(TransDate);
		pay.setTransType(TransType);
		pay.setVersion(Version);
		pay.setCuryId(CuryId);
		pay.setGateId(GateId);
		pay.setPageRetUrl(PageRetUrl);
		pay.setBgRetUrl(BgRetUrl);
		pay.setPriv1(Priv1);
		pay.setChkValue(ChkValue);
		
		// 20100304防钓鱼版本还需使用以下ClientIp数据
		pay.setClientIP(ClientIp);
		
		// 境外版本还需使用以下6个数据，境内版本不需要
		pay.setTransTime(TransTime);
		pay.setCountryId(CountryId);
		pay.setDSTFlag(DSTFlag);
		pay.setExtFlag(ExtFlag);
		pay.setTimeZone(TimeZone);
		pay.setPriv2(Priv2);
		
		req.setAttribute("paybean", pay);
		req.setAttribute("pay_url", pay_url);
		//req.getRequestDispatcher(CREATE_ORDER_COMMIT_JSP).forward(req, resp);
		return;
		}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
