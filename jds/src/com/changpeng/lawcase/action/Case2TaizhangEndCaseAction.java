/**
 * 
 */
package com.changpeng.lawcase.action;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.workflow.WorkFlowAction;

/**
 * 
 * 诉讼承办人提交到台帐,进行结案的动作
 * 
 * @author 华锋 Oct 26, 2009-9:25:13 PM
 * 
 */
public class Case2TaizhangEndCaseAction extends WorkFlowAction {

	
	

	@Override
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub
		this.remarks=com.changpeng.lawcase.util.StringUtil.str2hexstr("调解成功,撤诉后请求结案");
		return SUCCESS;
	}
}