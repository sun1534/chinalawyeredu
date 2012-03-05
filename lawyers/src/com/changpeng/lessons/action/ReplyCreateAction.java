package com.changpeng.lessons.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.*;

/**
 * 
 * 对课程的回复
 * @author 华锋
 *
 */
public class ReplyCreateAction extends AbstractAction{
	private String replycontent;
	private int lessonid;
	public String getTopbarpic(){
		return com.changpeng.common.Constants.TOP_BAR_PIC;
	}
	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}
	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}
	public ReplyCreateAction(){
		
	
	}
	@Override
	protected String go() throws Exception {
		BasicService service = (BasicService) this.getBean("basicService");
	
		Lessonreply reply=new Lessonreply();
	
		reply.setReplycontent(replycontent);
		reply.setReplyuser(getLoginUser().getLawyername());
		reply.setLessonid(lessonid);
		reply.setReplyuserid(getLoginUser().getLawyerid());
		
	
		reply.setReplytime(new java.sql.Timestamp(System.currentTimeMillis()));
		service.save(reply);
		this.message="您的评论发表成功";
	
		this.nextPage="../lessons/lessonsView.pl?lessonid="+lessonid;

		return SUCCESS;
	}

}
