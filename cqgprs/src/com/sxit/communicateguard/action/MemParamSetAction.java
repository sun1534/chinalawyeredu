/**
 * TSysUserAddAction.java
 */
package com.sxit.communicateguard.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.BasicService;
import com.sxit.common.CommonDatas;
import com.sxit.common.action.AbstractAction;
import com.sxit.models.system.SysParameter;

/**
 * 
 * 用户信息修改
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class MemParamSetAction extends AbstractAction {

	private static Log LOG = LogFactory.getLog(MemParamSetAction.class);
	private static final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

	private BasicService service;

	private String settype;
	private String pvaluecmwap;
	private String pvaluecmnet;
	private String comments;

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the settype
	 */
	public String getSettype() {
		return settype;
	}

	/**
	 * @param settype
	 *            the settype to set
	 */
	public void setSettype(String settype) {
		this.settype = settype;
	}

	public MemParamSetAction() {
		service = (BasicService) this.getBean("basicService");
		this.rightCode = "sysParameterEdit";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		// 218.201.8.150,cmnet|218.201.8.123,cmwap
		SysParameter sysParameter = (SysParameter) service.get(SysParameter.class, settype);
		System.out.println("sysparamater===="+sysParameter+",,,,,,,,,,,,"+settype);
		sysParameter.setParamvalue(pvaluecmnet + ",cmnet|" + pvaluecmwap + ",cmwap");
		sysParameter.setComments(comments);
		service.update(sysParameter);
		synchronized (CommonDatas.SysParameter) {
			CommonDatas.SysParameter.remove(sysParameter.getParamname());
			CommonDatas.SysParameter.put(sysParameter.getParamname(), sysParameter.getParamvalue());
		}
		if (settype.equals("pingip")) {
			this.message = "ping测试IP修改成功,请确认";
		} else {
			this.message = "tracert测试IP修改成功,请确认";
		}

		this.nextPage = "memParamSet!input.action?settype=" + settype;
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		SysParameter sysParameter = (SysParameter) service.get(SysParameter.class, settype);
		String pvalue = sysParameter.getParamvalue();
		// 218.201.8.150,cmnet|218.201.8.123,cmwap

		String[] ss = pvalue.split("\\|");
		this.pvaluecmnet = ss[0].split(",")[0];
		if (ss.length > 1)
			this.pvaluecmwap = ss[1].split(",")[0];

		this.comments = sysParameter.getComments();
		return INPUT;
	}

	/**
	 * @return the pvaluecmwap
	 */
	public String getPvaluecmwap() {
		return pvaluecmwap;
	}

	/**
	 * @param pvaluecmwap
	 *            the pvaluecmwap to set
	 */
	public void setPvaluecmwap(String pvaluecmwap) {
		this.pvaluecmwap = pvaluecmwap;
	}

	/**
	 * @return the pvaluecmnet
	 */
	public String getPvaluecmnet() {
		return pvaluecmnet;
	}

	/**
	 * @param pvaluecmnet
	 *            the pvaluecmnet to set
	 */
	public void setPvaluecmnet(String pvaluecmnet) {
		this.pvaluecmnet = pvaluecmnet;
	}
}
