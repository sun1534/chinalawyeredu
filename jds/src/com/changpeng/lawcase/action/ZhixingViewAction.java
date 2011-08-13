/**
 * 
 */
package com.changpeng.lawcase.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.model.TlawSusong;
import com.changpeng.lawcase.model.TlawZhixing;
import com.changpeng.lawcase.util.Clob2String;

/**
 * 
 * 查看填写的诉状材料信息
 * 
 * 
 * @author 华锋 Oct 24, 2009-9:18:18 AM
 * 
 */
public class ZhixingViewAction extends com.changpeng.lawcase.workflow.WorkFlowAction {

	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		this.zhixing = (TlawZhixing) getSession().get(TlawZhixing.class, caseid);
	if(zhixing==null){
		this.message="暂时还没有填写案件的执行材料";
		return "message";
	}
		
		this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
		this.filelist=getSession().createQuery("from TlawFiles a where a.type=3 and a.caseid="+caseid).list();

		this.thecontent = Clob2String.clob2String(zhixing.getThecontent());
		return SUCCESS;
	}
	private List filelist;
	public List getFilelist(){
		return this.filelist;
	}
	private String thecontent;
	
	private com.changpeng.lawcase.model.TlawZhixing zhixing;

	public TlawZhixing getZhixing() {

		return zhixing;
	}
	private TlawLawcase lawcase;
	public TlawLawcase getLawcase() {
		return this.lawcase;
	}
	/**
	 * @return the thecontent
	 */
	public String getThecontent() {
		return thecontent;
	}

	/**
	 * @param thecontent
	 *            the thecontent to set
	 */
	public void setThecontent(String thecontent) {
		this.thecontent = thecontent;
	}
}
