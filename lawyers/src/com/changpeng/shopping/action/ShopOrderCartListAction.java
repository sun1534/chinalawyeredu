package com.changpeng.shopping.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import chinapay.PrivateKey;
import chinapay.SecureLink;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.lawyers.service.SysGroupService;
import com.changpeng.models.Articles;
import com.changpeng.models.Lawyers;
import com.changpeng.models.ShopCart;
import com.changpeng.shopping.util.Config;
import com.changpeng.shopping.util.PaymentBean;
import com.opensymphony.xwork2.ActionContext;

public class ShopOrderCartListAction extends AbstractListAction{
	
	private static final String KEY_CHINAPAY_MERID = "chinapay.merid";
	private static final String KEY_CHINAPAY_MERKEY_FILEPATH = "chinapay.merkey.filepath";
	private static final String PAYMENT_URL = "chinapay.payment.url";
	private static final String VERSION_INPUT_JSP = "/template/versionInput.jsp";
	private static final String CREATE_ORDER_INPUT_JSP = "/template/createOrderInput.jsp";
	private static final String CREATE_ORDER_COMMIT_JSP = "/shopping/paymentcommit.jsp";
	public String getTopbarpic(){
		return super.webpara.getTopbarpic();
	}
	public ShopOrderCartListAction() {
		
	}
	
	private Lawyers lawyer;
	public Lawyers getLawyer() {
		return lawyer;
	}
	
	private String orderno;
	
	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	
	
	
	protected String go() throws Exception {

		this.lawyer = this.getLoginUser();
		System.out.println(orderno);
		int lawyerid = this.getLoginUser().getLawyerid();
		BasicService basicService = (BasicService) getBean("basicService");
		DetachedCriteria detachedCriteria =  DetachedCriteria.forClass(ShopCart.class);
		if(lawyerid!=0){
			detachedCriteria.add(Restrictions.eq("lawyerid", lawyerid));
		}
		detachedCriteria.add(Restrictions.eq("ordernoid", orderno));
		detachedCriteria.add(Restrictions.eq("state", 1));
		detachedCriteria.addOrder(Order.desc("createtime"));
		this.page = basicService.findPageByCriteria(detachedCriteria, Integer.MAX_VALUE, pageNo);
		System.out.println(page.getTotalCount());
		
		//查询出多少金额
		String hql="select sum(price)  from ShopCart where  ordernoid='"+orderno+"' ";
		System.out.println(hql);		
		String totalprice=String.valueOf(basicService.getSumByQuery(hql));
		System.out.println(totalprice);
		float totalmoney=0;
		if(totalprice!="" && totalprice!=null){
			totalmoney=Float.valueOf(totalprice);
		}
		System.out.println(totalmoney);
		
		//取得今天的日期
		Date dt = new Date();
		//交易日期
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMMdd");
		TransDate = sf2.format(dt);
		//交易金额
		//TransAmt = "000000000001";
		
		//验证支付信息
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest req = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse resp = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
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
			//return;
		}
		
		// 支付订单数据准备
		String MerId = "808080011391913"; //商户号，固定是长鹏公司的
		String OrdId = orderno;  //交易订单号
		//String Version = req.getParameter("Version");
		String Version = "20070129";//版本号
		//String TransAmt = req.getParameter("TransAmt");// 12
		String TransAmt = "000000000001";// 交易金额。12位数字
		String CuryId = "156";// 支付币种。156默认为人民币
		TransDate = TransDate;// 交易日期，默认为今天
		String TransType = "0001";// 交易类型。4位数字，支付交易为0001
		String BgRetUrl = "http://131.252.83.73:8080/chinapay_java/getBgReturn";//后台应答接收URL,商户系统后台应答接受地址
		String PageRetUrl ="http://cpsoft.3322.org:8082//lawyerNew/shopping/shopOrderReturn.pl";//页面应答接收URL
		String GateId ="";
		String Priv1 ="";
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
			//req.getRequestDispatcher(CREATE_ORDER_COMMIT_JSP).forward(req, resp);
			return "";
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
			//return;
		}
		PaymentBean pay = new PaymentBean();
		pay.setMerId(MerId);
		pay.setOrdId(OrdId);
		pay.setTransAmt("000000000001");
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
		
		
		return SUCCESS;
	}

	private String TransDate;
	public String getTransDate() {
		return TransDate;
	}

	public void setTransDate(String transDate) {
		TransDate = transDate;
	}
	private String TransAmt;

	public String getTransAmt() {
		return TransAmt;
	}

	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}
	
}
