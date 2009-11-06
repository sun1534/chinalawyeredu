package com.changpeng.sns.photo.action;

import java.util.List;

import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.sns.photo.model.SnsPhoto;
import com.changpeng.sns.photo.model.SnsPhotoAlbum;
import com.changpeng.sns.photo.service.PhotoService;

public class ShowPhotoAction extends AbstractListAction {
	
	private String previous;
	private String next;
	private int albumid;
	private SnsPhotoAlbum album;
	private int photoid=0;
	private PaginationSupport commentpage;
	private String pageString;

	@Override
	protected String go() throws Exception {
		
//		photolist = (List) get("photolist");
		pageSize = 1;
		PhotoService photoService = (PhotoService)this.getBean("photoService");
		
		/* 通过photoid来查询，这里主要是相片动态的链入用 */
		if(photoid!=0){
			SnsPhoto photo = (SnsPhoto) photoService.getPhotoById(photoid);
			if(photo==null){
				return "del";
			}
			albumid = photo.getAlbumid();
			List list = photoService.getMyPhotos(albumid, this.currentUserid);
			int i;
			for(i=0;i<list.size();i++){
				if(((SnsPhoto)list.get(i)).getPhotoid()==photoid)
					break;
			}
			pageNo = i+1;
		}
		
		/* 相片的分页显示 */
		page = photoService.getMyPhotos(this.currentUserid,albumid,pageSize,pageNo);
		
		/* 获取相册的信息 */
		album =(SnsPhotoAlbum)photoService.get(SnsPhotoAlbum.class, albumid);
		
		/* 获取评论信息 */
		if(page.getItems().size()>0){
			photoid = ((SnsPhoto)page.getItems().get(0)).getPhotoid();
			
			commentpage = photoService.getPhotoComment(photoid, 5, 1);
			
			Pagefoot pagefoot = new Pagefoot();
			pageString = pagefoot.packString(commentpage, 1,"getcommentpage");
		}
		
		/* 封装上一页和下一页的链接 */
		if(pageNo>1){
			previous = "showphoto.action?albumid="+albumid+"&pageNo="+(pageNo-1);
		}else{
			previous = "javascript:alert('这是第一张图片')";
		}
		
		if(pageNo<page.getCount()){
			next = "showphoto.action?albumid="+albumid+"&pageNo="+(pageNo+1);
		}else{
			next = "javascript:alert('这是最后一张图片')";
		}

		return SUCCESS;
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

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public int getPhotoid() {
		return photoid;
	}

	public void setPhotoid(int photoid) {
		this.photoid = photoid;
	}
	
	public PaginationSupport getCommentpage() {
		return commentpage;
	}

	public void setCommentpage(PaginationSupport commentpage) {
		this.commentpage = commentpage;
	}

	public String getPageString() {
		return pageString;
	}

	public void setPageString(String pageString) {
		this.pageString = pageString;
	}


}
