package com.changpeng.core.user.action;

import java.util.Date;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.core.user.model.CoreUserDetail;

public class VerifyApplyP1Action extends AbstractAction {

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
	
	protected String go() throws Exception {
		UserService userService = (UserService) this.getBean("userService");
		CoreUser user = userService.getUserById(currentUserid);

		CoreUserDetail up = userService.getUserDetailById(currentUserid);

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

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Short getSex() {
		return sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getEntno() {
		return entno;
	}

	public void setEntno(String entno) {
		this.entno = entno;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
