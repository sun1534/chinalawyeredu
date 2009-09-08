/**
 * CommonDatas.java
 */

package com.changpeng.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
	public static Map SysParameter=new HashMap();
	/**
	 * 存储课程id和课程名称，修改的时候做变动
	 */
	public static Map AllLessons =new HashMap();
	public static Map AllLawyers=new LinkedHashMap();
	public static Map<String,String> AllSystemNos=new LinkedHashMap<String,String>();
	static {

//		AllLawyers.put("shenzhen", "深圳律协");
//		AllLawyers.put("dongguan", "东莞律协");
//		AllLawyers.put("changchun", "长春律协");
//		AllLawyers.put("server", "课件平台");
//		AllLawyers.put("nanning", "南宁律协");
//		AllLawyers.put("dalian", "大连律协");
//		AllLawyers.put("changzhou", "常州律协");
//		AllLawyers.put("test", "中华全国律协");
	}
	
}