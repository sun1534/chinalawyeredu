package com.changpeng.sns.diary.action;


import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.sns.diary.model.SnsDiary;
import com.changpeng.sns.diary.model.SnsDiaryComment;
import com.changpeng.sns.diary.service.ProDiaryReplyService;
import com.changpeng.sns.diary.service.ProDiaryService;


public class ProDiaryandReplyList extends AbstractListAction {
    private int  proDiaryId;
   
    private boolean mFalg=false;
    private boolean lFalg=false;
    private boolean fFalg=false;
    private String tiShi;
    private int falg;
    protected PaginationSupport page=new PaginationSupport();
    protected PaginationSupport friendPage=new PaginationSupport();
    private int userSize;
    private int friendSize;
    private int freiendPageNo;
    private String pageString;
    private SnsDiary diary;
    private SnsDiaryComment proDiaryReply;
    private int replyDiaryId;
 
    private int statIndex;

	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		ProDiaryService proDiaryService=(ProDiaryService)this.getBean("proDiaryService");
		ProDiaryReplyService diaryReplyService=(ProDiaryReplyService)this.getBean("proDiaryReplyService");
		if(friendSize==0)
		{
			friendSize=8;
			this.pageSize = friendSize;
		}
		if(pageNo < 1)
			  pageNo = 1;
		int userid=(Integer)this.get("currentUserid");
	   
		diary=proDiaryService.getProDiaryBySqlandDiaryid(proDiaryId,userid);
		
		friendPage=diaryReplyService.ProDiaryReplyListByDiaryId(proDiaryId, friendSize, pageNo);
		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(friendPage, pageNo,"getPage",friendSize);
		return "success";
	}
	/**
	 * 得到当前用户发表文章的列表
	 * @return
	 */
  public String getProDiaryList()
  { 
		ProDiaryService proDiaryService=(ProDiaryService)this.getBean("proDiaryService");
		
	   int userid=(Integer)this.get("currentUserid");
	   getDiaryReplyList();
	  return "curr";
  }
  /**
   * 得到主贴回复
   */
  public String getDiaryReplyList()
  {
		 
		ProDiaryReplyService diaryReplyService=(ProDiaryReplyService)this.getBean("proDiaryReplyService");
	
	  if(friendSize==0)
	  {
		  friendSize=8;
		  this.pageSize = friendSize;
	  }
	  if(pageNo < 1)
		  pageNo = 1;
	     
	     
	     friendPage=diaryReplyService.ProDiaryReplyListByDiaryId(proDiaryId,friendSize,pageNo);
	     
	     
	     
	     Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(friendPage, pageNo,"getPage",friendSize);

	  return "retply";
  }
  
public int getProDiaryId() {
	return proDiaryId;
}
public void setProDiaryId(int proDiaryId) {
	this.proDiaryId = proDiaryId;
}
public PaginationSupport getPage() {
	return page;
}
public void setPage(PaginationSupport page) {
	this.page = page;
}
public int getUserSize() {
	return userSize;
}
public void setUserSize(int userSize) {
	this.userSize = userSize;
}
public PaginationSupport getFriendPage() {
	return friendPage;
}
public void setFriendPage(PaginationSupport friendPage) {
	this.friendPage = friendPage;
}
public int getFriendSize() {
	return friendSize;
}
public void setFriendSize(int friendSize) {
	this.friendSize = friendSize;
}
public int getFreiendPageNo() {
	return freiendPageNo;
}
public void setFreiendPageNo(int freiendPageNo) {
	this.freiendPageNo = freiendPageNo;
}

public SnsDiaryComment getProDiaryReply() {
	return proDiaryReply;
}
public void setProDiaryReply(SnsDiaryComment proDiaryReply) {
	this.proDiaryReply = proDiaryReply;
}
public int getReplyDiaryId() {
	return replyDiaryId;
}
public void setReplyDiaryId(int replyDiaryId) {
	this.replyDiaryId = replyDiaryId;
}
public int getPageNo() {
	return pageNo;
}
public void setPageNo(int pageNo) {
	this.pageNo = pageNo;
}

public SnsDiary getDiary() {
	return diary;
}
public void setDiary(SnsDiary diary) {
	this.diary = diary;
}
public int getStatIndex() {
	return statIndex;
}
public void setStatIndex(int statIndex) {
	this.statIndex = statIndex;
}
public int getFalg() {
	return falg;
}
public void setFalg(int falg) {
	this.falg = falg;
}
public boolean isMFalg() {
	return mFalg;
}
public void setMFalg(boolean falg) {
	mFalg = falg;
}
public boolean isLFalg() {
	return lFalg;
}
public void setLFalg(boolean falg) {
	lFalg = falg;
}
public boolean isFFalg() {
	return fFalg;
}
public void setFFalg(boolean falg) {
	fFalg = falg;
}
public String getTiShi() {
	return tiShi;
}
public void setTiShi(String tiShi) {
	this.tiShi = tiShi;
}
public String getPageString() {
	return pageString;
}
public void setPageString(String pageString) {
	this.pageString = pageString;
}
}
