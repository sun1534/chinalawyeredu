/**
 * JSONExample.java
 */

package com.changpeng.system.action.ajax;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.changpeng.common.action.AbstractListAction;

/**
 * 
 * ajax形式的数据,不必进行权限处理等等
 * 
 * @author 华锋 2008-3-11 下午11:10:44
 * 
 */
public class JSONExample extends AbstractListAction {

	// private SysLoginLog log;
	//	
	// public SysLoginLog getLog(){
	// return this.log;
	// }
	private List list;

	public List getList() {
		return this.list;
	}

	// private Map map;
	// public Map getMap(){
	// return this.map;
	// }

	@Override
	public String go() throws Exception {
		list = new ArrayList();
		// map=new HashMap();



		//		
		// map.put("11", "aaaaaa");
		// map.put("22", "bbbbbb");
		// map.put("33", "cccccc");
		// map.put("44", "dddddd");
		// SysLoginLogService service=(SysLoginLogService)getBean("sysLoginLogService");
		//		
		//		
		// DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysLoginLog.class);
		// this.page=service.findPageByCriteria(detachedCriteria, 30, 1);
		//		
		// log=(SysLoginLog)page.getItems().get(0);
		//		
		// System.out.println("=============log+++++"+log.getLoginip());

		return SUCCESS;

	}
}
