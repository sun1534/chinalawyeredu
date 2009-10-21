/**
 * IndexPageAction.java
 */

package com.sxit.index;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import com.sxit.common.action.AbstractAction;
import com.sxit.stat.service.StatService;

/**
 * 
 * 
 * 
 * @author 华锋 2008-4-22 下午06:02:52
 * 2009-3-6 Tompan 修改加上获取用户信息
 * 
 */
public class MyWorkspacePageAction extends AbstractAction {

	private static final DateFormat dftime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat dfdate = new java.text.SimpleDateFormat("yyyy-MM-dd");

	public MyWorkspacePageAction() {
		
	}

	@Override
	protected String go()throws Exception
	{
		
		Date end=super.getPrevDate();
		Date start=super.getPrevCountDate(3);
		
		
		
		
		com.sxit.stat.service.StatService statservice=(StatService)getBean("statService");
		totallist=statservice.getDaysTotalStream(start, end);
		total23glist=statservice.getDaysTotalStream23g(start, end);
		
		return SUCCESS;
	}
	
	private List totallist;
	private List total23glist;

	/**
	 * @return the totallist
	 */
	public List getTotallist() {
		return totallist;
	}

	/**
	 * @return the total23glist
	 */
	public List getTotal23glist() {
		return total23glist;
	}
	
	
}