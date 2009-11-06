package com.changpeng.core.user.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUserDetail;

public class SafePwdAction extends AbstractAction{

	private String question;
	private String answer;
	private String email;
	
	@Override
	protected String go() throws Exception {
		UserService uservice=(UserService)this.getBean("userService");
		CoreUserDetail userdetail=uservice.getUserDetailById(this.currentUserid);
		userdetail.setEmail(email);
		userdetail.setQuestion(question);
		userdetail.setAnswer(answer);
		uservice.update(userdetail);
		this.message="修改成功";
		return SUCCESS;
	}
	
	@Override
	public String getin(){
		UserService uservice=(UserService)this.getBean("userService");
		CoreUserDetail userdetail=uservice.getUserDetailById(this.currentUserid);
		this.email=userdetail.getEmail();
		this.question=userdetail.getQuestion();
		this.answer=userdetail.getAnswer();
		return "input";
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
