package com.changpeng.sns.photo.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.sns.photo.service.PhotoService;

/**
 * 修改
 * 
 * @author 华锋
 * May 20, 2009 9:37:39 PM
 *
 */
public class EditPhotoAction extends AbstractAction {
	
	private int photoid;
	private String photoname;
//	private int albumid;

	public EditPhotoAction(){
		this.moduleid=16;
	}
	
	
	@Override
	protected String go() throws Exception {
		int userid = 0;
		userid = this.currentUserid;
		
		PhotoService photoService = (PhotoService)this.getBean("photoService");
		boolean b = photoService.modifyPhotoName(photoid, userid, photoname);
		
		if(b)
			this.result="1";
		return "result";
	}

	public int getPhotoid() {
		return photoid;
	}

	public void setPhotoid(int photoid) {
		this.photoid = photoid;
	}

	public String getPhotoname() {
		return photoname;
	}

	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}

//	public int getAlbumid() {
//		return albumid;
//	}
//
//	public void setAlbumid(int albumid) {
//		this.albumid = albumid;
//	}

}
