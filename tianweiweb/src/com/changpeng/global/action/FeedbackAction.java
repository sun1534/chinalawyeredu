/**
 * 
 */
package com.changpeng.global.action;


import java.sql.Timestamp;


import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.global.model.GlobalCommentfeedback;

/**
 * @author yuanwentao
 *
 */
public class FeedbackAction extends AbstractAction {

	Logger log=Logger.getLogger(FeedbackAction.class);
	private static final long serialVersionUID = 1L;
	private String content;
	private String type;
	private String username;
	private String userphone;
	private int provinceid;
	private int cityid;
	private int districtid;
	private int schoolid;
	private int classid;
	private int resultuserid;
	
	

	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		if(username==null||username.trim().equals("")){
	    	message = "请填写您的姓名！";
	    	return "message";
	    }
		else if(userphone==null||userphone.trim().equals("")){
	    	message = "请填写您的电话！";
	    	return "message";
	    }
		else if(provinceid==-1){
	    	message = "请选择省份！";
	    	return "message";
	    }
		else if(content==null||content.trim().equals("")){
	    	message = "请填写意见或建议！";
	    	return "message";
	    }
		else{
	    	message = " 谢谢您的关注，您给的意见或建议已经提交给系统管理员。";
	    	GlobalCommentfeedback feedback=new GlobalCommentfeedback();
	    	feedback.setCommenttime(new Timestamp(System.currentTimeMillis()));
	    	feedback.setContent(content);
	    	feedback.setIp(userip);
	    	feedback.setType(type);
	    	feedback.setUsername(username);
	    	if(currentUserid!=0){
	    		feedback.setUserid(currentUserid);
	    	}
	    	service.save(feedback);
	    }
		this.redirectURL="../about/feedback.html";
		return SUCCESS;
	}

	@Override
	protected String getin() {
		//this.redirectURL="..";
		return INPUT;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public FeedbackAction() {
		this.rightCode="LOGIN";
	}

	public int getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}

	public int getCityid() {
		return cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	public int getDistrictid() {
		return districtid;
	}

	public void setDistrictid(int districtid) {
		this.districtid = districtid;
	}

	public int getSchoolid() {
		return schoolid;
	}

	public void setSchoolid(int schoolid) {
		this.schoolid = schoolid;
	}

	public int getClassid() {
		return classid;
	}

	public void setClassid(int classid) {
		this.classid = classid;
	}

	public int getResultuserid() {
		return resultuserid;
	}

	public void setResultuserid(int resultuserid) {
		this.resultuserid = resultuserid;
	}
	

}
