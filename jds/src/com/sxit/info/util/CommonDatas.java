/**
 * 
 */
package com.sxit.info.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sg
 *
 */
public class CommonDatas {
	
	/**
	 * 存储对应的状态Html
	 */
	public static Map StatusWeb;
	
	/**
	 * 存储对应的状态
	 */
	public static Map Status;
	
	/**
	 * 权限列表
	 */
	public static Map Power;
	
	static{
		
		StatusWeb = new HashMap();
		StatusWeb.put(1, "<font color=\"#F09900\">草稿</font>");
		StatusWeb.put(2, "<font color=\"#0000FF\">待批</font>");
		StatusWeb.put(3, "<font color=\"#400080\">通过审批</font>");
		StatusWeb.put(4, "<font color=\"#FF0080\">未通过审批</font>");
		StatusWeb.put(0, "<font color=\"#009933\">发布</font>");
		StatusWeb.put(-1, "<font color=\"#FF0000\">冻结</font>");
		
		Status = new HashMap();
		Status.put(1, "草稿");
		Status.put(2, "待批");
		Status.put(3, "通过审批");
		Status.put(4, "未通过审批");
		Status.put(0, "发布");
		Status.put(-1, "冻结");
		

		Power = new HashMap();
		Power.put(1, "审批");
		Power.put(2, "发布");
		Power.put(4, "新建");
		Power.put(8, "编辑");
		Power.put(16, "删除");
		Power.put(32, "查看");
	}

}
