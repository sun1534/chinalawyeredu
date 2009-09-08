/**
 * 
 */
package com.changpeng.lawyers.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 华锋
 *
 */
public class CommonDatas {

	public static Map<Integer,String> LawyerType=new LinkedHashMap<Integer,String>();
	static{
		
//		律师合伙人2个都是实习律师等,0代表律师
		LawyerType.put(0, "执业律师");
		LawyerType.put(1, "实习律师");
		LawyerType.put(3, "合伙人");
		
		
	}
}
