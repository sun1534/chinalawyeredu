package com.changpeng.sns.photo.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.sns.photo.service.PhotoService;

/**
 * 删除某个具体的照片
 * 
 * @author 华锋 May 20, 2009 9:32:29 PM
 * 
 */
public class DelPhotoAction extends AbstractAction {

	private int photoid;
	private int albumid;
	int i;

	public DelPhotoAction() {
		this.moduleid = 16;
	}

	@Override
	protected String go() throws Exception {
		int userid = 0;
		userid = this.currentUserid;

		PhotoService photoService = (PhotoService) this.getBean("photoService");

		int result = photoService.deletePhoto(photoid, albumid, userid);

		

		// 0 删除成功 -1 删除失败 -2 删除的照片不存在

		 this.redirectURL="photolist.action?albumid="+albumid;
		 this.result=result+"";
		 if(result==0){
		 this.message="您的照片删除成功";
		 }
		// else if(result==-1){
		// this.message="照片删除失败,请联系客服人员";
		// }else if(result==-2){
		// this.message="您的照片已经被删除,请联系客服人员";
		// }

		

		return "result";
	}

	protected String getin() {

		return INPUT;
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

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

}
