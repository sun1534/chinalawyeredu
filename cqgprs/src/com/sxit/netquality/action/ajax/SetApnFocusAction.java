/**
 * 
 */
package com.sxit.netquality.action.ajax;

import com.sxit.common.action.AbstractAction;
import com.sxit.netquality.service.BasicSetService;

/**
 * 
 * 设置或者取消重点apn设置
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class SetApnFocusAction extends AbstractAction {

	private String all;
	private String selected;
	private String msg;

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param all
	 *            the all to set
	 */
	public void setAll(String all) {
		this.all = all;
	}

	/**
	 * @param selected
	 *            the selected to set
	 */
	public void setSelected(String selected) {
		this.selected = selected;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		try {
			BasicSetService setservice = (BasicSetService) this.getBean("basicSetService");
		System.out.println("+selected+"+selected);
			setservice.setFocusApn(all, selected,this.getLoginUser().getUserid(),this.getLoginUser().getUsername());
			this.msg = "重点APN设置成功";
		} catch (Exception e) {
			this.msg = "设置失败,请返回";
		}
		return SUCCESS;
	}

}
