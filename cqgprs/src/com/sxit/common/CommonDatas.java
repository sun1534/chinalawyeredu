/**

 * CommonDatas.java
 */

package com.sxit.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sxit.models.mem.MemDevice;

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
	 * 存储用户已经登录的设备
	 * userid   List
	 */
	
	public static Map<String,HashMap<String, MemDevice>> LOGINDEVICE=new HashMap<String,HashMap<String, MemDevice>>();
	
/**
 * 存储所有的频道
 */
//	public static Map<Integer,String>  ALLCHANELS=new LinkedHashMap<Integer,String>();
    	
	static {

	
	}
}