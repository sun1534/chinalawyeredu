/**
 * CommonDatas.java
 */

package com.changpeng.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.changpeng.models.SysUnionparams;

/**
 * 公共数据,以后的这些数据考虑都放在一个大的xml文件里面，系统启动的时候对其进行读取并初始化下述的各个map表
 * 
 * @author 华锋 2008-3-10 下午01:50:30
 * 
 */
public class CommonDatas {

	/**
	 * 存储所有的参数信息
	 */
	public static Map<String,String> SysParameter=new HashMap<String,String>();
	/**
	 * key是url，value是对应的对象
	 */
	public static Map<String,SysUnionparams> SysUnionparams=new HashMap<String,SysUnionparams>();
	
	// 存储areaid和上级areaid的数据
	public static Map<Integer,Integer> AreasIdParent = new LinkedHashMap<Integer,Integer>();
	// 存储areaid和areaname的数据
	public static Map<Integer,String> AreasIdName = new LinkedHashMap<Integer,String>();
	
	public static Map<Integer,String> ForumType=new LinkedHashMap<Integer,String>();
	
	/**
	 * 
	 */
	public static Map<String,String> AllSystemNos=new LinkedHashMap<String,String>();

	static{
//		意见和建议、积分申诉、补卡申请、产品使用咨询
		ForumType.put(1, "意见和建议");
		ForumType.put(2, "积分申诉");
		ForumType.put(3, "补卡申请");
		ForumType.put(4, "产品使用咨询");
	}
}