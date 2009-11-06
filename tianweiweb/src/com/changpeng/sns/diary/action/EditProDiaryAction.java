package com.changpeng.sns.diary.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.core.user.model.Userinfo;
import com.changpeng.sns.diary.model.SnsDiary;
import com.changpeng.sns.diary.model.SnsDiarytype;
import com.changpeng.sns.diary.service.ProDiaryService;
import com.changpeng.sns.diary.service.ProDiaryTypeService;



public class EditProDiaryAction extends AbstractListAction  {
    private int diaryid;

    private PaginationSupport replyPage;

	private SnsDiary proDiary;
	private int replPageNo;
	private int replPageSize;

	private Map<Integer, String> diaryTypeList=new HashMap<Integer,String>();
	@Override
	protected String go() throws Exception {
		ProDiaryService proDiaryService=(ProDiaryService)this.getBean("proDiaryService");
		String str=proDiary.getContent();
		 
		 if(str.length()>100)
		 {
			 str=str.substring(0,100);
		 }
		String sql="UPDATE sns_diary set tile='"+proDiary.getTile()+"',content='"+proDiary.getContent()+"',private_flag="+proDiary.getPrivateFlag()+" where diaryid="+diaryid;
		 System.out.println(sql);
		proDiaryService.updateProDiary(sql);
		 
	    return "success";
		
	}
	
//	public String goDiarylist()
//	{
//		ProDiaryService proDiaryService=(ProDiaryService)this.getBean("proDiaryService");
//		int userid=(Integer)this.get("currentUserid"); 
//		page=proDiaryService.getProDiaryListByDiaryId(pageSize, pageNo);
//		ProDiary proDiary=(ProDiary)page.getItems().get(0);
//		
//		List item=(List)proDiary.getProDiaryreplies();
//		replyPage=new PaginationSupport(item,item.size(),replPageSize,replPageNo);
//		if(proDiary.getUserinfo().getUserid()==userid)
//		{
//			return "curruserid";
//		}else
//		{
//			return "frienduserid";
//		}
//	}
	/**
	 * 点击编辑按钮所做的处理
	 * 从session中找到ProDiary和ProDiaryType列表
	 */
	public String updateProDiary()
	{
		 ProDiaryService proDiaryService=(ProDiaryService)this.getBean("proDiaryService");
		 ProDiaryTypeService diaryTypeService=(ProDiaryTypeService)this.getBean("proDiaryTypeService");
		  int userid=currentUserid;
		  Userinfo userinfo = userutil.getUserinfo(userid);
		  List list=diaryTypeService.getDiaryListByUserId(0);
		  if(null==list||list.size()<=0)
		  {
//			  SnsDiarytype snsDiarytype = diaryTypeService.getDefaultDiaryType(0);
//			  
//			  if(snsDiarytype==null){
//				  diaryTypeService.addDefaultDiaryType(userid);
//				  snsDiarytype = diaryTypeService.getDefaultDiaryType(userid);
//				  diaryTypeList.put(snsDiarytype.getId(), snsDiarytype.getTypename());
//			  }
//			  List listType=diaryTypeService.getDiaryList();
//			  if(null!=listType&&listType.size()>0)
//			  {
//				  int count=listType.size();
//				  for(int i=0;i<count;i++)
//				  {   
//					  SnsDiarytype type=(SnsDiarytype) listType.get(i);
//					  diaryTypeList.put(type.getId(), type.getTypename());
//					  SnsDiarytype newType=new SnsDiarytype();
//					   newType.setUserid(userid);
//					   newType.setTypename(type.getTypename());
//					 
//					  diaryTypeService.save(newType);
//				  }
//			  }
		  }else
		  {
				  int count=list.size();
				  for(int i=0;i<count;i++)
				  {   
					  SnsDiarytype type=(SnsDiarytype) list.get(i);
					  diaryTypeList.put(type.getId(), type.getTypename());
					   
				  }
			   
		  }
		 
          proDiary=proDiaryService.findProDiary(diaryid);
		 return "updatediary";
	}
	
	

	public int getDiaryid() {
		return diaryid;
	}

	public void setDiaryid(int diaryid) {
		this.diaryid = diaryid;
	}

	public PaginationSupport getReplyPage() {
		return replyPage;
	}
	public void setReplyPage(PaginationSupport replyPage) {
		this.replyPage = replyPage;
	}

	public SnsDiary getProDiary() {
		return proDiary;
	}
	public void setProDiary(SnsDiary proDiary) {
		this.proDiary = proDiary;
	}
	public int getReplPageNo() {
		return replPageNo;
	}
	public void setReplPageNo(int replPageNo) {
		this.replPageNo = replPageNo;
	}
	public int getReplPageSize() {
		return replPageSize;
	}
	public void setReplPageSize(int replPageSize) {
		this.replPageSize = replPageSize;
	}

	public Map<Integer, String> getDiaryTypeList() {
		return diaryTypeList;
	}

	public void setDiaryTypeList(Map<Integer, String> diaryTypeList) {
		this.diaryTypeList = diaryTypeList;
	}

	
	
}
