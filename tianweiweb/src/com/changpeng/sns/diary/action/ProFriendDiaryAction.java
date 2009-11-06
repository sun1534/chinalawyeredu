package com.changpeng.sns.diary.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.core.service.GeneralmessageService;
import com.changpeng.sns.diary.model.SnsDiary;
import com.changpeng.sns.diary.model.SnsDiaryComment;
import com.changpeng.sns.diary.service.ProDiaryService;



public class ProFriendDiaryAction  extends AbstractListAction  {
	  protected int userSize;
	  private String pageString;
	  private int friendID;
	  private String messageContentID;
	  private List frienDiarydList;
	  protected PaginationSupport page;
	  
	  public ProFriendDiaryAction(){
		  if(pageNo<1)
			  pageNo=1;
	  }
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		if(userSize==0)
		{
			userSize=8;
		}
		if(pageNo<=1){
			pageNo=1;
		}
		ProDiaryService proDiaryService=(ProDiaryService)this.getBean("proDiaryService");
		//List frienDiarydList=proDiaryService.getViewDiaryPageByUserID(userid);
		List frienDiarydList = proDiaryService.getViewDiaryListByUserID(friendID);
		 

		
		if(frienDiarydList!=null)
		{
			int diaysize = frienDiarydList.size();
			for(int i=0;i<diaysize;i++)
			{
				SnsDiary diary = new SnsDiary();
				diary = (SnsDiary) frienDiarydList.get(i);
				
				//获取摘要
				if(diary.getContent()!=null && !diary.getContent().equals(""))
				{
					if(diary.getContent().length()>300)
					{
						diary.setContent(diary.getContent().substring(0, 300)+"...");
					}else
					{
						diary.setContent(diary.getContent()+"...");
					}				
				}

//				//获取评论数
//				if(diary.getDiaryid()!=0)
//				{
//					DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SnsDiaryComment.class);
//					detachedCriteria.add(Restrictions.eq("diaryid", diary.getDiaryid()));
//					int replycount=0;
//					replycount = proDiaryService.getCountByCriteria(detachedCriteria);
//					diary.setReplyCount(replycount);
//				}
			}	
		}
		
		page=new PaginationSupport(frienDiarydList,frienDiarydList.size(),userSize,pageNo);
		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(page, pageNo,"getPage",userSize);
		
		return SUCCESS;
		//return goFriendDiaryListByUser();
	}
	
//	public String goFriendDiaryListByUser()
//	{
//		if(userSize==0)
//		{
//			userSize=8;
//		}
//		//int userid=(Integer)this.get("currentUserid");
//		ProDiaryService proDiaryService=(ProDiaryService)this.getBean("proDiaryService");
//		//List frienDiarydList=proDiaryService.getViewDiaryPageByUserID(userid);
//		List frienDiarydList = proDiaryService.getViewDiaryListByUserID(friendID);
//		 
//		page=new PaginationSupport(frienDiarydList,frienDiarydList.size(),userSize,pageNo);
//		Pagefoot pagefoot = new Pagefoot();
//		pageString = pagefoot.packString(page, pageNo,"getPage",userSize);
//		return "success";
//	}
	 
	
	//发送站内信
	public String sendMessage(){
		GeneralmessageService messageService = (GeneralmessageService) this.getBean("messageService");
		int userid=(Integer)this.get("currentUserid");
		messageService.saveShortMessage(userid,new int[]{friendID},messageContentID,false);
		message = "消息已发送成功";
		this.redirectURL="../diary/proFriendDiaryList.action";
		 return "message";
	}
	public String getPageString() {
		return pageString;
	}
	public void setPageString(String pageString) {
		this.pageString = pageString;
	}
	public int getUserSize() {
		return userSize;
	}
	public void setUserSize(int userSize) {
		this.userSize = userSize;
	}
	public PaginationSupport getPage() {
		return page;
	}
	public void setPage(PaginationSupport page) {
		this.page = page;
	}
	public int getFriendID() {
		return friendID;
	}
	public void setFriendID(int friendID) {
		this.friendID = friendID;
	}
	public List getFrienDiarydList() {
		return frienDiarydList;
	}
	public void setFrienDiarydList(List frienDiarydList) {
		this.frienDiarydList = frienDiarydList;
	}
	public String getMessageContent() {
		return messageContentID;
	}
	public void setMessageContent(String messageContentID) {
		this.messageContentID = messageContentID;
	}
	 
}
