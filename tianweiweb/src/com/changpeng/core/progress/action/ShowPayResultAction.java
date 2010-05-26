package com.changpeng.core.progress.action;

import org.apache.struts2.ServletActionContext;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.core.model.CorePublish;
import com.tenpay.PayResponseHandler;


/**
 * 商家ID:1205090201
 * 
 * @author 华锋
 * Sep 23, 2009 10:12:37 PM
 *
 */
public class ShowPayResultAction extends AbstractListAction {


	private String result;

	
	public ShowPayResultAction() {
		this.rightCode = "PUBLIC";
	}

	@Override
	protected String go() throws Exception {
		if(result==null||result.equals(""))
			result="1";
		
		if(result.equals("0")){
			this.message="您的费用支付成功,<a href=\"http://www.topway-ad.com/progress/progresslist.action\">请点这里返回</a>";
		}
		else 
			if(result.equals("1")){
				this.message="您的费用支付失败,<a href=\"http://www.topway-ad.com/progress/progresslist.action\">请点这里返回</a>";
				
			}else 
				if(result.equals("2")){
					this.message="支付签名失败,<a href=\"http://www.topway-ad.com/progress/progresslist.action\">请点这里返回</a>";
				}
		System.out.println(this.message);
		
		return SUCCESS;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}


}
