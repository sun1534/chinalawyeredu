/**
 * LawerChangeCardNoAction.java
 */
package com.changpeng.system.action.ajax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysUser;

/**
 * 密码重置
 * 
 * @author 华锋
 * 2008-5-5 下午05:34:22
 *
 */
public class LawerChangeCardNoAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(LawerChangeCardNoAction.class);
	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		
		try{
			BasicService basicService=(BasicService)getBean("basicService");
			debug("======basicService::"+basicService);
			
			SysUser lawer=(SysUser)basicService.get(SysUser.class, userid);
			if(lawer.getCardno()!=null&&this.cardno.equals(lawer.getCardno())){
				throw new Exception("已经有律师使用了这个卡号");
			}
			
			debug("========="+lawer);
			debug("==cardno+"+cardno+"===carddate==="+carddate);
			
			lawer.setCardno(cardno);
			lawer.setCarddate(carddate);
			basicService.update(lawer);
			changeok="true";

		}catch(Exception e){
			LOG.error("卡号信息修改失败:"+e);
			changeok="false";
		}
		
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	
	private String changeok;
	public String getChangeok(){
		return this.changeok;
	}
	
	private  String cardno;
	private String carddate;
	private int userid;
	/**
	 * @return the cardno
	 */
	public String getCardno() {
		return cardno;
	}
	/**
	 * @param cardno the cardno to set
	 */
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	/**
	 * @return the carddate
	 */
	public String getCarddate() {
		return carddate;
	}
	/**
	 * @param carddate the carddate to set
	 */
	public void setCarddate(String carddate) {
		this.carddate = carddate;
	}
	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

}
