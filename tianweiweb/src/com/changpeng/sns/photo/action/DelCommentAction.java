/**
 * wuguirong
 * Jun 17, 2009
 */
package com.changpeng.sns.photo.action;


import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.sns.photo.model.SnsPhoto;
import com.changpeng.sns.photo.service.PhotoService;

/**
 * @author wuguirong
 * Jun 17, 2009
 */
public class DelCommentAction extends AbstractListAction {

	private int commentid;
	private int photoid;
	private String pageString;
	private PaginationSupport commentpage;
	private boolean photoismine = false;
	
	public DelCommentAction(){
		this.moduleid=16;
	}
	
	@Override
	protected String go() throws Exception {
		pageSize = 5;
		PhotoService photoService = (PhotoService)this.getBean("photoService");
		SnsPhoto photo = (SnsPhoto) photoService.get(SnsPhoto.class, photoid);
		if(photo!=null){
			int photouserid = photo.getUserid();
			photoService.delPhotoComment(photouserid,this.currentUserid, commentid);
		}
		
		commentpage = photoService.getPhotoComment(photoid, pageSize, pageNo);
		if(pageNo>commentpage.getCount()){
			pageNo = commentpage.getCount();
			commentpage = photoService.getPhotoComment(photoid, pageSize, pageNo);
		}
		
		if(photo.getUserid() == this.currentUserid){
			photoismine = true;
		}
		
		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(commentpage, pageNo,"getcommentpage");
		
		return SUCCESS;
	}

	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}

	public int getPhotoid() {
		return photoid;
	}

	public void setPhotoid(int photoid) {
		this.photoid = photoid;
	}

	public String getPageString() {
		return pageString;
	}

	public void setPageString(String pageString) {
		this.pageString = pageString;
	}

	public PaginationSupport getCommentpage() {
		return commentpage;
	}

	public void setCommentpage(PaginationSupport commentpage) {
		this.commentpage = commentpage;
	}

	public boolean isPhotoismine() {
		return photoismine;
	}

	public void setPhotoismine(boolean photoismine) {
		this.photoismine = photoismine;
	}

}
