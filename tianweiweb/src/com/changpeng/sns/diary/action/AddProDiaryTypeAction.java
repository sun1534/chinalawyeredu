package com.changpeng.sns.diary.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUserDetail;
import com.changpeng.core.user.model.Userinfo;
import com.changpeng.sns.diary.model.SnsDiarytype;
import com.changpeng.sns.diary.service.ProDiaryTypeService;


public class AddProDiaryTypeAction extends AbstractAction {
    private String diaryType;
    private int proDiaryId;
    private String typeurl;
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		 ProDiaryTypeService proDiaryTypeService=(ProDiaryTypeService)this.getBean("proDiaryTypeService");
		 UserService userService=(UserService)this.getBean("userService");
		 System.out.println(diaryType);
		 int userid=currentUserid;
		 SnsDiarytype proDiaryType=new SnsDiarytype();
		 proDiaryType.setTypename(diaryType);
		 proDiaryType.setUserid(userid);
		 proDiaryTypeService.save(proDiaryType);
		 
		 
		 redirectURL="../diary/addDProiary!input.action";
		 message="添加类型成功";
		 return "success";
		
	}
	public String getDiaryType() {
		return diaryType;
	}
	public void setDiaryType(String diaryType) {
		this.diaryType = diaryType;
	}
	public int getProDiaryId() {
		return proDiaryId;
	}
	public void setProDiaryId(int proDiaryId) {
		this.proDiaryId = proDiaryId;
	}
	 
	public void setTypeurl(String typeurl) {
		this.typeurl = typeurl;
	}

}
