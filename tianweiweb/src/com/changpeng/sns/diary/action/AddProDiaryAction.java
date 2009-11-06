package com.changpeng.sns.diary.action;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.util.WaitWork;
import com.changpeng.core.model.CorePublish;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.core.user.model.CoreUserDetail;
import com.changpeng.core.user.model.Userinfo;
import com.changpeng.sns.diary.model.SnsDiary;
import com.changpeng.sns.diary.model.SnsDiarytype;
import com.changpeng.sns.diary.service.ProDiaryService;
import com.changpeng.sns.diary.service.ProDiaryTypeService;

public class AddProDiaryAction extends AbstractAction {
	private SnsDiary proDiary = new SnsDiary();
	private Map<Integer, String> diaryTypeList = new HashMap<Integer, String>();
	private List channels;

	private boolean needverify;
	private String cardno;
	private String entno;
	
	private boolean totv;
	
	private int channel;
	

	@Override
	protected String go() throws Exception {
		ProDiaryService proDiaryService = (ProDiaryService) this.getBean("proDiaryService");
		int userid = currentUserid;
		proDiary.setUserid(userid);
		proDiary.setTop((short) 0);
		proDiary.setCreateTime(new Timestamp(System.currentTimeMillis()));
		proDiary.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		proDiary.setCreateIp("");
		proDiary.setClickCount(0);
		proDiary.setReplyCount(0);
		proDiary.setPrivateFlag((short) 0);
		proDiary.setStatusid((short)1);
		proDiaryService.saveProDiary(proDiary, currentUsername);

		if(totv){
			CorePublish publish=new CorePublish();
			publish.setChannelid(this.channel);
//			publish.setContenttype(1);
//			publish.setContentid(proDiary.getDiaryid());
			publish.setCreatetime(new Timestamp(System.currentTimeMillis()));
			publish.setEndtime(new Timestamp(System.currentTimeMillis()));
			publish.setStarttime(new Timestamp(System.currentTimeMillis()));
			publish.setFee(0.0);
			publish.setUserid(this.currentUserid);
			
			publish.setProductid(0);
			
			UserService userservice = (UserService) this.getBean("userService");
			CoreUser user=userservice.getUserById(currentUserid);
			if(user.getStatus().intValue()==1){
				user.setStatus((short)2);
				user.setCardno(cardno);
				if(currentRole==2){
					user.setEntno(entno);
				}
				userservice.update(user);
				
			}
			if(user.getStatus().intValue()==0){
				publish.setStatusid((short)1);
			}else{
				publish.setStatusid((short)2);
			}
//			publish.setTitle(proDiary.getTile());
//			publish.setContent(proDiary.getContent());
			service.save(publish);
			WaitWork.Sendwait(currentUserid, proDiary.getTile(), userservice);
		}
		return "success";
	}

	/**
	 * 点击发表博客按钮所做的处理 查询出所有主贴类型
	 */
	public String getin() {

		int userid = currentUserid;
		Userinfo userinfo = userutil.getUserinfo(userid);
		ProDiaryTypeService diaryTypeService = (ProDiaryTypeService) this
				.getBean("proDiaryTypeService");

		List list = diaryTypeService.getDiaryListByUserId(0);
		if (null == list || list.size() <= 0) {
			SnsDiarytype snsDiarytype = diaryTypeService
					.getDefaultDiaryType(userid);
			if (snsDiarytype == null) {
				diaryTypeService.addDefaultDiaryType(userid);
				snsDiarytype = diaryTypeService.getDefaultDiaryType(userid);
				diaryTypeList.put(snsDiarytype.getId(), snsDiarytype
						.getTypename());
			}
		} else {
			int count = list.size();
			for (int i = 0; i < count; i++) {
				SnsDiarytype type = (SnsDiarytype) list.get(i);
				diaryTypeList.put(type.getId(), type.getTypename());
			}
		}
		
		/*
		 *获取栏目列表 
		 */
		ProDiaryService proDiaryService = (ProDiaryService) this.getBean("proDiaryService");
		this.channels = proDiaryService.getChannels();
		
		if(userinfo.getStatus().intValue()==1){
			needverify=true;
			CoreUser ud=(CoreUser)service.get(CoreUser.class, this.currentUserid);
			if(ud!=null&&ud.getCardno()!=null){
				cardno=ud.getCardno();
			}else{
				cardno="";
			}
			if(ud!=null&&ud.getEntno()!=null){
				entno=ud.getEntno();
			}else{
				entno="";
			}
		}else{
			needverify=false;
		}
		
		return "adddir";
	}

	public boolean isNeedverify() {
		return needverify;
	}

	public void setNeedverify(boolean needverify) {
		this.needverify = needverify;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public SnsDiary getProDiary() {
		return proDiary;
	}

	public void setProDiary(SnsDiary proDiary) {
		this.proDiary = proDiary;
	}

	public Map<Integer, String> getDiaryTypeList() {
		return diaryTypeList;
	}

	public void setDiaryTypeList(Map<Integer, String> diaryTypeList) {
		this.diaryTypeList = diaryTypeList;
	}

	public List getChannels() {
		return channels;
	}
	
	public void setTotv(boolean totv) {
		this.totv = totv;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public String getEntno() {
		return entno;
	}

	public void setEntno(String entno) {
		this.entno = entno;
	}

}
