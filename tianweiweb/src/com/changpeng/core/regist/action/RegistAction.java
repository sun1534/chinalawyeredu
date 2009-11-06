/**
 * 
 */
package com.changpeng.core.regist.action;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.sysdata.CommonData;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.core.user.model.CoreUserDetail;

/**
 */
public class RegistAction extends AbstractAction {
	/**
	 * 
	 */
	private static  Logger log = Logger.getLogger(RegistAction.class);
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String repassword;
	private String question;
	private String answer;
	private String email;
	private boolean agreerule;
	private String cardno;
	private String address;
	private String postcode;
	private String phone;
	private String qq;
	private String msn;
	
	private String name;
	
	private int role = 0;

	public RegistAction() {
		this.rightCode = "PUBLIC";
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	@Override
	protected String go() throws Exception {
		String rstr = ERROR;
		UserService uservice=(UserService)this.getBean("userService");
		CoreUser u=uservice.getUserByLoginName(username);
		if (role != 1 && role != 2 ) {
			message = "请选择注册类型！";
		}else if(username.length()<6){
			message = "用户名必须大于6位";
		}else if(password.length()<6||!password.equals(repassword)){
			message = "密码设置有误，请重新设置密码！";
		}else if(u!=null){
			message="用户名已经注册,请重新输入";
		}else if(!agreerule){
			message="请同意注册协议完成注册！";
		}else{
			log.info("agreerule:"+agreerule);
			
			CoreUser user=new CoreUser();
			user.setBirthday(new Timestamp(System.currentTimeMillis()));
			user.setProvinceId(0);
			user.setCityId(0);
			user.setDistrictId(0);
			user.setGender((short)0);
			if(role==1){
				user.setPic(CommonData.DEFAULT_FAM_PIC);
			}else{
				user.setPic(CommonData.DEFAULT_ENT_PIC);
			}
			user.setLogo(CommonData.DEFAULT_ENT_LOGO);
			user.setPrivateFlag("");
			user.setMobile("");
			user.setUserName(name);
			user.setLoginName(username);
			user.setPwd(password);
			user.setUserRole((short)role);
			user.setRegTime(new Timestamp(System.currentTimeMillis()));
			user.setRegIp(this.userip);
			user.setSign("");
			user.setStatus((short)1);
			user.setUserType((short)1);
			user.setCardno(cardno);
			
			CoreUserDetail userdetail=new CoreUserDetail();
			userdetail.setAdages("");
			userdetail.setAnimations("");
			userdetail.setAnswer(answer);
			userdetail.setBooks("");
			userdetail.setEmail(email);
			userdetail.setFetion("");
			userdetail.setGames("");
			userdetail.setInterest("");
			userdetail.setMovies("");
			userdetail.setMsn(msn);
			userdetail.setMusics("");
			userdetail.setPhone(phone);
			userdetail.setPostcode(postcode);
			userdetail.setQq(qq);
			userdetail.setQuestion(question);
			userdetail.setSports("");
			userdetail.setSummary("");
			userdetail.setUserAddress(address);
			
			uservice.regUser(user, userdetail);
			message = "感谢您的注册！";
			this.redirectURL = "../index.html";
		}
		
		rstr = SUCCESS;
		return rstr;
	}

	@Override
	protected String getin() {
		return INPUT;
	}

	public static void setLog(Logger log) {
		RegistAction.log = log;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAgreerule(boolean agreerule) {
		this.agreerule = agreerule;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public void setName(String name) {
		this.name = name;
	}

}
