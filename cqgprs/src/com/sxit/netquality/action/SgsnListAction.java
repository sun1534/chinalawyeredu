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
 * 显示sgsn的列表
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class SgsnListAction extends AbstractListAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		BasicSetService setservice = (BasicSetService) this.getBean("basicSetService");
		setservice.getAllSets();
		resultList = setservice.getSgsns();
		if (resultType.equals("list")) {
			return SUCCESS;
		} else {
			// this.startIndex = (pageNo - 1) * pageSize;
			// this.page = new PaginationSupport(BasicSetService.ALL_APN_LIST,
			// BasicSetService.ALL_APN_LIST.size(),
			// pageSize, startIndex);
			return "excel";
		}
	}

	private List resultList;

	public List getResultList() {
		return this.resultList;
	}

	private String resultType = "list";
	private String bscid;

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

}
