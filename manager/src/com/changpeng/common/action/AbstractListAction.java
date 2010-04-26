
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
	

	
	protected int pageSize=10;
	
	protected int startIndex;
	
	public void setPageSize(int size){
		this.pageSize=size;
	}
	
	public PaginationSupport getPage(){
		return page;
	}



/**
 * 
 * @return
 * @throws Exception
 */
	protected abstract String go() throws Exception;

	
}