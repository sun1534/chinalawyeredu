package com.changpeng.sns.diary.action;

import java.util.List;

import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.sns.diary.model.SnsDiary;
import com.changpeng.sns.diary.service.ProDiaryReplyService;
import com.changpeng.sns.diary.service.ProDiaryService;



public class ProFriendDiaryReplyAction extends  AbstractListAction {
	  protected int userSize;
	  private String pageString;
	  private SnsDiary friendProDiary;
	  private int diaryId;
	  private int friendID;
	  private int falg;
	  protected PaginationSupport friendPage=new PaginationSupport();
    
	  public ProFriendDiaryReplyAction(){
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
		
		ProDiaryService proDiaryService=(ProDiaryService)this.getBean("proDiaryService");
		ProDiaryReplyService diaryReplyService=(ProDiaryReplyService)this.getBean("proDiaryReplyService");
	    
		friendProDiary=(SnsDiary) proDiaryService.get(SnsDiary.class,diaryId);
		
		friendProDiary.setClickCount(friendProDiary.getClickCount()+1);
		proDiaryService.update(friendProDiary);
		friendPage=diaryReplyService.ProDiaryReplyListByDiaryId(diaryId,userSize,pageNo);
		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(friendPage, pageNo,"getPage",userSize);
		return "success";
	}
	//得到回复
	public String getFriendDiaryReplyBydiaryid()
	{
		if(userSize==0)
		{
			userSize=8;
		}
		ProDiaryReplyService diaryReplyService=(ProDiaryReplyService)this.getBean("proDiaryReplyService");
		friendPage=diaryReplyService.ProDiaryReplyListByDiaryId(diaryId,userSize,pageNo);
		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(friendPage, pageNo,"getPage",userSize);
		 
		return "reply";
	}
	//得到主贴
	 public String getFriendDiaryBydiaryId()
	 {
		 
		 ProDiaryService proDiaryService=(ProDiaryService)this.getBean("proDiaryService");
		 int userid= this.currentUserid;
		 List  friendDiaryList=proDiaryService.getViewDiaryListByUserID(friendID);
		 int count=friendDiaryList.size();
	
		 for(int i=0;i<count;i++)
		 {
			 friendProDiary=(SnsDiary) friendDiaryList.get(i);
			 System.out.println(friendProDiary.getDiaryid());
			 if(friendProDiary.getDiaryid()==diaryId)
			 {
				 if(falg==0&&count>i+1)
				 {
					 friendProDiary=(SnsDiary) friendDiaryList.get(i+1);
					 System.out.println(friendProDiary);
					 break;
				 }else if(falg==0&&count-1==i)
				 {
					 friendProDiary=(SnsDiary) friendDiaryList.get(count-1);
					 break;
				 }else if(falg==1&&i-1>0)
				 {
					 friendProDiary=(SnsDiary) friendDiaryList.get(i-1);
					 break;
				 }else
				 {
					 friendProDiary=(SnsDiary) friendDiaryList.get(0);
					 break;
				 }
				  
			 }
		 }
		 diaryId=friendProDiary.getDiaryid();
		 getFriendDiaryReplyBydiaryid();
		 return "diary";
		 
	 }
	public int getDiaryId() {
		return diaryId;
	}
	public void setDiaryId(int diaryId) {
		this.diaryId = diaryId;
	}
	public int getUserSize() {
		return userSize;
	}
	public void setUserSize(int userSize) {
		this.userSize = userSize;
	}
	public String getPageString() {
		return pageString;
	}
	public void setPageString(String pageString) {
		this.pageString = pageString;
	}
	public SnsDiary getFriendProDiary() {
		return friendProDiary;
	}
	public void setFriendProDiary(SnsDiary friendProDiary) {
		this.friendProDiary = friendProDiary;
	}

	public PaginationSupport getFriendPage() {
		return friendPage;
	}
	public void setFriendPage(PaginationSupport friendPage) {
		this.friendPage = friendPage;
	}
	public int getFalg() {
		return falg;
	}
	public void setFalg(int falg) {
		this.falg = falg;
	}
	/**
	 * @return the friendID
	 */
	public int getFriendID() {
		return friendID;
	}
	/**
	 * @param friendID the friendID to set
	 */
	public void setFriendID(int friendID) {
		this.friendID = friendID;
	}

}
