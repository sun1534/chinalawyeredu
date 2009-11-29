/**
 * 
 */
package com.sxit.cellConference.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.sxit.cellConference.service.CellConferenceService;
import com.sxit.common.action.AbstractListAction;

/**
 * 
 * 会议小区列表
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class ConferenceCellListAction extends AbstractListAction {
//	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private String cellid;

	/**
	 * @return the cellid
	 */
	public String getCellid() {
		return cellid;
	}

	/**
	 * @param cellid
	 *            the cellid to set
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
		CellConferenceService service = (CellConferenceService) this.getBean("cellConferenceService");

		if(!resultType.equals("list"))
		{
			this.pageNo=1;
			this.pageSize=Integer.MAX_VALUE;
		}
		this.page = service.getConferenceCellids(cellid, pageNo, pageSize);
		
		timelist=CellConferenceService.TIMELIST;
		if (resultType.equals("list"))
			return SUCCESS;
		return "excel";
	}
	
	
	private Map<Integer,String> timelist;
	public Map<Integer,String> getTimelist(){
		return this.timelist;
	}
}
