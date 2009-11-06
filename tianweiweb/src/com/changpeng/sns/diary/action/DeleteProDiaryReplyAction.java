package com.changpeng.sns.diary.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.sns.diary.model.SnsDiaryComment;
import com.changpeng.sns.diary.service.ProDiaryReplyService;
import com.changpeng.sns.diary.service.ProDiaryService;

public class DeleteProDiaryReplyAction  extends AbstractAction {
    private int id;
    private int diaryid;
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		ProDiaryReplyService replyService=(ProDiaryReplyService) this.getBean("proDiaryReplyService");
		replyService.delete(SnsDiaryComment.class, id);
		ProDiaryService proDiaryService=(ProDiaryService)this.getBean("proDiaryService");
	    String sql="UPDATE sns_diary SET reply_count=reply_count-1 WHERE diaryid="+diaryid;
	    System.out.println("主贴ID  "+diaryid);
        System.out.println("跟贴ID   "+id);
		proDiaryService.updateProDiary(sql);
		return "successli";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDiaryid() {
		return diaryid;
	}
	public void setDiaryid(int diaryid) {
		this.diaryid = diaryid;
	}
	 
	 
	
}
