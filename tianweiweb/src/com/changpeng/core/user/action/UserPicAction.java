package com.changpeng.core.user.action;

import java.util.Date;

import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractUploadAction;
import com.changpeng.common.util.ImageUtil;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.Userinfo;

public class UserPicAction extends AbstractUploadAction{
	private Userinfo userpersonal;
	private static Logger LOG = Logger.getLogger(AbstractUploadAction.class);
	private String picurl;
	@Override
	protected String go() throws Exception {
		String ext=dstPath[0].substring(dstPath[0].lastIndexOf(".")+1).toLowerCase();
		if(!ext.equals("jpg")&&!ext.equals("bmp")&&!ext.equals("jpeg")){
			message="您上传的图片格式有误，请上传jpg、jpeg、bmp格式的图片！";
			UserService userService = (UserService)this.getBean("userService");
			userpersonal = userService.getUserinfoById(currentUserid);
			return "input";
		}
		if(ImageUtil.getWidth(dstPath[0])<480){
			this.picurl=filenames[0];
		}else{
			
			String newFileName = currentUserid+"_" +new Date().getTime()+ "_orgian_" + dstPath[0].substring(dstPath[0].lastIndexOf("."));
			
			String realpath = getRealPath("origin", currentUserid);
			
			picurl = getRelativePath("origin", currentUserid) + newFileName;
			String orgtmpfile=realpath+newFileName;
			ImageUtil.resize(dstPath[0], orgtmpfile, 480);
		}
		java.lang.Thread.sleep(1000);
		return SUCCESS;
	}
	
	public String getin(){
		UserService userService = (UserService)this.getBean("userService");
		userpersonal = userService.getUserinfoById(currentUserid);
		return "input";
	}

	public Userinfo getUserpersonal() {
		return userpersonal;
	}
	private String getLogoVersion(String pic){
		String s="";
		try{
			s=pic.substring(pic.lastIndexOf("_")+1, pic.lastIndexOf("."));
			s=Integer.toString(Integer.parseInt(s)+1);
		}catch(Exception e){
			s="0";
		}
		return s;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
}
