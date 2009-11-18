/**
 * 
 */
package com.sxit.communicateguard.action.ajax;

import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.action.AbstractAction;
import com.sxit.communicateguard.service.GuardService;
import com.sxit.netquality.service.BasicSetService;

/**
 * 
 * 设置或者取消重点apn设置
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class GuardCellSetAction extends AbstractAction {

	private static Log _LOG = LogFactory.getLog(GuadrNoSetAction.class);
	private String now;
	private String cellid;
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
	 * @return the mobile
	 */
	public String getCellid() {
		return cellid;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setCellid(String cellid) {
		this.cellid = cellid;
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
		GuardService service = (GuardService) this.getBean("guardService");

		try {
			if (isdelete == 0) {
				if (!BasicSetService.ALL_CELLS.containsKey(cellid)) {
					isok = "2";
				}
				else {
					if (service.isExistCell(cellid)){
						isok = "4";
					
					} else {
						setservice.saveFocusCell(cellid, this.getLoginUser().getUserid(), this.getLoginUser()
								.getUsername());
						isok = "1";
					}
				}
			} else {

				StringTokenizer st = new StringTokenizer(cellid, ",");
				while (st.hasMoreTokens()) {
					String cellid1 = st.nextToken();
					_LOG.debug("cellid1" + cellid1);
					setservice.deleteFocusCell(cellid1);
				}
				isok = "1";
			}

		} catch (Exception e) {
			isok = "3";
			_LOG.error("保障号码设置错误", e);
		}
		return SUCCESS;
	}

	/**
	 * @param now
	 *            the now to set
	 */
	public void setNow(String now) {
		this.now = now;
	}
}
