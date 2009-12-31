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
	private float flowview;
	private float userview;
	private int timeview1;
	private int timeview2;
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
	public float getFlowview() {
		return flowview;
	}

	/**
	 * @param flowview
	 *            the flowview to set
	 */
	public void setFlowview(float flowview) {
		this.flowview = flowview;
	}

	/**
	 * @return the userview
	 */
	public float getUserview() {
		return userview;
	}

	/**
	 * @param userview
	 *            the userview to set
	 */
	public void setUserview(float userview) {
		this.userview = userview;
	}

	/**
	 * @return the timeview
	 */
	public int getTimeview1() {
		return timeview1;
	}

	/**
	 * @param timeview
	 *            the timeview to set
	 */
	public void setTimeview1(int timeview1) {
		this.timeview1 = timeview1;
	}
	/**
	 * @return the timeview
	 */
	public int getTimeview2() {
		return timeview2;
	}

	/**
	 * @param timeview
	 *            the timeview to set
	 */
	public void setTimeview2(int timeview2) {
		this.timeview2 = timeview2;
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
				service.updateConferenceCell(cellid, lac, userview, flowview, timeview1,timeview2);

				isok = 0;
			} else {
				isok = 1;
			}
			this.message="参数信息修改成功";
			service.getAllConferenceCell(true);
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
		this.timeview1=cell.getTimeview1();
		this.timeview2=cell.getTimeview2();
		this.flowview=cell.getFlowalarmvalue();
		this.userview=cell.getUseralarmvalue();
		
	
			timeview1hour=timeview1/100;
			int _timeview1min=timeview1%100;
			if(_timeview1min<10)
				timeview1min="0"+_timeview1min;
			else
				timeview1min=""+_timeview1min;

			System.out.println("timeview1min:::"+timeview1min);
			
			timeview2hour=timeview2/100;
			int _timeview2min=timeview2%100;
			if(_timeview2min<10)
				timeview2min="0"+_timeview2min;
			else
				timeview2min=""+_timeview2min;
		
			System.out.println("timeview2min:::"+timeview2min);
		
		System.out.println(timeview1+"=="+timeview2+"=="+flowview+"==="+userview);
		
		return INPUT;

		
	}
	private int timeview1hour;
	private String timeview1min;

	private int timeview2hour;
	private String timeview2min;

	/**
	 * @return the timeview1hour
	 */
	public int getTimeview1hour() {
		return timeview1hour;
	}

	/**
	 * @return the timeview1min
	 */
	public String getTimeview1min() {
		return timeview1min;
	}

	/**
	 * @return the timeview2hour
	 */
	public int getTimeview2hour() {
		return timeview2hour;
	}

	/**
	 * @return the timeview2min
	 */
	public String getTimeview2min() {
		return timeview2min;
	}


}
