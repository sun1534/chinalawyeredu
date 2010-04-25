/**
 * CMBPayServlet.java
 */
package com.bocom;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bocom.netpay.b2cAPI.BOCOMB2CClient;

/**
 * 
 * 招行的支付接口,接受
 * 
 * @author 华锋 Mar 28, 201011:01:13 PM
 * 
 */
public class BOCOMPayBackServlet extends HttpServlet {

	public void init() {

	}

	private DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 
	 * 
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String notifyMsg = req.getParameter("notifyMsg");// 获取银行通知结果
		System.out.println("notifyMsg::" + notifyMsg);
		int lastIndex = notifyMsg.lastIndexOf("|");
		String signMsg = notifyMsg.substring(lastIndex + 1, notifyMsg.length());// 获取签名信息
		String srcMsg = notifyMsg.substring(0, lastIndex + 1); // 本地验证的源签名信息

		java.util.StringTokenizer stName = new java.util.StringTokenizer(notifyMsg, "|");// 拆解通知结果到一个vector

		java.util.Vector vc = new java.util.Vector();
		int i = 0;
		while (stName.hasMoreTokens()) {
			String value = (String) stName.nextElement();
			if (value.equals(""))
				value = "&nbsp;";
			vc.add(i++, value);
		}

		// 商户客户号 15 MERCHNO
		// 订单编号 20 ORDERNO
		// 交易金额 17 TRANAMOUNT 1位符号+13位整数+1位小数点+2位小数
		// 交易币种 3 TRANCURRTYPE CNY人民币
		// 平台批次号 10 PAYBATNO 银行支付平台的批次号
		// 商户批次号 10 MERCHBATCHNO 为商家预留的对账字段
		// 交易日期 8 TRANDATE yyyymmdd
		// 交易时间 8 TRANTIME Hhmmss
		// 交易流水号 15 SERIALNO
		// 交易结果 1 TRANRST 1成功
		// 手续费总额 17 FEESUM
		// 银行卡类型 1 CARDTYPE 0 借记卡 1 准贷记卡 2 贷记卡 3 他行银联卡
		// 银行备注 100 BankMoNo
		// 错误信息描述 100 ErrDis
		// 用户IP 15 UserRemoteAddr 用户的IP地址（验证用）
		// 域名 50 Referer 跳转前的域名（验证用）
		// 银行签名 2000 SignMsg

		String MERCHNO = vc.get(0).toString();
		String ORDERNO = vc.get(1).toString();
		String TRANAMOUNT = vc.get(2).toString();
		String TRANCURRTYPE = vc.get(3).toString();
		String PAYBATNO = vc.get(4).toString();
		String MERCHBATCHNO = vc.get(5).toString();
		String TRANDATE = vc.get(6).toString();
		String TRANTIME = vc.get(7).toString();
		String SERIALNO = vc.get(8).toString();
		String TRANRST = vc.get(9).toString();
		String FEESUM = vc.get(10).toString();
		String CARDTYPE = vc.get(11).toString();
		String BankMoNo = vc.get(12).toString();
		String ErrDis = vc.get(13).toString();
		String UserRemoteAddr = vc.get(14).toString();
		String Referer = vc.get(15).toString();

		System.out.println("MERCHNO:" + MERCHNO);
		System.out.println("ORDERNO:" + ORDERNO);
		System.out.println("TRANAMOUNT:" + TRANAMOUNT);
		System.out.println("TRANCURRTYPE:" + TRANCURRTYPE);
		System.out.println("PAYBATNO:" + PAYBATNO);
		System.out.println("MERCHBATCHNO:" + MERCHBATCHNO);
		System.out.println("TRANDATE:" + TRANDATE);
		System.out.println("TRANTIME:" + TRANTIME);
		System.out.println("SERIALNO:" + SERIALNO);
		System.out.println("TRANRST:" + TRANRST);
		System.out.println("FEESUM:" + FEESUM);
		System.out.println("CARDTYPE:" + CARDTYPE);
		System.out.println("BankMoNo:" + BankMoNo);
		System.out.println("ErrDis:" + ErrDis);
		System.out.println("UserRemoteAddr:" + UserRemoteAddr);
		System.out.println("Referer:" + Referer);

		String SignMsg = vc.get(16).toString();
		int veriyCode = -1; // 验证签名结果

		BOCOMB2CClient client = new BOCOMB2CClient();
		String path = BOCOMPayServlet.class.getResource("B2CMerchant.xml").getPath();
		if (!path.startsWith("/"))
			path = path.substring(1);
		int ret = client.initialize(path); // 该代码只需调用一次
		if (ret != 0) { // 初始化失败
			resp.getWriter().println("初始化失败,错误信息：" + client.getLastErr());
			System.out.println("初始化失败,错误信息：" + client.getLastErr());
			resp.flushBuffer();
			return;
		}

		com.bocom.netpay.b2cAPI.NetSignServer nss = new com.bocom.netpay.b2cAPI.NetSignServer();
		nss.NSDetachedVerify(signMsg.getBytes("GBK"), srcMsg.getBytes("GBK"));
		nss.getLastErrnum();
		veriyCode = nss.getLastErrnum();
		if (veriyCode < 0) {// 验签出错
			System.out.print("商户端验证签名失败：return code:" + veriyCode);
			resp.getWriter().println("商户端验证签名失败：return code:" + veriyCode);
			return;
		}
		String notifyurl = (String) req.getSession().getAttribute("bocom" + ORDERNO);
		System.out.println("notifyurl:" + notifyurl);
		req.getSession().removeAttribute("bocom"+ORDERNO);
		// 将页面转向到提交的notifyurl
		resp.sendRedirect(notifyurl + "?orderid=" + ORDERNO + "&tranStat=" + TRANRST);

	}
}
