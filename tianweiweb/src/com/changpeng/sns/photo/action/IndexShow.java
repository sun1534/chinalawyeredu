package com.changpeng.sns.photo.action;

import java.util.List;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.sns.photo.service.PhotoService;

/**
 * 显示到首页的我的相册列表
 * 
 * @author 华锋
 * May 20, 2009 9:45:22 PM
 *
 */
public class IndexShow extends AbstractAction {
	private List photolist ;
	
	
	public IndexShow(){
		this.moduleid=16;
	}
	
	@Override
	protected String go() throws Exception {
		int userid;
		userid = this.viewUserid;
		
		PhotoService photoService = (PhotoService)this.getBean("photoService");
		
		photolist = photoService.indexShow(userid, 3);
		
		return SUCCESS;
	}

	public List getPhotolist() {
		return photolist;
	}

	public void setPhotolist(List photolist) {
		this.photolist = photolist;
	}

}
