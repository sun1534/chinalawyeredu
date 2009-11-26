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
	private String cellkey;

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
		GuardService service = (GuardService) this.getBean("guardService");

		try {
			if (isdelete == 0) {
				
			
				int idx=cellkey.indexOf("-");
				String cellid=cellkey.substring(idx+1);
				String lac=cellkey.substring(0,idx);
				
				
				if (!BasicSetService.ALL_CELLS.containsKey(cellkey)) {
					isok = "2";
				}
				else {
					if (service.isExistCell(cellid,lac)){
						isok = "4";
					
					} else {
						setservice.saveFocusCell(cellid,lac, this.getLoginUser().getUserid(), this.getLoginUser()
								.getUsername());
						isok = "1";
					}
				}
			} else {

				StringTokenizer st = new StringTokenizer(cellkey, ",");
				while (st.hasMoreTokens()) {
					String str = st.nextToken();
					int idx=str.indexOf("-");
					String cellid1=str.substring(idx+1);
					String lac1=str.substring(0,idx);
					_LOG.debug("cellid1" + cellid1);
					setservice.deleteFocusCell(cellid1,lac1);
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

	/**
	 * @return the cellkey
	 */
	public String getCellkey() {
		return cellkey;
	}

	/**
	 * @param cellkey the cellkey to set
	 */
	public void setCellkey(String cellkey) {
		this.cellkey = cellkey;
	}

	
}
