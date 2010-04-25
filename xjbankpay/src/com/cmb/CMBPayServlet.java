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
public class CMBPayServlet extends HttpServlet {
	public void init()

	{

	}

	private DateFormat df=new java.text.SimpleDateFormat("yyyyMMdd");
	/**
	 * 
	 * 
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();

		String orderId = req.getParameter("orderid");//订单号
		String money = req.getParameter("amount");//金额,以元为单位
		String notifyUrl = req.getParameter("notifyurl");//收到招行返回的url后，将参数送给notifyUrl
		
//		参数	说明	
//		Key	商户密钥	
//		StrDate	订单日期	
//		strBranchID	开户分行号	
//		StrCono	商户号	
//		StrBillNo	订单号	
//		strAmount	订单金额	
//		strMerchantPara	商户自定义参数	
//		strMerchantUrl	商户接受通知的URL	
//		strPayerUserID	付款方用户标识。用来唯一标识商户的一个用户。长度限制为40字符以内。
//		并不要求商户提供用户的注册名称，但需要保证一个用户对应一个UserID。
//		商户可以通过某些转换，把用户名转换为一个UserID。比如商户可以把用户注册的“日期+时分秒毫秒”作为UserID。如果还有重复，可再加上用户的IP。
//		空白表示匿名用户。	
		
     
       String strCono=CMBDatas.CONO;
        String key=CMBDatas.KEYVALUE;
        String strDate=df.format(new Date());
        String strBranchID=CMBDatas.BRANCHID;
        String strBillNo=orderId;
        String strAmount=com.bankpay.util.NumberUtil.toMoney(Float.parseFloat(money)/100);
        String strMerchantPara=notifyUrl;
        String strMerchantUrl=CMBDatas.NOTIFYURL;
        String strPayerUserID="";
        String strPayeeUserCode="";
        //
        MerchantCode mc=new MerchantCode();
        
        System.out.println(key);
        
        
//        String genMerchantCode(String key,String strDate,String strBranchID,String strCono, String strBillNo,String strAmount,String strMerchantPara,String strMerchantUrl,String strPayerUserCode,String StrPayeeUserCode)
        String strVerifyCode=mc.genMerchantCode(key, strDate, strBranchID, strCono,strBillNo, strAmount, strMerchantPara, strMerchantUrl, strPayerUserID,strPayeeUserCode);
        
        
        System.out.println("产生的校验码为：" + strVerifyCode);
        System.out.println("strMerchantPara：" + strMerchantPara);
        System.out.println("strMerchantUrl：" + strMerchantUrl);
        req.setAttribute("BillNo", strBillNo);
        req.setAttribute("Amount", strAmount);
        req.setAttribute("Date", strDate);
        req.setAttribute("MerchantPara", strMerchantPara);
        req.setAttribute("MerchantCode", strVerifyCode);
        
    	javax.servlet.RequestDispatcher rd = getServletContext().getRequestDispatcher("/cmb/pay2cmb.jsp");
		rd.forward(req, resp);
        
		
	}
}
