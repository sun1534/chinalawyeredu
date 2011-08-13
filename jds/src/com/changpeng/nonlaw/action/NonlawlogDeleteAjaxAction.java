package com.changpeng.nonlaw.action;


import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.TnlwNonlawlog;





/**
 * ajax方式删除催收日志
 * @author sinhoo
 * Sep 2, 2009
 */

public class NonlawlogDeleteAjaxAction extends AbstractAction {
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
	public NonlawlogDeleteAjaxAction() {
          // rights="usr1,8";
	}
	public String go() throws HibernateException {
		TnlwNonlawlog log = (TnlwNonlawlog)getSession().get(TnlwNonlawlog.class, logid);
	    getSession().delete(log);
	    result=true;
         //       message="删除成功！";
		return SUCCESS;
	}

}
