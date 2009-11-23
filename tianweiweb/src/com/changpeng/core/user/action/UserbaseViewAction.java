package com.changpeng.core.user.action;

import java.util.Date;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.core.user.model.CoreUserDetail;

public class UserbaseViewAction extends AbstractAction{
	private String username;
	private Date birth;
	private Short sex=1;
	
	private String summary;
	private String address;
	private String postcode;
	private String phone;
	private String cardno;
	private String entno;
	private String qq;
	private String msn;
	private int status;
	private String sign;
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	protected String go() throws Exception {
		UserService userService = (UserService)this.getBean("userService");

		CoreUser user=userService.getUserById(currentUserid);
		CoreUserDetail up=userService.getUserDetailById(currentUserid);
		
		username=user.getUserName();
		birth=user.getBirthday();
		sex=user.getGender();
		phone=user.getMobile();
		cardno=user.getCardno();
		entno=user.getEntno();
		status=user.getStatus().intValue();
		
		summary=up.getSummary();
		address=up.getUserAddress();
		postcode=up.getPostcode();
		sign=user.getSign();
		qq=up.getQq();
		msn=up.getMsn();
		
		
		return SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public Date getBirth() {
		return birth;
	}

	public Short getSex() {
		return sex;
	}

	public String getSummary() {
		return summary;
	}

	public String getAddress() {
		return address;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getPhone() {
		return phone;
	}

	public String getCardno() {
		return cardno;
	}

	public String getQq() {
		return qq;
	}

	public String getMsn() {
		return msn;
	}

	public String getEntno() {
		return entno;
	}

	public int getStatus() {
		return status;
	}

	
}
