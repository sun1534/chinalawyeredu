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
public class NsvcListAction extends AbstractListAction {


private String bscid;
	

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
			orderfield="nsvc";
		}
		if(resultType.equals("list")){
			this.page=setservice.getNsvces(bscid,getOrderby(),pageNo,pageSize);
			this.nsvclist=this.page.getItems();
			return SUCCESS;
		}else{
			this.nsvclist=setservice.getNsvces(bscid,getOrderby());
			return "excel";
		}
	}

	private List nsvclist;
	public List getNsvclist(){
		return this.nsvclist;
	}


	/**
	 * @return the bscid
	 */
	public String getBscid() {
		return bscid;
	}

	/**
	 * @param bscid the bscid to set
	 */
	public void setBscid(String bscid) {
		this.bscid = bscid;
	}
}