/**
 * 
 */
package com.changpeng.lawcase.action;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

/**
 * 
 * 删除刚新增的案件
 * 
 * @author 华锋 Oct 28, 2009-3:04:20 PM
 * 
 */
public class CaseDeleteAction extends com.changpeng.lawcase.workflow.WorkFlowAction  {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub
		if ((check == null || check.length == 0)&&caseid==0) {
			this.message = "请选择需要删除的案件";
			return "message";
		}
		if(check==null||check.length==0)
			check=new long[]{caseid};
		int i = 0;
		for (long _caseid : check) {
			//将删除的案件备份到另外一个表里面
			getSession().createSQLQuery("inser tinto tlaw_lawcase_bak select * from tlaw_lawcase where caseid="+_caseid);
			i += getSession().createSQLQuery("delete from tlaw_lawcase where caseid=" + _caseid).executeUpdate();
			this.saveOperlog("删除案件:"+_caseid, 4);
		}
		this.message = "删除" + i + "个初始新增案件信息成功";
		
		
		
		this.nextpage = "newcaseList.action";
		return SUCCESS;
	}

	private long[] check;

	public void setCheck(long check[]) {
		this.check = check;
	}
}
