package com.changpeng.operation.action;


import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.address.model.*;
import com.changpeng.operation.model.ToprCreditlog;




/**
 * ajax方式删除催收日志
 * @author sinhoo
 * Sep 2, 2009
 */

public class CreditlogDeleteAjaxAction extends AbstractAction {
	private long logid;
	private boolean result; //删除结果
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
	public long getLogid() {
		return logid;
	}
	public void setLogid(long logid) {
		this.logid = logid;
	}
	public CreditlogDeleteAjaxAction() {
          // rights="usr1,8";
	}
	public String go() throws HibernateException {
		ToprCreditlog log = (ToprCreditlog)getSession().get(ToprCreditlog.class, logid);
	    getSession().delete(log);
	    result=true;
         //       message="删除成功！";
		return SUCCESS;
	}

}
