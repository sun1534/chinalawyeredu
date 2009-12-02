/**
 * 
 */
package com.sxit.netquality.action;

import java.util.List;

import com.sxit.common.PaginationSupport;
import com.sxit.common.action.AbstractListAction;
import com.sxit.netquality.service.BasicSetService;

/**
 * 
 * 设置或者取消保障的号码
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class ApnListAction extends AbstractListAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		BasicSetService setservice = (BasicSetService) this.getBean("basicSetService");
		setservice.getAllSets();
		if(orderfield==null||orderfield.equals("")){
			orderfield="orderby";
		}
		if (resultType.equals("list")) {
			focuslist = setservice.getFocusApns();
			this.page = setservice.getApns(apnid, getOrderby(),pageNo, pageSize);
			return SUCCESS;
		} else {
			this.startIndex = (pageNo - 1) * pageSize;
			this.page = new PaginationSupport(BasicSetService.ALL_APN_LIST, BasicSetService.ALL_APN_LIST.size(),
					pageSize, startIndex);

			return "excel";
		}
	}

	private String resultType="list";
	private String apnid;
	private List focuslist;

	public List getFocuslist() {
		return this.focuslist;
	}

	/**
	 * @return the resultType
	 */
	public String getResultType() {
		return resultType;
	}

	/**
	 * @param resultType
	 *            the resultType to set
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	/**
	 * @return the apnid
	 */
	public String getApnid() {
		return apnid;
	}

	/**
	 * @param apnid
	 *            the apnid to set
	 */
	public void setApnid(String apnid) {
		this.apnid = apnid;
	}

}
