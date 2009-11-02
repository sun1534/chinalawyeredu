package com.changpeng.lessons.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.*;

public class ReplyCreateAction extends AbstractAction{
	private String replycontent;
	private int lessonid;
	private String type;
	public void setType(String type) {
		this.type = type;
	}
	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}
	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}
	public ReplyCreateAction(){
		
		this.rightCode = "locale";
	}
	@Override
	protected String go() throws Exception {
		BasicService service = (BasicService) this.getBean("basicService");
		Lessons lessons=(Lessons)service.get(Lessons.class, lessonid);
		Lessonreply reply=new Lessonreply();
	
		reply.setReplycontent(replycontent);
		reply.setReplyuser(getLoginUser().getLoginname());
		reply.setLessons(lessons);
		reply.setReplytime(new java.util.Date());
		service.save(reply);
		this.message="评论发表成功";
		
		if(type!=null&&"online".equals(type))
			this.nextPage="../lessons/lessonsOnlineView.pl?lessonid="+lessonid;
		else
			this.nextPage="../lessons/lessonsLocalView.pl?lessonid="+lessonid;
		return SUCCESS;
	}

}
