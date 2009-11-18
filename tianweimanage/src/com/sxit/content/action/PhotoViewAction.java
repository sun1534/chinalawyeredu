package com.sxit.content.action;

import java.text.DateFormat;
import java.util.List;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.content.SnsDiary;
import com.sxit.models.content.SnsPhoto;

/**
 * 1,家庭 2企业',
 * 
 * 根据姓名，注册的起始终止时间，状态以及身份证号码进行查询
 * 
 * @author 华锋 Jul 9, 2009 11:16:21 PM
 * 
 */

public class PhotoViewAction extends AbstractListAction {

	public PhotoViewAction() {

	}


	public String go() throws Exception {

		this.photo = (SnsPhoto) basicService.get(SnsPhoto.class, serviceId);
		if (photo == null) {
			this.message = "您所查看的相片已不存在,请返回";
			return "message";
		}
		String hql = "from com.sxit.models.workflow.TwflDohistory a where a.businessid="+businessId+" and a.serviceid=" + serviceId;
		dolist = basicService.find(hql);
		return SUCCESS;
	}

	private int serviceId;
	private int businessId;
	private SnsPhoto photo;




	public int getServiceId() {
		return serviceId;
	}


	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}


	public int getBusinessId() {
		return businessId;
	}


	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}


	public SnsPhoto getPhoto() {
		return photo;
	}
	private List dolist;

	public List getDolist() {
		return this.dolist;
	}
}
