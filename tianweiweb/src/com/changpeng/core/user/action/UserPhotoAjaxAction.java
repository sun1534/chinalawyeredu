package com.changpeng.core.user.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.sns.photo.model.SnsPhoto;
import com.changpeng.sns.photo.service.PhotoService;

public class UserPhotoAjaxAction extends AbstractAction{
    private SnsPhoto userphoto;
    private int photoid;
    String picurl;
	@Override
	protected String go() throws Exception {
		PhotoService photoService = (PhotoService)this.getBean("photoService");
		userphoto=photoService.getUserPhoto(photoid);
		
		picurl=userphoto.getUrl();
		return SUCCESS;
	}
	public SnsPhoto getUserphoto() {
		return userphoto;
	}
	public void setUserphoto(SnsPhoto userphoto) {
		this.userphoto = userphoto;
	}
	public int getPhotoid() {
		return photoid;
	}
	public void setPhotoid(int photoid) {
		this.photoid = photoid;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

}
