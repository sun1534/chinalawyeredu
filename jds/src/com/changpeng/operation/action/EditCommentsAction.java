package com.changpeng.operation.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.*;
import com.changpeng.operation.model.ToprCreditcard;
import com.changpeng.operation.model.ToprCredittask;

import org.hibernate.Query;
import java.util.List;
/**
 * 设置任务备注
 * @author sinhoo
 * Sep 21, 2009
 */

public class EditCommentsAction extends AbstractAction {
	private long creditcardid;
	private String comments;
	private boolean result; //结果
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
	

	public long getCreditcardid() {
		return creditcardid;
	}
	public String getComments() {
		return comments;
	}
	public void setCreditcardid(long creditcardid) {
		this.creditcardid = creditcardid;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String go() throws HibernateException {
		ToprCreditcard c=(ToprCreditcard)getSession().get(ToprCreditcard.class, creditcardid);
		if(c!=null){
			c.setComments(comments);
			getSession().update(c);
		}
		//getSession().createSQLQuery("update topr_creditcard set comments=? where creditcardid="+creditcardid).setParameter(1, comments).executeUpdate();
		result=true;
		return SUCCESS;
	}
	public String input(){
		
		return INPUT;
	}
}
