/**
 * 
 */
package com.sxit.cellConference.action.ajax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.cellConference.models.ConferenceCell;
import com.sxit.cellConference.service.CellConferenceService;
import com.sxit.common.action.AbstractAction;

/**
 * @author 华锋 Nov 20, 2009-10:09:40 PM
 * 
 */
public class ConferenceCellEditAction extends AbstractAction {
	private static Log _LOG = LogFactory.getLog(ConferenceCellEditAction.class);

	private String cellkey;
	private int flowview;
	private int userview;
	private int timeview;

	private int isok;

	/**
	 * @return the cellkey
	 */
	public String getCellkey() {
		return cellkey;
	}

	/**
	 * @param cellkey
	 *            the cellkey to set
	 */
	public void setCellkey(String cellkey) {
		this.cellkey = cellkey;
	}

	/**
	 * @return the flowview
	 */
	public int getFlowview() {
		return flowview;
	}

	/**
	 * @param flowview
	 *            the flowview to set
	 */
	public void setFlowview(int flowview) {
		this.flowview = flowview;
	}

	/**
	 * @return the userview
	 */
	public int getUserview() {
		return userview;
	}

	/**
	 * @param userview
	 *            the userview to set
	 */
	public void setUserview(int userview) {
		this.userview = userview;
	}

	/**
	 * @return the timeview
	 */
	public int getTimeview() {
		return timeview;
	}

	/**
	 * @param timeview
	 *            the timeview to set
	 */
	public void setTimeview(int timeview) {
		this.timeview = timeview;
	}

	/**
	 * @return the isok
	 */
	public int getIsok() {
		return isok;
	}

	/**
	 * @param isok
	 *            the isok to set
	 */
	public void setIsok(int isok) {
		this.isok = isok;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		try {
			CellConferenceService service = (CellConferenceService) this.getBean("cellConferenceService");
			int idx = cellkey.indexOf("-");
			String lac = cellkey.substring(0, idx);
			String cellid = cellkey.substring(idx + 1);
			ConferenceCell cell = service.getConferenceCell(lac, cellid);
			if (cell != null) {
				service.updateConferenceCell(cellid, lac, userview, flowview, timeview);

				isok = 0;
			} else {
				isok = 1;
			}
			this.message="参数信息修改成功";
		} catch (Exception e) {
			this.message="参数信息修改失败:"+e.getMessage();
			isok = 2;
			_LOG.error("ConferenceCellEditAction", e);
		}
		this.nextPage="../cellConference/conferenceCellList.action";
		return SUCCESS;
	}
	
	public String input()throws Exception{
		CellConferenceService service = (CellConferenceService) this.getBean("cellConferenceService");
	
		int idx = cellkey.indexOf("-");
		String lac = cellkey.substring(0, idx);
		String cellid = cellkey.substring(idx + 1);
		
		ConferenceCell cell = service.getConferenceCell(lac, cellid);
		this.timeview=cell.getTimeview();
		this.flowview=cell.getFlowalarmvalue();
		this.userview=cell.getUseralarmvalue();
		
		System.out.println(timeview+"=="+flowview+"==="+userview);
		
		return INPUT;

		
	}

}
