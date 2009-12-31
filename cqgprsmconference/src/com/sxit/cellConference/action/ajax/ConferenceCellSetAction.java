/**
 * 
 */
package com.sxit.cellConference.action.ajax;

import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.cellConference.service.CellConferenceService;
import com.sxit.common.action.AbstractAction;
import com.sxit.netquality.service.BasicSetService;

/**
 * 
 * 设置或者取消重点apn设置
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class ConferenceCellSetAction extends AbstractAction {

	private static Log _LOG = LogFactory.getLog(ConferenceCellSetAction.class);
	private String cellkey;
	private float flowview;
	private float userview;
	private int timeview1;
	private int timeview2;
	private String isok;
	
	private int isdelete;

	/**
	 * @param isdelete
	 *            the isdelete to set
	 */
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	public int getIsdelete() {
		return this.isdelete;
	}

	/**
	 * @return the isok
	 */
	public String getIsok() {
		return isok;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		BasicSetService setservice = (BasicSetService) this.getBean("basicSetService");
		setservice.getAllSets();
		CellConferenceService service = (CellConferenceService) this.getBean("cellConferenceService");

		try {
			if (isdelete == 0) {
				int idx = cellkey.indexOf("-");
				String cellid = cellkey.substring(idx + 1);
				String lac = cellkey.substring(0, idx);

				if (!BasicSetService.ALL_CELLS.containsKey(cellkey)) {
					isok = "2";
				} else {
					if (service.isExistCell(cellid, lac)) {
						isok = "4";
					} else {
						service.saveConferenceCell(cellid, lac, userview, flowview, timeview1,timeview2, this.getLoginUser());
						isok = "1";
					}
				}
			} else if (isdelete == 1) {

				StringTokenizer st = new StringTokenizer(cellkey, ",");
				while (st.hasMoreTokens()) {
					String str = st.nextToken();
					int idx = str.indexOf("-");
					String cellid1 = str.substring(idx + 1);
					String lac1 = str.substring(0, idx);
					_LOG.debug("cellid1" + cellid1);
					service.deleteConferenceCell(cellid1, lac1);
				}
				isok = "1";
			} else if (isdelete == 2) { // 修改时间点信息等

				int idx = cellkey.indexOf("-");
				String cellid1 = cellkey.substring(idx + 1);
				String lac1 = cellkey.substring(0, idx);
				_LOG.debug("cellid1" + cellid1);
				service.updateConferenceCell(cellid1, lac1, userview, flowview, timeview1,timeview2);

				isok = "1";
			}
			service.getAllConferenceCell(true);

		} catch (Exception e) {
			isok = "3";
			_LOG.error("监控会议小区设置错误", e);
		}
		return SUCCESS;
	}

	/**
	 * @param now
	 *            the now to set
	 */
	public void setNow(String now) {

	}

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
	 * @param flowview the flowview to set
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
	 * @param userview the userview to set
	 */
	public void setUserview(float userview) {
		this.userview = userview;
	}

	/**
	 * @return the timeview1
	 */
	public int getTimeview1() {
		return timeview1;
	}

	/**
	 * @param timeview1 the timeview1 to set
	 */
	public void setTimeview1(int timeview1) {
		this.timeview1 = timeview1;
	}

	/**
	 * @return the timeview2
	 */
	public int getTimeview2() {
		return timeview2;
	}

	/**
	 * @param timeview2 the timeview2 to set
	 */
	public void setTimeview2(int timeview2) {
		this.timeview2 = timeview2;
	}

	

}
