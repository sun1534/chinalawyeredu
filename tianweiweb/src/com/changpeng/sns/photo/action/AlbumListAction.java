/**
 * 显示相册列表
 */
package com.changpeng.sns.photo.action;

import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.JugeTime;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.sns.photo.service.PhotoService;


/**
 * 
 * 显示相册列表
 * 
 * @author 华锋
 * May 20, 2009 7:32:38 PM
 *
 */
public class AlbumListAction extends AbstractListAction {
	Logger log=Logger.getLogger(AlbumListAction.class);
	
	public AlbumListAction(){
		this.moduleid=16;
	}

	@Override
	protected String go() throws Exception {
		if(pageNo<1){
			pageNo=1;
		}
		
		this.jugeTime=new JugeTime();
		
		int userid = 0;
		userid = this.currentUserid;
	
		pageSize = 8;
		PhotoService photoService = (PhotoService)this.getBean("photoService");
		
		this.page = photoService.getMyAlbumList(userid, pageSize, pageNo);
		
		
	
		
		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(page, pageNo,"getPage");
		return SUCCESS;
	}



    private String pageString;

	public String getPageString() {
		return pageString;
	}

	public void setPageString(String pageString) {
		this.pageString = pageString;
	}

	private JugeTime jugeTime;

	public JugeTime getJugeTime() {
		return jugeTime; 
	}

	
	
}
