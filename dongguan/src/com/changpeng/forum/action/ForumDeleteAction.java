/**
 * ForumCreateAction.java
 */
package com.changpeng.forum.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Forum;

/**
 * 删除帖子
 * 
 * @author 华锋
 * 2008-5-5 下午06:36:36
 *
 */
public class ForumDeleteAction extends AbstractAction{
	//BasicService basicService = null;
	
	
	public ForumDeleteAction(){
		this.rightCode="forumDelete";
		//basicService = (BasicService) getBean("basicService");
	}
	
	/**
	 * 回复帖子的帖子id
	 */
	private int mainforumid;
	
	
	/**
	 * @param mainforumid the mainforumid to set
	 */
	public void setMainforumid(int forumid) {
		this.mainforumid = forumid;
	}
	

	public int getMainforumid() {
		return this.mainforumid;
	}
	

	/**
	 * 删除帖子，如果是主题帖，删除所有跟帖
	 * @return
	 * @throws Exception
	 */
	public String go()throws Exception{
		
		//只有系统管理员才有删除帖子的权限
//		if(getLoginUser().getRoleid()!=3){
//			return "noright";
//		}

		
		
		BasicService basicService = (BasicService) getBean("basicService");
		
		Forum deleteforum=(Forum)basicService.get(Forum.class, mainforumid);
		if(deleteforum.getIsmain()){
			//将所有的跟帖删除
			basicService.execute("update com.changpeng.models.Forum forum set forum.delflag=true where forum.mainforum="+mainforumid);
		}else{//如果是跟帖，修改forumid为主题贴的id
			this.mainforumid=deleteforum.getMainforum();
			//修改主题帖的回复数减1；
			Forum themainforum=(Forum)basicService.get(Forum.class, mainforumid);
			themainforum.setReplycount(themainforum.getReplycount()-1);
			basicService.update(themainforum);
			
		}
		deleteforum.setDelflag(true);
		basicService.update(deleteforum);

		//删除的是主题帖，回到主题帖列表
		if(deleteforum.getIsmain()){
			return "list";
			
		}else{//回到这个帖子的主题贴列表
			
			return "view";
		}
	}


	
	
}
