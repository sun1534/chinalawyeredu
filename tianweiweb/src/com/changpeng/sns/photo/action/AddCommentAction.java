
package com.changpeng.sns.photo.action;

import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.sns.photo.model.SnsPhoto;
import com.changpeng.sns.photo.service.PhotoService;


public class AddCommentAction extends AbstractListAction {

	private int photoid;
	private String comment;
	private String pageString;
	private PaginationSupport commentpage;
	private boolean photoismine = false;
	
	public AddCommentAction(){
		this.moduleid=16;
	}
	
	@Override
	protected String go() throws Exception {
		pageSize = 5;
		PhotoService photoService = (PhotoService)this.getBean("photoService");
		
		photoService.addPhotoComment(this.currentUserid,  photoid, comment);
		
		int totlecount = photoService.getCommentCount(photoid);
		
		/* 最后发表的评论的页数 */
		pageNo = totlecount>0?(totlecount-1)/pageSize+1:0;
		
		commentpage = photoService.getPhotoComment(photoid, pageSize, pageNo);
		
		SnsPhoto photo = (SnsPhoto) photoService.get(SnsPhoto.class, photoid);
		if(photo.getUserid() == this.currentUserid){
			photoismine = true;
		}
		
		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(commentpage, pageNo ,"getcommentpage");
		
		return SUCCESS;
	}

	public int getPhotoid() {
		return photoid;
	}

	public void setPhotoid(int photoid) {
		this.photoid = photoid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
