/**
 * 
 */
package com.sxit.communicateguard.action;

import java.text.DateFormat;

import com.sxit.common.action.AbstractListAction;
import com.sxit.communicateguard.service.GuardService;

/**
 * 
 * 保障小区列表
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class GuardCellsAction extends AbstractListAction {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

private String cellid;
	



	/**
 * @return the cellid
 */
public String getCellid() {
	return cellid;
}




/**
 * @param cellid the cellid to set
 */
public void setCellid(String cellid) {
	this.cellid = cellid;
}




	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		GuardService service=(GuardService)this.getBean("guardService");
		
	this.page=service.getFocusCellids(cellid, pageNo, pageSize);
		return SUCCESS;
	}
}
