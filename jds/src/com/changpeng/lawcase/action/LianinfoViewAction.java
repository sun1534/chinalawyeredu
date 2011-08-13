/**
 * 
 */
package com.changpeng.lawcase.action;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.model.TlawLianinfo;

/**
 * 
 * 查看这个案件的立案信息
 * 
 * 
 * @author 华锋 Oct 24, 2009-9:18:18 AM
 * 
 */
public class LianinfoViewAction extends com.changpeng.lawcase.workflow.WorkFlowAction {

	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub
	
		this.lawcase=(TlawLawcase)getSession().get(TlawLawcase.class, caseid);
		if(lawcase==null){
			this.message="案件信息已不存在,请联系管理员";
		}
		this.lianinfo=(TlawLianinfo)getSession().get(TlawLianinfo.class, caseid);
		if(lawcase==null){
			this.message="立案信息还未填写,请先填写";
		}
		return SUCCESS;
	}

	private TlawLawcase lawcase;
	private TlawLianinfo lianinfo;
	public TlawLawcase getLawcase(){
		return this.lawcase;
	}
	
	public TlawLianinfo getLianinfo(){
		return this.lianinfo;
	}
	
	
}
