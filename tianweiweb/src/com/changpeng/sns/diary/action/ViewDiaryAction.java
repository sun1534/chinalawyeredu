package com.changpeng.sns.diary.action;

import java.io.Reader;
import java.sql.Clob;
import java.util.List;


import org.apache.log4j.Logger;

import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.JugeTime;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.Userinfo;
import com.changpeng.sns.diary.model.SnsDiary;
import com.changpeng.sns.diary.model.SnsDiaryComment;
import com.changpeng.sns.diary.model.SnsDiarytype;
import com.changpeng.sns.diary.service.ProDiaryReplyService;
import com.changpeng.sns.diary.service.ProDiaryService;
import com.changpeng.sns.diary.service.ProDiaryTypeService;


public class ViewDiaryAction extends AbstractListAction {
	Logger log=Logger.getLogger(ViewDiaryAction.class);
	private int diarytypeId;
	public int diaryid;
	private SnsDiary diary;
	
	protected PaginationSupport friendPage=new PaginationSupport();
	private List<SnsDiarytype> diarytypelist;
	private int viewUserid;
	private String username;
	private JugeTime jt;
	String content2;
	private String type=null;
	private PaginationSupport page;
	private String pageString;

	@Override
	protected String go() throws Exception {
		jt=new JugeTime();
		ProDiaryService diaryService = (ProDiaryService) this.getBean("proDiaryService");
		ProDiaryReplyService diaryReplyService = (ProDiaryReplyService) this.getBean("proDiaryReplyService");
//		int currentUserid = (Integer) this.get("currentUserid");
//		UserService userService = (UserService) this.getBean("userService");
		
//		if(type==null)
//		if(viewUserid==0)
//		{    int id =currentUserid;
//			Userinfo userinfo= (Userinfo)diaryService.get(Userinfo.class, id);
//			username = userinfo.getUserName();
//		}else
//		{
//			if(viewUserid==currentUserid)
//			{   int id=currentUserid;
//				Userinfo userinfo= (Userinfo)diaryService.get(Userinfo.class, id);
//				username = userinfo.getUserName();
//
//			}else
//			{  int id =viewUserid;
//				Userinfo userinfo= (Userinfo)diaryService.get(Userinfo.class, id);
//				username = userinfo.getUserName();
//			}
//		}
		
		
		diary = (SnsDiary)diaryService.get(SnsDiary.class, diaryid);
//		Clob clob =diary.getContent();
//		content2 = clob2string(clob);
		
		
		log.debug(viewUserid);
		friendPage =diaryReplyService.ProDiaryReplyListByDiaryId(diaryid,pageSize, pageNo);
		
		Pagefoot pagefoot=new Pagefoot();
		pageString=pagefoot.packString(friendPage, pageNo,"getPage",8);

//		Integer curuserid = (Integer) this.get("currentUserid");
		ProDiaryTypeService diaryTypeService = (ProDiaryTypeService) this.getBean("proDiaryTypeService");
//		String opruseridStr = "";
		diarytypelist = diaryTypeService.getDiaryListByUserId(viewUserid);

		if(type==null)
		{
			return SUCCESS;
		}else
		{
			diarytypeId = 0;
			return "friendpage";
		}
		
	}
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public JugeTime getJt() {
		return jt;
	}

	public void setJt(JugeTime jt) {
		this.jt = jt;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public int getViewUserid() {
		return viewUserid;
	}


	public void setViewUserid(int viewUserid) {
		this.viewUserid = viewUserid;
	}

	public SnsDiary getDiary() {
		return diary;
	}

	public int getDiaryid() {
		return diaryid;
	}

	public void setDiaryid(int diaryid) {
		this.diaryid = diaryid;
	}

	public List<SnsDiarytype> getDiarytypelist() {
		return diarytypelist;
	}

	public PaginationSupport getFriendPage() {
		return friendPage;
	}

	public void setFriendPage(PaginationSupport friendPage) {
		this.friendPage = friendPage;
	}

	public int getDiarytypeId() {
		return diarytypeId;
	}

	public void setDiarytypeId(int diarytypeId) {
		this.diarytypeId = diarytypeId;
	}

	public static String clob2string(Clob clob) {
		StringBuffer sb = new StringBuffer(1024);
		Reader instream = null;
		try {
			instream = clob.getCharacterStream();
			char[] buffer = new char[(int) clob.length()];
			int length = 0;
			while ((length = instream.read(buffer)) != -1) {
				sb.append(buffer);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (instream != null)
					instream.close();
			} catch (Exception dx) {
				instream = null;
			}
			return sb.toString();
		}
	}

	public String getContent2() {
		return content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}


	public String getPageString() {
		return pageString;
	}

}

