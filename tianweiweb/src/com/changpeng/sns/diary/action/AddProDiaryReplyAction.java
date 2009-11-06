package com.changpeng.sns.diary.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.Userinfo;
import com.changpeng.sns.diary.model.SnsDiary;
import com.changpeng.sns.diary.model.SnsDiaryComment;
import com.changpeng.sns.diary.service.ProDiaryReplyService;
import com.changpeng.sns.diary.service.ProDiaryService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 增加回复ACTION
 * lzh
 * @author lenovo
 *
 */
public class AddProDiaryReplyAction extends AbstractListAction  {
    private SnsDiaryComment proDiaryReply=new SnsDiaryComment();
    private int diaryid;
    private String content;
    protected int friendUserSize;

	protected PaginationSupport friendPage;
	private int diaryFalg;
	 private String pageString;
    Logger log=Logger.getLogger(AddProDiaryReplyAction.class);
	@Override
	protected String go() throws Exception {
		
		// TODO Auto-generated method stub
	    UserService userService=(UserService)this.getBean("userService");
		ProDiaryService proDiaryService=(ProDiaryService)this.getBean("proDiaryService");
		ProDiaryReplyService diaryReplyService=(ProDiaryReplyService)this.getBean("proDiaryReplyService");
		ActionContext ctx=ActionContext.getContext();
	   	HttpServletRequest request=(HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
//		int userid=currentUserid;

//	    Userinfo userInof=(Userinfo) userService.get(Userinfo.class, userid);
		proDiaryReply.setContent(content);
//		proDiaryReply.setUserinfo(userInof);
		proDiaryReply.setUserid(currentUserid);
		proDiaryReply.setReplyFloorid(0);
		proDiaryReply.setHiddenFlag((short)0);
		proDiaryReply.setAnonymousFlag((short)0);
		SnsDiary proDiary=(SnsDiary) proDiaryService.get(SnsDiary.class,diaryid);
		
		//更新回复数
	
		String sqlUpdate="UPDATE sns_diary SET reply_count=reply_count+1 WHERE diaryid="+diaryid;
		proDiaryService.updateProDiary(sqlUpdate);
		proDiaryReply.setDiaryid(proDiary.getDiaryid());
		proDiaryReply.setCreateTime(new Timestamp(System.currentTimeMillis()));
		diaryReplyService.save(proDiaryReply);
		

	  
		  if(friendUserSize==0)
		  {
			  friendUserSize=8;
			  this.pageSize = friendUserSize;
		  }
		  if(pageNo < 1)
			  pageNo = 1;
		  friendPage=diaryReplyService.ProDiaryReplyListByDiaryId(diaryid, friendUserSize, pageNo);
			Pagefoot pagefoot = new Pagefoot();
			pageString = pagefoot.packString(friendPage, pageNo,"getPage",friendUserSize);
		System.out.println(diaryFalg);
			if(diaryFalg==0){
		  return "success";
		}else
		{
			return "friend";
		}
	}
	public SnsDiaryComment getProDiaryReply() {
		log.debug(""+proDiaryReply);
		return proDiaryReply;
	}
	public void setProDiaryReply(SnsDiaryComment proDiaryReply) {
		this.proDiaryReply = proDiaryReply;
	}
	public int getReplyDiaryId() {
		return diaryid;
	}
	public void setReplyDiaryId(int proDiaryId) {
		this.diaryid = proDiaryId;
	}
	
	
	public int getDiaryid() {
		return diaryid;
	}
	public void setDiaryid(int diaryid) {
		this.diaryid = diaryid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFriendUserSize() {
		return friendUserSize;
	}
	public void setFriendUserSize(int friendUserSize) {
		this.friendUserSize = friendUserSize;
	}
	 
	public PaginationSupport getFriendPage() {
		return friendPage;
	}
	public void setFriendPage(PaginationSupport friendPage) {
		this.friendPage = friendPage;
	}
	public String getPageString() {
		return pageString;
	}
	public void setPageString(String pageString) {
		this.pageString = pageString;
	}
	public int getDiaryFalg() {
		return diaryFalg;
	}
	public void setDiaryFalg(int diaryFalg) {
		this.diaryFalg = diaryFalg;
	}

}
