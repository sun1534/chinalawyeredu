package com.changpeng.sns.photo.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.sns.photo.model.SnsPhoto;
import com.changpeng.sns.photo.service.PhotoService;

/**
 * 
 * 
 * 将这个照片设置为相册的封面
 * 
 * @author 华锋
 * May 20, 2009 9:47:14 PM
 *
 */
public class SetFrontAction extends AbstractAction {
	
	private int photoid;
	private int albumid;
	private int pageNo;

	
	public SetFrontAction(){
		this.moduleid=16;
	}
	@Override
	protected String go() throws Exception {
		int userid = 0;
		userid = this.currentUserid;
		
		PhotoService photoService = (PhotoService)this.getBean("photoService");
		
		SnsPhoto photo = photoService.getUserPhoto(photoid);
		
		String albumpic = photo.getSmallUrl();

//		log.debug("xxxxxxxxxxx"+albumpic);
//		SnsPhotoAlbum album = (SnsPhotoAlbum)photoService.get(SnsPhotoAlbum.class, photo.getAlbumid());
//		album.setAlbumpic(albumpic);
		photoService.setAlbumUrl(photo.getAlbumid(),albumpic);
		
		message = "您的相册首页图片设置成功";
		
		this.redirectURL = "../photo/showphoto.action?albumid="+albumid+"&pageNo="+pageNo;
		
		return "message";
	}

	public int getPhotoid() {
		return photoid;
	}

	public void setPhotoid(int photoid) {
		this.photoid = photoid;
	}
	public int getAlbumid() {
		return albumid;
	}
	public void setAlbumid(int albumid) {
		this.albumid = albumid;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
}
