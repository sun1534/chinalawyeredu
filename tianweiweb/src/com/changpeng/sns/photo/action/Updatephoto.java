package com.changpeng.sns.photo.action;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.sns.photo.model.SnsPhotoAlbum;
import com.changpeng.sns.photo.service.PhotoService;

/**
 * 
 * 修改相册的名称
 * 
 * @author 华锋 May 20, 2009 9:51:05 PM
 * 
 */
public class Updatephoto extends AbstractListAction {
	private SnsPhotoAlbum photos;
	private int albumid;
	private String albumname;

	public Updatephoto(){
		this.moduleid=16;
	}
	
	@Override
	protected String go() throws Exception {

		// TODO Auto-generated method stub

		PhotoService photoService = (PhotoService) this.getBean("photoService");

		photoService.updateAlbumName(albumid, albumname);
		message = "您的相册名称修改成功,请返回";
		this.redirectURL = "albumlist.action";

		return "message";

	}

	public String input() {
		PhotoService photoService = (PhotoService) this.getBean("photoService");
		photos = (SnsPhotoAlbum) photoService.get(SnsPhotoAlbum.class, albumid);

		return "input";
	}

	public SnsPhotoAlbum getPhotos() {
		return photos;
	}

	public void setPhotos(SnsPhotoAlbum photos) {
		this.photos = photos;
	}

	public int getAlbumid() {
		return albumid;
	}

	public void setAlbumid(int albumid) {
		this.albumid = albumid;
	}

	public String getAlbumname() {
		return albumname;
	}

	public void setAlbumname(String albumname) {
		this.albumname = albumname;
	}



}
