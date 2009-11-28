package com.changpeng.core.user.action;

import java.util.Date;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.util.WaitWork;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.core.user.model.CoreUserDetail;

public class UserbaseUpdateAction extends AbstractAction{
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
	
	private String sign;
	
	private String fromorder;
	private int approve_type;
	
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	protected String go() throws Exception {
		UserService userService = (UserService)this.getBean("userService");
		CoreUser user=userService.getUserById(currentUserid);
		user.setUserName(username);
		if(currentRole==1){
			user.setGender(sex);
			user.setBirthday(birth);
		}
		user.setMobile(phone);
		user.setCardno(cardno);
		user.setEntno(entno);
		user.setSign(sign);
		userService.update(user);

		
		
		String usersession=get("USERSESSION").toString();
		usersession = currentUserid + "," + provinceid + "," + cityid + "," + districtid + "," + currentRole + "," + username + "," + usertype;
		set("USERSESSION", usersession);
		//保存生日和性别
		CoreUserDetail detail=userService.getUserDetailById(currentUserid);
		
		if(detail==null){
			detail=new CoreUserDetail();
			detail.setUserid(currentUserid);
		}
		detail.setUserAddress(address);
		detail.setSummary(summary);
		detail.setPostcode(postcode);
		
		detail.setQq(qq);
		detail.setMsn(msn);
		userService.saveOrupdate(detail);
		if(user.getUserRole()==1)
		message="恭喜您，您的个人资料修改成功！";
		else
			message="恭喜您，您的企业资料修改成功！";
		this.redirectURL="javascript:$.unblockUI()";
		
		
		if(fromorder!=null&&fromorder.equals("true")){
			user.setStatus((short)2);
			user.setApproveType(approve_type);
			
			
			int waitid=WaitWork.Sendwait(currentUserid, "用户认证", userService);
			user.setWaitid(waitid);
			
			userService.update(user);
//			this.message="ok";
			this.message="您的身份认证提交成功,请等待身份认证通过后再订购产品";
			this.redirectURL="javascript:$.unblockUI()";
		}
		return SUCCESS;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public void setEntno(String entno) {
		this.entno = entno;
	}

	public void setFromorder(String fromorder) {
		this.fromorder = fromorder;
	}

	public void setApprove_type(int approve_type) {
		this.approve_type = approve_type;
	}
	
}
