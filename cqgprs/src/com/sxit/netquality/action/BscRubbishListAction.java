/**
 * 
 */
package com.sxit.netquality.action;

import java.util.ArrayList;
import java.util.List;

import com.sxit.common.PaginationSupport;
import com.sxit.common.action.AbstractListAction;
import com.sxit.netquality.service.BasicSetService;

/**
 * 
 * 显示垃圾bsc数据的列表
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class BscRubbishListAction extends AbstractListAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		BasicSetService setservice = (BasicSetService) this.getBean("basicSetService");
		// setservice.getAllSets();

		if (orderfield == null || orderfield.equals("")) {
			orderfield = "bvci";
		}
		List list = setservice.getBscRubbishes(bscid, sgsnid, getOrderby());
		int count = list.size();
		if (resultType.equals("list")) {
			// 对resultList进行分页
			resultList = new ArrayList();
			int startIndex = (pageNo - 1) * pageSize;
			for (int i = startIndex;i<pageSize+startIndex&&i < count; i++) {
				resultList.add(list.get(i));
			}
System.out.println("resultList.size():::"+resultList.size());
			this.page = new PaginationSupport(resultList, count, pageSize, startIndex);
			return SUCCESS;
		} else {
			resultList = list;
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
	 * 根据sgsnid以及bscid来查询
	 */
	private String sgsnid;

	/**
	 * @return the sgsnid
	 */
	public String getSgsnid() {
		return sgsnid;
	}

	/**
	 * @param sgsnid
	 *            the sgsnid to set
	 */
	public void setSgsnid(String sgsnid) {
		this.sgsnid = sgsnid;
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
	 * @return the bscic
	 */
	public String getBscid() {
		return bscid;
	}

	/**
	 * @param bscic
	 *            the bscic to set
	 */
	public void setBscid(String bscid) {
		this.bscid = bscid;
	}

}
