package com.changpeng.sns.photo.action;

import java.util.List;
import java.util.Map;

import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.sysdata.CommonData;
import com.changpeng.common.util.JugeTime;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.sns.photo.service.PhotoService;

/**
 * 
 * 显示好友的相册列表
 * 
 * @author 华锋
 * May 20, 2009 9:40:45 PM
 *
 */
public class FriendPhotoListAction extends AbstractListAction {
	
	private List fphotoPage;
	private PaginationSupport fp;
	private String pageString;
	private JugeTime jugeTime;
	private Map maprole;

	
	public FriendPhotoListAction(){
		this.moduleid=16;
	}
	
	@Override
	protected String go() throws Exception {
		
	
		
		jugeTime = new JugeTime();
		pageSize = 8;
		
		PhotoService photoService = (PhotoService)this.getBean("photoService");
		
		List list;
		list = photoService.getMyFriendAlbumList(this.currentUserid);
		List sublist ;
		int startIndex=pageSize*(pageNo-1);
		int totalcount = list.size();
		
		if(list!=null&&list.size()>0){
			int toIndex;
			if(pageSize>totalcount-startIndex){
				toIndex = totalcount;
			}else{
				toIndex = startIndex+pageSize;
			}
			
			sublist = list.subList(startIndex, toIndex);
		}else{
			sublist = list;
		}
		
		page = new PaginationSupport();
		page.setItems(sublist);
		page.setPageSize(pageSize);
		page.setStartIndex(startIndex);
		page.setTotalCount(totalcount);
		
		maprole = CommonData.UserRoles;
		
		if(pageNo<1){
			pageNo=1;
		}
		
		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(page, pageNo,"getPage");
		return SUCCESS;

	}

	public List getFphotoPage() {
		return fphotoPage;
	}

	public void setFphotoPage(List fphotoPage) {
		this.fphotoPage = fphotoPage;
	}

	public String getPageString() {
		return pageString;
	}

	public void setPageString(String pageString) {
		this.pageString = pageString;
	}

	public JugeTime getJugeTime() {
		return jugeTime;
	}

	public void setJugeTime(JugeTime jugeTime) {
		this.jugeTime = jugeTime;
	}

	/**
	 * @return the maprole
	 */
	public Map getMaprole() {
		return maprole;
	}

	/**
	 * @param maprole the maprole to set
	 */
	public void setMaprole(Map maprole) {
		this.maprole = maprole;
	}


}
