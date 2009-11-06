/**
 * 显示相片列表
 */
package com.changpeng.sns.photo.action;

import java.util.List;

import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.sns.photo.model.SnsPhotoAlbum;
import com.changpeng.sns.photo.service.PhotoService;

/**
 * 根据相册编号，显示这个相册的照片列表
 * @author 华锋
 * May 20, 2009 9:45:58 PM
 *
 */
public class PhotoListAction extends AbstractListAction {
	
	private List photolist ;
	private PaginationSupport photopage;
	private int albumid;
	private String albumName;

	private SnsPhotoAlbum album;
	private String pageString;

	public PhotoListAction(){
		this.moduleid=16;
	}
	
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
	
		pageSize = 8;
		PhotoService photoService = (PhotoService)this.getBean("photoService");
		
		photopage = photoService.getMyPhotos(this.currentUserid,albumid,pageSize,pageNo);
		
		album =(SnsPhotoAlbum)photoService.get(SnsPhotoAlbum.class, albumid);
//		set("photolist",photolist);
		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(photopage, pageNo,"getPage");
		return SUCCESS;
	}
	


	public String getPageString() {
		return pageString;
	}

	public void setPageString(String pageString) {
		this.pageString = pageString;
	}

	public List getPhotolist() {
		return photolist;
	}

	public void setPhotolist(List photolist) {
		this.photolist = photolist;
	}

	public int getAlbumid() {
		return albumid;
	}

	public void setAlbumid(int albumid) {
		this.albumid = albumid;
	}

	public SnsPhotoAlbum getAlbum() {
		return album;
	}

	public void setAlbum(SnsPhotoAlbum album) {
		this.album = album;
	}

	public PaginationSupport getPhotopage() {
		return photopage;
	}

	public void setPhotopage(PaginationSupport photopage) {
		this.photopage = photopage;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}



}
