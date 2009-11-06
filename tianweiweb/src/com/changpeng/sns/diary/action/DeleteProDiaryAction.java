package com.changpeng.sns.diary.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.sns.diary.model.SnsDiary;
import com.changpeng.sns.diary.service.ProDiaryService;


public class DeleteProDiaryAction extends AbstractAction {
     private int diaryid;
     
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		 ProDiaryService diaryService=(ProDiaryService)this.getBean("proDiaryService"); 
//		 SnsDiary diary=new SnsDiary();
//	     diary.setDiaryid(diaryid);
		 diaryService.deleteProDiary(diaryid);
	     redirectURL="../diary/diarylist.action";
	     message="删除成功";
		 return "success";
	}
	public int getDiaryid() {
		return diaryid;
	}
	public void setDiaryid(int diaryid) {
		this.diaryid = diaryid;
	}

}
