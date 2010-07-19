/**
 * 
 */
package com.changpeng.lawyers.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.changpeng.common.BasicService;
import com.changpeng.common.context.Globals;
import com.changpeng.models.Lawyers;

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
	private static BasicService service = (BasicService) Globals.getBean("basicService");
	public static Lawyers getLawyersById(int lawyerid){
		try{
		return (Lawyers)service.get(Lawyers.class,lawyerid);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
