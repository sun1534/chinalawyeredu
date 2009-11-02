
package com.changpeng.common.action;

import com.changpeng.common.PaginationSupport;

/**
 * 如果是需要分页的话,继承此类
 * 
 * 
 * @author 华锋 2008-2-20 下午04:54:59
 * 
 */
public abstract class AbstractListAction extends AbstractAction {

	
	protected PaginationSupport page;
	
	protected int pageNo=1;
	
	protected int pageSize=10;
	
	protected int startIndex;
	public void setPageNo(int pageNo){
		this.pageNo=pageNo;
	}
	public void setPageSize(int size){
		this.pageSize=size;
	}
	
	public PaginationSupport getPage(){
		return page;
	}
	public int getPageNo(){
		return this.pageNo;
	}
	
	
/**
 * 
 * @return
 * @throws Exception
 */
	protected abstract String go() throws Exception;

	
}