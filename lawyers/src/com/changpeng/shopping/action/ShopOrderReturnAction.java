package com.changpeng.shopping.action;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.changpeng.shopping.util.PaymentBean;
import com.changpeng.shopping.util.Config;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Lawyers;
import com.changpeng.models.Lessons;
import com.changpeng.models.ShopCart;
import com.changpeng.models.ShopOrder;
import com.changpeng.shopping.util.NumberUtil;
import com.opensymphony.xwork2.ActionContext;

public class ShopOrderReturnAction extends AbstractAction{
	private static final String KEY_CHINAPAY_PUBKEY_FILEPATH = "chinapay.pubkey.filepath";
	public ShopOrderReturnAction(){
		shopOrder=new ShopOrder();
	}	
	public String getTopbarpic(){
		return super.webpara.getTopbarpic();
	}
	
	private ShopOrder shopOrder;
	
	private List<Integer> chkcartid = new ArrayList<Integer>();

	public List<Integer> getChkcartid() {
		return chkcartid;
	}

	public void setChkcartid(List<Integer> chkcartid) {
		this.chkcartid = chkcartid;
	}
	
	private String orderno;
	
	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	protected String go() throws Exception {
		
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
		
		
		System.out.println("CCCCCCCCCCCCCCC");
		List errorList = new ArrayList();
		String PubKeyPath = null;
		try {
			Properties config = Config.getInstance().getProperties();
			PubKeyPath = config.getProperty(KEY_CHINAPAY_PUBKEY_FILEPATH);
		} catch (Exception e) {
			System.out.println("errors_properties_init_failed");
			errorList.add("errors_properties_init_failed");
		}
		if (!errorList.isEmpty()) {
			request.setAttribute("errors", errorList);
			//request.getrequestuestDispatcher(PAGE_RETURN_JSP).forward(request, response);
			return null;
		}
		System.out.println("<====Receive PageReturnData Start!");
		//支付订单数据准备
		String Version = request.getParameter("version");
		String MerId = request.getParameter("merid");
		String OrdId = request.getParameter("orderno");
		String TransAmt = request.getParameter("amount");// 12
		String CuryId = request.getParameter("currencycode");// 3
		String TransDate = request.getParameter("transdate");// 8
		String TransType = request.getParameter("transtype");// 4
		String Status = request.getParameter("status");
		String BgRetUrl = request.getParameter("BgRetUrl");
		String PageRetUrl = request.getParameter("PageRetUrl");
		String GateId = request.getParameter("GateId");
		String Priv1 = request.getParameter("Priv1");
		/*
		try {
			Priv1 = Base64Util.base64Decoder(Priv1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		String ChkValue = request.getParameter("checkvalue");
		
		System.out.println(MerId+OrdId+TransAmt+CuryId+TransDate+TransType+Status+ChkValue);

		//境外版本还需使用以下5个数据，境内版本不需要
		/*
		String TransTime = request.getParameter("TransTime");
		String CountryId = request.getParameter("CountryId");
		String TimeZone = request.getParameter("TimeZone");
		String DSTFlag = "1";
		String ExtFlag = "00";
		*/

		boolean buildOK = false;
		boolean res = false;
		int KeyUsage = 0;
		PrivateKey key = new PrivateKey();
		try {
			buildOK = key.buildKey("999999999999999", KeyUsage, PubKeyPath);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		if (!buildOK) {
			System.out.println("build error!");
			errorList.add("build error!");
		}
		if (!errorList.isEmpty()) {
			request.setAttribute("errors", errorList);
			//request.getrequestuestDispatcher(PAGE_RETURN_JSP).forward(request, response);
			return null;
		}
		
		try {
			SecureLink sl = new SecureLink(key);
			res=sl.verifyTransResponse(MerId, OrdId, TransAmt, CuryId, TransDate, TransType, Status, ChkValue);
			//.verifyTransresponseonse(MerId, OrdId, TransAmt, CuryId, TransDate, TransType, Status, ChkValue);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			errorList.add(e.getMessage());
		}
		if (!errorList.isEmpty()) {
			request.setAttribute("errors", errorList);
			//request.getrequestuestDispatcher(PAGE_RETURN_JSP).forward(request, response);
			return null;
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
		
		//境外版本还需使用以下5个数据，境内版本不需要
		/*
		pay.setTransTime(TransTime);
		pay.setCountryId(CountryId);
		pay.setDSTFlag(DSTFlag);
		pay.setExtFlag(ExtFlag);
		pay.setTimeZone(TimeZone);
		*/
		
		
		BasicService basicService = (BasicService) getBean("basicService");
		if (res){
			
			System.out.println("PageReturn Check OK!");
			request.setAttribute("payResult", pay);
//			shopOrder.setOrderno(orderno);
//			//shopOrder.setLawyerid(lawyerid);
//			shopOrder.setState(2);
//			//shopOrder.setTotalmoney(totalmoney);
//			//shopOrder.setCreatedate(new java.sql.Timestamp(System.currentTimeMillis()));
//			shopOrder.setTradedate(new java.sql.Timestamp(System.currentTimeMillis()));
//			basicService.update(shopOrder);
			
			Timestamp tradedate=new java.sql.Timestamp(System.currentTimeMillis());
			//更新订单表。
			String updateOrder="update ShopOrder set state=2,tradedate='"+tradedate+"' where orderno="+orderno+" ";
			basicService.execute(updateOrder);
			//更新购物车的课件
			String updateCart="update ShopCart set state=2 where ordernoid="+orderno+" ";
			basicService.execute(updateCart);		
			//更新收藏夹的课件
			basicService.execute("update ShopFavorites set state=2  where ordernoid="+orderno+"  ");
			System.out.println("更新成功");
		}
		//String url="shopping/shopOrderReturn.pl?orderno="+OrdId+"&Status="+Status+"";
		//System.out.println(url);
		//request.getrequestuestDispatcher(url).forward(request, response);
		//request.getrequestuestDispatcher(PAGE_RETURN_JSP).forward(request, response);
		System.out.println("Receive PageReturnData End!====>");
		
		
//		System.out.println(chkcartid);
//		BasicService basicService = (BasicService) getBean("basicService");
//		
//		//1、生成订单
//		int lawyerid = this.getLoginUser().getLawyerid();
//		orderno=NumberUtil.RandomOrderno();
//		
//		
//		String ss="";
//		Integer[] st=new Integer[chkcartid.size()];
//
//		for(int i=0;chkcartid != null && i < chkcartid.size(); i++){
//			System.out.println(chkcartid.get(i).toString());
//			st[i]=Integer.parseInt(chkcartid.get(i).toString()); 
//
//			
//			ss+=chkcartid.get(i).toString();
//			if(i!=(chkcartid.size()-1)){
//				ss+=",";
//			}			
//		}
//		System.out.println(st);
//
//		
//		String hql="select sum(price)  from ShopCart where lawyerid="+lawyerid+" and cartid in ("+ss+") ";
//		System.out.println(hql);		
//		String totalprice=String.valueOf(basicService.getSumByQuery(hql));
//		System.out.println(totalprice);
//		float totalmoney=0;
//		if(totalprice!="" && totalprice!=null){
//			totalmoney=Float.valueOf(totalprice);
//		}
//		System.out.println(totalmoney);

		

		
		
//		DetachedCriteria detachedCriteria =  DetachedCriteria.forClass(ShopCart.class);
//		if(lawyerid!=0){
//			detachedCriteria.add(Restrictions.eq("lawyerid", lawyerid));
//		}
//		detachedCriteria.add(Restrictions.in("cartid", st));
//		
//		detachedCriteria.addOrder(Order.desc("createtime"));
//		this.page = basicService.findPageByCriteria(detachedCriteria, Integer.MAX_VALUE, pageNo);
		
		
		//保存订单		
		
//		shopOrder.setOrderno(orderno);
//		//shopOrder.setLawyerid(lawyerid);
//		shopOrder.setState(2);
//		//shopOrder.setTotalmoney(totalmoney);
//		//shopOrder.setCreatedate(new java.sql.Timestamp(System.currentTimeMillis()));
//		shopOrder.setTradedate(new java.sql.Timestamp(System.currentTimeMillis()));
//		basicService.update(shopOrder);
//		
//		String updateCart="update ShopCart set state=2 where orderno="+orderno+" ";
//		basicService.execute(hql);
		//ShopCart shopCart=(ShopCart) basicService.get(ShopCart.class, Integer.parseInt(chkcartid.get(i).toString()));
		
//		for(int i=0;chkcartid != null && i < chkcartid.size(); i++){
//			ShopCart shopCart=(ShopCart) basicService.get(ShopCart.class, Integer.parseInt(chkcartid.get(i).toString()));
//			shopCart.setCartid(Integer.parseInt(chkcartid.get(i).toString()));
//			shopCart.setState(1);
//			shopCart.setOrderno(orderno);
//			basicService.update(shopCart);
//		}
		System.out.println(orderno);
		this.nextPage = "shopOrderCartList.pl?orderno="+ orderno;
		System.out.println("添加成功了；；；；；；；；");
		return SUCCESS;
	}

	public ShopOrder getShopOrder() {
		return shopOrder;
	}

	public void setShopOrder(ShopOrder shopOrder) {
		this.shopOrder = shopOrder;
	}
	
	//private ShopCart shopOrder;

}
