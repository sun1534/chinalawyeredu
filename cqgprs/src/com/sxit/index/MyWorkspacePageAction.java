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
 * @author 华锋 2008-4-22 下午06:02:52 2009-3-6 Tompan 修改加上获取用户信息
 * 
 */
public class MyWorkspacePageAction extends AbstractAction {

	private static final DateFormat dftime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat dfdate = new java.text.SimpleDateFormat("yyyy-MM-dd");

	

	public MyWorkspacePageAction() {

	}

	
	@Override
	protected String go() throws Exception {

		BasicSetService service = (BasicSetService) this.getBean("basicSetService");

//		Date end = super.getPrevDate();
//		Date start = super.getPrevCountDate(3);
//		String today = dfdate.format(new java.util.Date());

		this.newaddapn = service.getTodayAddApn();
		this.newaddbsc = service.getTodayAddBsc();
		this.newaddcell = service.getTodayAddCell();
		this.totalapn = service.getTotalApns();
		this.totalcqapn=service.getTotalCqApns();
		this.totalbsc = service.getTotalBscs();
		this.totalcell = service.getTotalCells();
		this.totallink = service.getTotalLinks();
		this.newlink = service.getTodayAddLink();

//		if (cache == null || (cache != null && !cache.getKeydate().equals(today))) {
//			synchronized (this) {
//				com.sxit.stat.service.StatService statservice = (StatService) getBean("statService");
//				totallist = statservice.getDaysTotalStream(start, end);
//				total23glist = statservice.getDaysTotalStream23g(start, end);
//				Date date = com.sxit.stat.util.StatUtil.getPrevDate();
//				ZeroService zeroservice = (ZeroService) this.getBean("zeroService");
//
//				this.exceptionapn = zeroservice.getZeroApns(date);
//				this.exceptioncell = zeroservice.getZeroCells(date);
//
//		   	List	streamlist = statservice.getDaysTotalStream(start, end);
//				
//				cache = new IndexCache();
//				cache.setExceptionapn(exceptionapn);
//				cache.setExceptioncell(exceptioncell);
//				cache.setKeydate(today);
//				cache.setTotal23glist(total23glist);
//				cache.setTotallist(totallist);
//				cache.setStreamlist(streamlist);
//				cache.setKeydate(today);
//			}
//		} else {
			this.exceptionapn = com.sxit.index.IndexPageAction.cache.getExceptionapn();
			this.exceptioncell = com.sxit.index.IndexPageAction.cache.getExceptioncell();
			this.total23glist = com.sxit.index.IndexPageAction.cache.getTotal23glist();
			this.totallist = com.sxit.index.IndexPageAction.cache.getTotallist();
//		}

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
private int totalcqapn;
	private int newaddcell;
	private int totalcell;
	private int newaddapn;
	private int totalapn;
	private int newaddbsc;
	private int totalbsc;
	private int newlink;
	private int totallink;

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

	/**
	 * @return the newlink
	 */
	public int getNewlink() {
		return newlink;
	}

	/**
	 * @return the totallink
	 */
	public int getTotallink() {
		return totallink;
	}


	/**
	 * @return the totalcqapn
	 */
	public int getTotalcqapn() {
		return totalcqapn;
	}

}