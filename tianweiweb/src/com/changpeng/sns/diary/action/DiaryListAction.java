package com.changpeng.sns.diary.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.core.user.model.Userinfo;
import com.changpeng.sns.diary.model.SnsDiary;
import com.changpeng.sns.diary.model.SnsDiaryComment;
import com.changpeng.sns.diary.model.SnsDiarytype;
import com.changpeng.sns.diary.service.ProDiaryService;
import com.changpeng.sns.diary.service.ProDiaryTypeService;


public class DiaryListAction extends AbstractListAction {
	private PaginationSupport diaryPage;
	private String pageString;

	private List<SnsDiarytype> diarytypelist; 
	private List<SnsDiary> diarylist;

	private String username;

	@Override
	protected String go() throws Exception {
		
		ProDiaryService diaryService = (ProDiaryService) this.getBean("proDiaryService");
		ProDiaryTypeService diaryTypeService = (ProDiaryTypeService) this.getBean("proDiaryTypeService");
	
		UserService userService = (UserService) this.getBean("userService");
        
		if(viewUserid!=0){
			CoreUser userinfo= (CoreUser)userService.get(CoreUser.class, viewUserid);
			if(userinfo!=null){
				username = userinfo.getUserName();
			}
		}
		
	    if(pageNo<1)  pageNo=1;
		diaryPage = diaryService.getCurrProDiaryListByUserID(currentUserid,pageSize, pageNo);
		diarylist = diaryPage.getItems();
		
		diarytypelist = diaryTypeService.getDiaryListByUserId(currentUserid);	
		
		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(diaryPage, pageNo,"getPage");	
		
		if(diarylist!=null){
			int diaysize = diarylist.size();
			for(int i=0;i<diaysize;i++){
				SnsDiary diary = new SnsDiary();
				diary = diarylist.get(i);
				
				//获取摘要
				if(diary.getContent()!=null && !diary.getContent().equals("")){
					if(diary.getContent().length()>300){
						diary.setContent(diary.getContent().substring(0, 300)+"...");
					}else{
						diary.setContent(diary.getContent()+"...");
					}				
				}
				
				//获取评论数
				if(diary.getDiaryid()!=0){
					DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SnsDiaryComment.class);
					detachedCriteria.add(Restrictions.eq("diaryid", diary.getDiaryid()));
					int replycount=0;
					replycount = diaryService.getCountByCriteria(detachedCriteria);
					diary.setReplyCount(replycount);
				}
			}	
		
		}
		

		return SUCCESS;

	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public PaginationSupport getDiaryPage() {
		return diaryPage;
	}


	public void setDiaryPage(PaginationSupport diaryPage) {
		this.diaryPage = diaryPage;
	}


	public String getPageString() {
		return pageString;
	}


	public List<SnsDiarytype> getDiarytypelist() {
		return diarytypelist;
	}


	public void setDiarytypelist(List<SnsDiarytype> diarytypelist) {
		this.diarytypelist = diarytypelist;
	}


	public List<SnsDiary> getDiarylist() {
		return diarylist;
	}


	public void setDiarylist(List<SnsDiary> diarylist) {
		this.diarylist = diarylist;
	}
}
