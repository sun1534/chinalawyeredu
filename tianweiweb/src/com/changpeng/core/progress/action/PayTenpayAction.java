package com.changpeng.core.progress.action;

import org.apache.struts2.ServletActionContext;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.core.model.CoreProduct;
import com.changpeng.core.model.CorePublish;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.core.user.model.CoreUserDetail;
import com.tenpay.PayRequestHandler;
import com.tenpay.util.TenpayUtil;


/**
 * 商家ID:1205090201
 * 
 * @author 华锋
 * Sep 23, 2009 10:12:37 PM
 *
 */
public class PayTenpayAction extends AbstractListAction {

	private static final String SHOP_ID="1205090201";
//	private static final String SHOP_ID="1900000109";
	//密钥
//	private static final String KEY = "8934e7d15453e97507ef794cf7b0519d";
	
	private static final String KEY = "631c3aaffdcebd88e7a19ac1e756c770";
	private static final String RETURN_URL = "http://211.148.192.252/progress/payresult.action";
	private int id;

	
	public PayTenpayAction() {
		this.rightCode = "PUBLIC";
	}

	@Override
	protected String go() throws Exception {
		CorePublish publish=(CorePublish)service.get(CorePublish.class, id);
		CoreUser user=(CoreUser)service.get(CoreUser.class, this.currentUserid);
		CoreUserDetail userdetail=(CoreUserDetail)service.get(CoreUserDetail.class, this.currentUserid);
		 // 定义必须传递的参数变量

	String bargainor_id=SHOP_ID;
		//当前时间 yyyyMMddHHmmss
		String currTime = TenpayUtil.getCurrTime();
		//8位日期
		String strDate = currTime.substring(0, 8);
		//6位时间
		String strTime = currTime.substring(8, currTime.length());
		//四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		//10位序列号,可以自行调整。
		String strReq = strTime + strRandom;
		//商家订单号,长度若超过32位，取前32位。财付通只记录商家订单号，不保证唯一。
		String sp_billno = strReq;
		//财付通交易单号，规则为：10位商户号+8位时间（YYYYmmdd)+10位流水号
		String transaction_id = bargainor_id + strDate + strReq;

//		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd-" + v_mid
//				+ "-hhmmss", Locale.US);
		if(publish.getOrderno()==null||publish.getOrderno().equals("")){
		
			publish.setOrderno(sp_billno);
			service.update(publish);
		}else{
			sp_billno=publish.getOrderno();
		}
		
		
	String	fee = publish.getFee()+""; // 订单金额
	
	fee="1"; //测试用1分钱
	
//		String feetype = "1"; // 币种 1是人民币
	
		//创建PayRequestHandler实例
		PayRequestHandler reqHandler = new PayRequestHandler( ServletActionContext.getRequest(),  ServletActionContext.getResponse());

		//设置密钥
		reqHandler.setKey(KEY);

		//初始化
		reqHandler.init();

		//-----------------------------
		//设置支付参数
		//-----------------------------
		
	int productid=	publish.getProductid();
	CoreProduct product=(CoreProduct)service.get(CoreProduct.class, productid);
	String name=product.getName();
	if(name.length()>11)
		name=name.substring(0,11);
	reqHandler.setParameter("cmdno", 1+"");
		reqHandler.setParameter("bargainor_id", bargainor_id);			//商户号
		reqHandler.setParameter("sp_billno", sp_billno);				//商家订单号
		reqHandler.setParameter("transaction_id", transaction_id);		//财付通交易单号
		reqHandler.setParameter("return_url", RETURN_URL);				//支付通知url
		reqHandler.setParameter("desc", name);	//商品名称
		reqHandler.setParameter("total_fee", fee);		
		reqHandler.setParameter("attach", id+"");	//商品金额,以分为单位

		//用户ip,测试环境时不要加这个ip参数，正式环境再加此参数
		reqHandler.setParameter("spbill_create_ip", ServletActionContext.getRequest().getRemoteAddr());

		//获取请求带参数的url
		 requestUrl = reqHandler.getRequestURL();

		//获取debug信息
		String debuginfo = reqHandler.getDebugInfo();

		System.out.println("requestUrl:" + requestUrl);
		System.out.println("debuginfo:" + debuginfo);

		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String requestUrl;
	public String getRequestUrl(){
		return this.requestUrl;
	}
}
