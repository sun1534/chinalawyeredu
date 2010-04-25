package com.icbc.common;
import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;


import cn.com.infosec.icbc.ReturnValue;

public class B2CUtil {
	private  static String B2CPRIPWD; //商户私钥保护口令	
	
	private static byte[] bkey=null; //私钥
	public static String merCert=""; //公钥base64编码
	
	private static String B2CMERID; //B2C商城代码
	private static String MERACCT;	//银行账号
	private static String B2CMERURL; //B2C通知地址
	
	static{
		try{
			Properties p=new Properties();
			p.load(B2CUtil.class.getResourceAsStream("icbc.properties"));			
			B2CPRIPWD=p.getProperty("B2CPRIPWD");			
			B2CMERID=p.getProperty("B2CMERID");	
			MERACCT=p.getProperty("MERACCT");	
			B2CMERURL=p.getProperty("B2CMERURL");	
			
			FileInputStream in = new FileInputStream(p.getProperty("B2CUSERKEY"));
			bkey = new byte[in.available()];
			in.read(bkey);
			in.close();			
			in = new FileInputStream(p.getProperty("B2CUSERCRT"));
			byte[] bcert = new byte[in.available()];
			in.read(bcert);
			in.close();
			merCert=new String(ReturnValue.base64enc(bcert)); //公钥base64编码
		}catch(IOException e){
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	
	
	private static long traceid;
	public static String getTraceid(){
		traceid++;
		if(traceid>1000000)
			traceid=0;
		return Common.addLeftZero(traceid+"", 8);
	}
	
	//组织交易数据 明文
	public String b2cTranData(String orderid,String amount,String notifyurl){
		String b2cTrandata="<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"no\"?>";
		b2cTrandata+="<B2CReq>";
		b2cTrandata+="<interfaceName>ICBC_PERBANK_B2C</interfaceName>";
		b2cTrandata+="<interfaceVersion>1.0.0.7</interfaceVersion>";
		b2cTrandata+="<orderInfo>";
		b2cTrandata+="<orderDate>"+new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date())+"</orderDate>";
		b2cTrandata+="<curType>001</curType>";
		b2cTrandata+="<merID>"+B2CMERID+"</merID>"; //商户代码
		b2cTrandata+="<subOrderInfoList>";
		b2cTrandata+="<subOrderInfo>";
		b2cTrandata+="<orderid>"+getTraceid()+"</orderid>";
		b2cTrandata+="<amount>"+amount+"</amount>"; //分
		b2cTrandata+="<installmentTimes>1</installmentTimes>";
		b2cTrandata+="<merAcct>"+MERACCT+"</merAcct>";
		b2cTrandata+="<goodsID></goodsID>";
		b2cTrandata+="<goodsName></goodsName>";
		b2cTrandata+="<goodsNum></goodsNum>";
		b2cTrandata+="<carriageAmt></carriageAmt>";
		b2cTrandata+="</subOrderInfo>";
		b2cTrandata+="</subOrderInfoList>";
		b2cTrandata+="</orderInfo>";
		b2cTrandata+="<custom>";
		b2cTrandata+="<verifyJoinFlag>0</verifyJoinFlag>";
		b2cTrandata+="<Language>ZH_CN</Language>";
		b2cTrandata+="</custom>";
		b2cTrandata+="<message>";
		b2cTrandata+="<merHint></merHint>";
		b2cTrandata+="<remark1></remark1>";
		b2cTrandata+="<remark2></remark2>";
		b2cTrandata+="<merURL>"+B2CMERURL+"</merURL>";
		b2cTrandata+="<merVAR>"+orderid+"##"+notifyurl+"</merVAR>";
		b2cTrandata+="</message>";
		b2cTrandata+="</B2CReq>";
		return b2cTrandata;
	}
	private static boolean isEmpty(String str){
		if(str==null||str.trim().equals(""))
			return true;
		else
			return false;
	}
	
	private boolean error; //错误提示信息
	private String message;
	private String b2cTrandata=""; //交易数据
	private String merSignMsg=""; //订单签名数据
	public boolean isError() {
		return error;
	}
	public String getMessage(){
		return message;
	}
	public String getMerSignMsg(){
		return merSignMsg;
	}
	public String getB2cTrandata() {
		return b2cTrandata;
	}
	public void b2cFormData(HttpServletRequest req){
		try{
			validB2CRequest(req);
			if(!error){
				String trandata=b2cTranData(req.getParameter("orderid"),req.getParameter("amount"),req.getParameter("notifyurl"));
				//System.out.println(trandata);
				b2cTrandata=new String(ReturnValue.base64enc(trandata.getBytes()));
				byte[] databytes=trandata.getBytes();
				byte[] sign=ReturnValue.sign(databytes,databytes.length,bkey,B2CPRIPWD.toCharArray()); //交易数据签名
				merSignMsg=new String(ReturnValue.base64enc(sign)); //订单签名数据base64编码
				
			}
		}catch(Exception e){
			System.out.println(e);
			message="系统忙，请稍后再试";
			error=true;
		}
	}
	/**
	 * 验证请求参数
	 * @param req
	 * @return
	 */
	private void validB2CRequest(HttpServletRequest req){
		String orderid=req.getParameter("orderid");
		String amount=req.getParameter("amount");
		String merVAR=req.getParameter("notifyurl");
		double _amount=0;
		if(isEmpty(orderid)){
			message="订单号不能为空";
			error=true;
		}
		else if(isEmpty(amount)){
			message="订单金额不能为空";
			error=true;
		}
		else if(isEmpty(merVAR)){
			message="通知地址不能为空";
			error=true;
		}
		try{
			_amount=Double.parseDouble(amount);
			if(_amount<=0){
				message="订单金额不得小于0";
				error=true;
			}
		}catch(Exception e){
			message="订单金额必须为数字";
			error=true;
		}
	}
}
