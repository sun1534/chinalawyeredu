package com.changpeng.sns.diary.action;


import org.apache.log4j.Logger;

import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.sns.diary.service.ProDiaryService;

public class ProCurrDiaryListAction  extends AbstractListAction  {
	  Logger log=Logger.getLogger(AbstractAction.class);
	  protected int userSize;
	  protected PaginationSupport page=new PaginationSupport();
	  protected PaginationSupport friendPage;
	  protected int friendUserSize;
	  protected int friendPageNo;
      private String pageView;
	  private int pageCurrNo;
	  private String pageString;
	  private int pageno;
	 
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method st
		if(userSize==0)
		{
			userSize=3;
		}
		try{
			int userid=(Integer)this.get("currentUserid");
			ProDiaryService proDiaryService=(ProDiaryService)this.getBean("proDiaryService");
			
			page=proDiaryService.getCurrProDiaryListByUserID(userid,userSize,pageNo);
			Pagefoot pagefoot = new Pagefoot();
			pageString = pagefoot.packString(page, pageNo,"getPage",userSize);
			 
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
	    return "success";
	}
	
	public String goCurrDiarylistByUser()
	{
		try{
		int userid=(Integer)this.get("currentUserid");
		ProDiaryService proDiaryService=(ProDiaryService)this.getBean("proDiaryService");
		 
		page=proDiaryService.getCurrProDiaryListByUserID(userid,userSize,pageNo);
		 
		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(page, pageNo,"getPage",userSize);
		 
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return "success";
	}
	
	public int getUserSize() {
		return userSize;
	}
	public void setUserSize(int userSize) {
		this.userSize = userSize;
	}
	public PaginationSupport getPage1() {
		return page;
	}
	public void setPage1(PaginationSupport page) {
		this.page = page;
	}
	public PaginationSupport getFriendPage() {
		return friendPage;
	}
	public void setFriendPage(PaginationSupport friendPage) {
		this.friendPage = friendPage;
	}
	public int getFriendUserSize() {
		return friendUserSize;
	}
	public void setFriendUserSize(int friendUserSize) {
		this.friendUserSize = friendUserSize;
	}
	public int getFriendPageNo() {
		return friendPageNo;
	}
	public void setFriendPageNo(int friendPageNo) {
		this.friendPageNo = friendPageNo;
	}
	
	public String getPageView() {
		return pageView;
	}
	public void setPageView(String pageView) {
		this.pageView = pageView;
	}
	public String getPageString() {
		return pageString;
	}
	public void setPageString(String pageString) {
		this.pageString = pageString;
	}
	public int getPageCurrNo() {
		return pageCurrNo;
	}
	public void setPageCurrNo(int pageCurrNo) {
		this.pageCurrNo = pageCurrNo;
	}
	public int getPageno() {
		return pageno;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public PaginationSupport getPage() {
		return page;
	}

	public void setPage(PaginationSupport page) {
		this.page = page;
	}
	
}
