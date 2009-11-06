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
 * 获取相片评论
 * @author wuguirong
 * Jun 17, 2009
 */
public class GetCommentAction extends AbstractListAction {

	private int photoid;
	private String pageString;
	private PaginationSupport commentpage;
	private boolean photoismine = false;
	
	public GetCommentAction(){
		this.moduleid=16;
	}
	
	@Override
	protected String go() throws Exception {
		pageSize = 5;
		PhotoService photoService = (PhotoService)this.getBean("photoService");
		
		commentpage = photoService.getPhotoComment(photoid, pageSize, pageNo);
		
		SnsPhoto photo = (SnsPhoto) photoService.get(SnsPhoto.class, photoid);
		if(photo.getUserid() == this.currentUserid){
			photoismine = true;
		}
		
		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(commentpage, pageNo,"getcommentpage");
		
		return SUCCESS;
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
