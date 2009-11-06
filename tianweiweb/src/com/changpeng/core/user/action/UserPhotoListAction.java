package com.changpeng.core.user.action;

import java.util.List;

import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.sns.photo.service.PhotoService;


public class UserPhotoListAction extends AbstractListAction{
	private PaginationSupport photoPage;
	private String pageString;
	private int albumid=0;
	private List picturelist;
	private List albumlist;
	@Override
	protected String go() throws Exception {
		PhotoService photoService = (PhotoService)this.getBean("photoService");
		if(pageNo<1) pageNo=1;
		pageSize=20;
//		System.out.println("albumid============="+albumid);
//		//获取照片分页
		photoPage =photoService.getMyPhotos(currentUserid, albumid, pageSize, pageNo);
		picturelist =photoPage.getItems();
//		//获取下拉列表
		albumlist=photoService.getMyAlbumList(currentUserid);

		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(photoPage, pageNo,"getPage");
		return SUCCESS;
	}
	public PaginationSupport getPhotoPage() {
		return photoPage;
	}

	public String getPageString() {
		return pageString;
	}

	public int getSelectedAlbumid() {
		return albumid;
	}
	public void setAlbumid(int albumid) {
		this.albumid = albumid;
	}
	public List getPicturelist() {
		return picturelist;
	}
	public void setPicturelist(List picturelist) {
		this.picturelist = picturelist;
	}
	public List getAlbumlist() {
		return albumlist;
	}
	public void setAlbumlist(List albumlist) {
		this.albumlist = albumlist;
	}

}
