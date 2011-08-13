package com.changpeng.operation.action;


import java.util.*;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.operation.model.*;


/**
 * 编辑催收日志
 * @author sinhoo
 * Oct 12, 2009
 */
public class CreditlogEdit2Action extends AbstractAction {

	private ToprCreditlog creditlog;
	private long logid;
	
	public void setLogid(long logid) {
		this.logid = logid;
	}
	public long getLogid() {
		return logid;
	}

	public void setCreditlog(ToprCreditlog creditlog) {
		this.creditlog = creditlog;
	}
	public ToprCreditlog getCreditlog() {
		if(creditlog==null)
			creditlog=(ToprCreditlog)get("creditlog");
		return creditlog;
	}
	

	public CreditlogEdit2Action() {
           rights="opr4,2";
	}

	public String go() throws HibernateException {
		
		getSession().update(creditlog);
                nextpage="creditlogList.action";
                message="保存成功！";
		return SUCCESS;
	}

	
    public String input() throws Exception {
    	creditlog=(ToprCreditlog)getSession().get(ToprCreditlog.class, logid);
    	set("creditlog",creditlog);
    	return "input";
    }
}
