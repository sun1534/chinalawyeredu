/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uu800.webbase;

import java.util.List;

/**
 * 
 * @author mfq 2008-11-26 13:08:32
 */
public class PageSupport {

	/**
	 * 每页显示记录数,默认为10
	 */
	private int pageSize = 10;
	/**
	 * 当前的页码
	 */
	private int pageNo;

	/**
	 * 总页数
	 */
	private int pageCount = 0;
	/**
	 * 总记录数
	 */
	private int recordCount;
	/**
	 * 当前页的存储对象
	 */
	private List items;

	/**
	 * 
	 * @param items
	 *            存储的对象
	 * @param recordCount
	 *            总记录数
	 * @param pageSize
	 *            当前页显示的个数
	 * @param pageNo
	 *            当前页码
	 */
	public PageSupport(List items, int recordCount, int pageSize, int pageNo) {
		this.pageNo = pageNo;
		this.items = items;
		this.pageSize = pageSize;
		if (recordCount > 0) {
			this.recordCount = recordCount;
			this.pageCount = (recordCount - 1) / pageSize + 1;
		}
	}

	/**
	 * 
	 * @return
	 */
	public List getItems() {
		return items;
	}

	/**
	 * 
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 
	 * @return
	 */
	public int getPageNo() {
		return this.pageNo;
	}

	/**
	 * 
	 * @return
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * 
	 * @return
	 */
	public int getRecordCount() {
		return recordCount;
	}
}