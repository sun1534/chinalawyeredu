/**
 * APIQueryStat.java
 */
package com.icbc;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 华锋
 * May 31, 20105:00:23 PM
 *
 */
public class ApiQueryStat {

	public static Map<String,String> QUERY_RESULT=new LinkedHashMap<String,String>();
	public static Map<String,String> PAY_RESULT=new LinkedHashMap<String,String>();
	
	static{
		
//		0-支付成功，未清算
//		1-支付成功，已清算
//		2-支付失败
//		3-支付可疑交易
		
		PAY_RESULT.put("0", "支付成功，未清算");
		PAY_RESULT.put("1", "支付成功，已清算");
		PAY_RESULT.put("2", "支付失败");
		PAY_RESULT.put("3", "支付可疑交易");
		
		
//		40972	API查询的订单不存在
//		40973	API查询过程中系统异常
//		40976	API查询系统异常
//		40977	商户证书信息错
//		40978	解包商户请求数据报错
//		40979	查询的订单不存在
//		40980	API查询过程中系统异常
//		40981	给商户打包返回数据错
//		40982	系统错误
//		40983	查询的订单不唯一
//		40987	请求数据中接口名错误
//		40947	商户代码或者商城账号有误
//		40948	商城状态非法
//		40949	商城类别非法
//		40950	商城应用类别非法
//		40951	商户证书id状态非法
//		40952	商户证书id未绑定
//		40953	商户id权限非法
//		40954	检查商户状态时数据库异常
		
		
	    QUERY_RESULT.put("40972","API查询的订单不存在");     
		QUERY_RESULT.put("40973","API查询过程中系统异常");   
		QUERY_RESULT.put("40976","API查询系统异常");         
		QUERY_RESULT.put("40977","商户证书信息错");          
		QUERY_RESULT.put("40978","解包商户请求数据报错");    
		QUERY_RESULT.put("40979","查询的订单不存在");        
		QUERY_RESULT.put("40980","API查询过程中系统异常");   
		QUERY_RESULT.put("40981","给商户打包返回数据错");    
		QUERY_RESULT.put("40982","系统错误");                
		QUERY_RESULT.put("40983","查询的订单不唯一");        
		QUERY_RESULT.put("40987","请求数据中接口名错误");    
		QUERY_RESULT.put("40947","商户代码或者商城账号有误");
		QUERY_RESULT.put("40948","商城状态非法");            
		QUERY_RESULT.put("40949","商城类别非法");            
		QUERY_RESULT.put("40950","商城应用类别非法");        
		QUERY_RESULT.put("40951","商户证书id状态非法");      
		QUERY_RESULT.put("40952","商户证书id未绑定");        
		QUERY_RESULT.put("40953","商户id权限非法");          
		QUERY_RESULT.put("40954","检查商户状态时数据库异常");

		
	}
	
}
