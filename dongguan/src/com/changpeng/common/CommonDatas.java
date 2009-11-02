/**
 * CommonDatas.java
 */

package com.changpeng.common;

import java.util.HashMap;
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
	
	static {

	
	}
	
	
}