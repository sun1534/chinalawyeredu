package com.changpeng.common.action;

import java.util.ArrayList;
import java.util.List;

import com.changpeng.common.util.UserUtil;
import com.changpeng.core.friend.model.CoreFriend;
import com.changpeng.core.service.FriendService;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.core.user.model.Userinfo;


public class GetMemberAction extends AbstractAction {
	private List<SUser> members;
	private String type;
	private int classid;

	@Override
	protected String go() throws Exception {
		members = new ArrayList<SUser>();
		UserService service=(UserService)this.getBean("userService");
		FriendService friendService=(FriendService)this.getBean("friendService");
		UserUtil userutil=UserUtil.getInstance();
		if(type!=null&&type.equals("4")){
			List friends=friendService.getMyFriends(this.currentUserid, 1000);
			for(Object o:friends){
				 CoreFriend friendinfo=(CoreFriend)o;
//				 members.add(new SUser(friendinfo.getId(),friendinfo.getUserName()+jxq.common.sysdata.CommonData.UserRoles.get(Short.parseShort(friendinfo.getUserRole()+""))));
				 members.add(new SUser(friendinfo.getFriendUserid(),userutil.getUserinfo(friendinfo.getFriendUserid()).getUserName()+com.changpeng.common.sysdata.CommonData.UserRoles.get(Short.parseShort(friendinfo.getFriendUserRole()+""))));
			}
		}

		return SUCCESS;
	}

	public List getMembers() {
		return members;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setClassid(int classid) {
		this.classid = classid;
	}
	public static class SUser{
		private int id;
		private String name;
		public SUser(){
			
		}
		public SUser(int id,String name){
			this.id=id;
			this.name=name;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
	
}
