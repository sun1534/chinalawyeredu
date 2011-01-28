/**
 * 
 */
package com.changpeng.forum.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 华锋
 *
 */
public class CommonDatas {

	public static Map<Integer,String> FORUMTYPE=new LinkedHashMap<Integer,String>();
	
	static{
//		意见和建议、积分申诉、补卡申请、产品使用咨询
		FORUMTYPE.put(1, "意见和建议");
		FORUMTYPE.put(2, "积分申诉");
		FORUMTYPE.put(3, "补卡申请");
		FORUMTYPE.put(4, "产品使用咨询");
	}
}