/**
 * 消息中心
 * 
 */
package com.changpeng.core.message.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.GeneralmessageService;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUserPersonal;

public class MsgCenterAction extends AbstractAction{
	 Logger log=Logger.getLogger(MsgCenterAction.class);

     
//     private int[] classseli;
     private int[] parentseli;
     private int[] teacherseli;
	 private int[] studentseli;
	 
	 /**
	  * 发送类型
	  */
     private int[] msgtype;
     
     /**
      * 发送标记, sendmsg = send 发送
      */
     private String sendmsg;
     private String msgcontent;
     
     private int syscount=0;
     private int msgcount=0;
     private int leavecount=0;
     
     
     
	 public MsgCenterAction() {
		 moduleid=12;
	}
	@Override
	 protected String go(){
		String result="error";
		if(sendmsg==null||!sendmsg.equals("send")){
			UserService userservice=(UserService)this.getBean("userService");
			CoreUserPersonal up=userservice.getUserPersonalById(currentUserid);
			if(up!=null){
				syscount=up.getCountSysmsgUnread();
				msgcount=up.getCountMsgUnread();
				leavecount=up.getCountComrepUnread();
			}
			result=SUCCESS;
		}else{
			message="发送失败";
			if(msgcontent==null||msgcontent.equals("")){
				message="发送消息不能为空！";
			}else if(msgtype==null||msgtype.length==0){
				message="必须选择发送类型！";
			}else if((teacherseli==null||teacherseli.length==0)&&(parentseli==null||parentseli.length==0)&&(studentseli==null||studentseli.length==0)){
				message="必须选择发送对象！";
			}else{
				message="发送成功";
				this.redirectURL="../message/shortmessagelist.action?type=2";
				for(int i=0;i<msgtype.length;i++){
					if(msgtype[i]==2){
						//站内短信
						GeneralmessageService msgService = (GeneralmessageService) this.getBean("generalmessageService");
						
						//发送目标用户集合到一起
						int[] dest=new int[(teacherseli!=null?teacherseli.length:0)+(parentseli!=null?parentseli.length:0)+(studentseli!=null?studentseli.length:0)];
						if(teacherseli!=null&&teacherseli.length>0){
							System.arraycopy(teacherseli, 0, dest, 0, teacherseli.length);
						}
						if(parentseli!=null&&parentseli.length>0){
							System.arraycopy(parentseli, 0, dest, teacherseli!=null?teacherseli.length:0, parentseli.length);
						}
						if(studentseli!=null&&studentseli.length>0){
							System.arraycopy(studentseli, 0, dest, (teacherseli!=null?teacherseli.length:0)+(parentseli!=null?parentseli.length:0), studentseli.length);
						}
						//dest[dest.length-1]=currentUserid;
						
						//改掉群发消息
						for(int desti:dest){
							msgService.saveShortMessage(currentUserid, new int[]{currentUserid,desti}, msgcontent, false);
						}
					}else if(msgtype[i]==3){
						//邮件 暂未实现
					}
				}
			}
			result="message";
		}
		return result;
	}


	public void setParentseli(int[] parentseli) {
		this.parentseli = parentseli;
	}
	public void setStudentseli(int[] studentseli) {
		this.studentseli = studentseli;
	}
	public void setMsgtype(int[] msgtype) {
		this.msgtype = msgtype;
	}
	public void setSendmsg(String sendmsg) {
		this.sendmsg = sendmsg;
	}
	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}

	public int[] getTeacherseli() {
		return teacherseli;
	}
	public void setTeacherseli(int[] teacherseli) {
		this.teacherseli = teacherseli;
	}
	public int getSyscount() {
		return syscount;
	}
	public int getMsgcount() {
		return msgcount;
	}
	public int getLeavecount() {
		return leavecount;
	}
}
