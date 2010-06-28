/**
 * 
 */
package com.sxit.query.action;

import java.text.DateFormat;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.action.AbstractAction;
import com.sxit.query.model.MobileTraceState;
import com.sxit.query.service.EricssonTrace;

/**
 * 
 * 执行脚本的方式获得数据
 * 
 * @author 华锋 Nov 5, 2009-10:32:58 PM
 * 
 */
public class EricssonTraceAction extends AbstractAction {
	private static Log _LOG = LogFactory.getLog(EricssonTraceAction.class);

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 1是追踪2是停止追踪
	 */
	private int stoporstart=1;
	
	private String random;
	private String mobile;
	private String sgsnid="SGSNCQ02";

	/**
	 * @return the random
	 */
	public String getRandom() {
		return random;
	}

	/**
	 * @param random
	 *            the random to set
	 */
	public void setRandom(String random) {
		this.random = random;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the sgsnid
	 */
	public String getSgsnid() {
		return sgsnid;
	}

	/**
	 * @param sgsnid
	 *            the sgsnid to set
	 */
	public void setSgsnid(String sgsnid) {
		this.sgsnid = sgsnid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		EricssonTrace service = new EricssonTrace();
		long now = System.currentTimeMillis();

		if(stoporstart==1){ //调用追踪的命令
			traceState=service.mobileTrac(sgsnid, mobile, random);
			
		}else if(stoporstart==2){ //停止追踪			
			traceState=service.mobileTracStop(sgsnid, mobile, random);
		}else if(stoporstart==3)//得到追踪数据的列表
		{
			traceState=service.getTraceLogs(sgsnid, mobile, random);
			return "noajax";
		
		}else{
			//停止对所有号码的追踪
			Iterator<String> iterator=EricssonTrace.TRACE_MOBILES.keySet().iterator();
		
			while(iterator.hasNext()){
				now=System.currentTimeMillis();
				String key=iterator.next();
				String mobile=key.split(",")[0];
				service.mobileTracStop(EricssonTrace.TRACE_MOBILES.get(key), mobile, now+"");
				System.out.println("停止执行时间:::"+(System.currentTimeMillis()-now));
			}
		}

		return SUCCESS;
	}
	private MobileTraceState traceState;

	/**
	 * @return the traceState
	 */
	public MobileTraceState getTraceState() {
		return traceState;
	}

	/**
	 * @return the stoporstart
	 */
	public int getStoporstart() {
		return stoporstart;
	}

	/**
	 * @param stoporstart the stoporstart to set
	 */
	public void setStoporstart(int stoporstart) {
		this.stoporstart = stoporstart;
	}
}