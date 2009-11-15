/**
 * IndexPageAction.java
 */

package com.sxit.index;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import com.sxit.common.action.AbstractAction;
import com.sxit.netquality.service.BasicSetService;
import com.sxit.netquality.service.ZeroService;
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
		
		BasicSetService service=(BasicSetService)this.getBean("basicSetService");
		
		Date end=super.getPrevDate();
		Date start=super.getPrevCountDate(3);
		
		this.newaddapn=service.getTodayAddApn();
		this.newaddbsc=service.getTodayAddBsc();
		this.newaddcell=service.getTodayAddCell();
		this.totalapn=service.getTotalApns();
		this.totalbsc=service.getTotalBscs();
		this.totalcell=service.getTotalCells();
		
		
		com.sxit.stat.service.StatService statservice=(StatService)getBean("statService");
		totallist=statservice.getDaysTotalStream(start, end);
		total23glist=statservice.getDaysTotalStream23g(start, end);
		
		Date date=com.sxit.stat.util.StatUtil.getPrevDate();
		ZeroService zeroservice=(ZeroService)this.getBean("zeroService");
		
		this.exceptionapn=zeroservice.getZeroApns(date);
		this.exceptioncell=zeroservice.getZeroCells(date);
		
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
	private int zeroapn;
	private int zerocell;
	private int newaddcell;
	private int totalcell;
	private int newaddapn;
	private int totalapn;
	private int newaddbsc;
	private int totalbsc;
	
	private int exceptioncell;
	private int exceptionapn;
	private int exceptionbsc;

	/**
	 * @return the exceptioncell
	 */
	public int getExceptioncell() {
		return exceptioncell;
	}

	/**
	 * @return the exceptionapn
	 */
	public int getExceptionapn() {
		return exceptionapn;
	}

	/**
	 * @return the exceptionbsc
	 */
	public int getExceptionbsc() {
		return exceptionbsc;
	}

	/**
	 * @return the newaddcell
	 */
	public int getNewaddcell() {
		return newaddcell;
	}

	/**
	 * @return the totalcell
	 */
	public int getTotalcell() {
		return totalcell;
	}

	/**
	 * @return the newaddapn
	 */
	public int getNewaddapn() {
		return newaddapn;
	}

	/**
	 * @return the totalapn
	 */
	public int getTotalapn() {
		return totalapn;
	}

	/**
	 * @return the newaddbsc
	 */
	public int getNewaddbsc() {
		return newaddbsc;
	}

	/**
	 * @return the totalbsc
	 */
	public int getTotalbsc() {
		return totalbsc;
	}
	
}