/**
 * 
 */
package com.changpeng.core.regist.action;

import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.core.user.model.CoreUserDetail;

/**
 * @author zhhw 密码找回
 */
public class FindpwdAction extends AbstractAction {
	/**
	 * 
	 */
	private static Logger log = Logger.getLogger(FindpwdAction.class);
	private static final long serialVersionUID = 1L;
	private String username;
	
	private String question;
	private String answer;
	/**
	 * 
	 */
	public FindpwdAction() {
		this.rightCode = "PUBLIC";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		UserService uservice=(UserService)this.getBean("userService");
		CoreUser user=uservice.getUserByLoginName(username);
		if(user!=null){
			CoreUserDetail detail=uservice.getUserDetailById(user.getId());
			if(answer.equals(detail.getAnswer())){
				//发送邮件
				detail.getEmail();
				message="密码已经发送到您的邮箱！";
				this.redirectURL="../index.html";
				return "message";
			}else{
				message="验证答案输入错误！";
				return "message";
			}
			
		}else{
			message="输入错误！";
			return "message";
		}
//		return SUCCESS;
	}

	protected String getin(){
		UserService uservice=(UserService)this.getBean("userService");
		CoreUser user=uservice.getUserByLoginName(username);
		if(user!=null){
			CoreUserDetail detail=uservice.getUserDetailById(user.getId());
			question=detail.getQuestion();
			
		}else{
			message="用户名输入错误！";
			return "plainmsg";
		}
		return "input";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

}
