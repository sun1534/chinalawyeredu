/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changpeng.common.action;

import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractAction;

/**
 *
 * @author mfq 2008-11-26 21:49:49
 */
public abstract class AbstractListAction extends AbstractAction {

    protected int pageSize = 10,  pageNo;
    protected PaginationSupport page;
    public AbstractListAction(){
    	if(pageNo==0) pageNo=1;
    }
    public PaginationSupport getPage() {
        return page;
    }

    public void setPage(PaginationSupport page) {
        this.page = page;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
