package com.changpeng.core.user.action;

import java.util.Date;

import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractUploadAction;
import com.changpeng.common.util.ImageUtil;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.core.user.model.Userinfo;

public class UserLogoAction extends AbstractUploadAction{

	private String picurl;
	private CoreUser user;
	@Override
	protected String go() throws Exception {
		user=(CoreUser)service.get(CoreUser.class, this.currentUserid);
		if(dstPath==null||dstPath.length==0){
			return "input";
		}
		String ext=dstPath[0].substring(dstPath[0].lastIndexOf(".")+1).toLowerCase();
		if(!ext.toLowerCase().equals("jpg")&&!ext.toLowerCase().equals("bmp")&&!ext.toLowerCase().equals("jpeg")&&!ext.toLowerCase().equals("gif")){
			message="您上传的图片格式有误，请上传jpg、jpeg、bmp格式的图片！";
			UserService userService = (UserService)this.getBean("userService");
			return "input";
		}
		if(ImageUtil.getWidth(dstPath[0])<620){
			this.picurl=filenames[0];
		}else{
			
			String newFileName = currentUserid+"_" +new Date().getTime()+ "_logo_" + dstPath[0].substring(dstPath[0].lastIndexOf("."));
			
			String realpath = getRealPath("logo", currentUserid);
			
			picurl = getRelativePath("logo", currentUserid) + newFileName;
			String orgtmpfile=realpath+newFileName;
			ImageUtil.resize(dstPath[0], orgtmpfile, 180);
			
			user.setLogo(picurl);
			service.update(user);
		}
		return SUCCESS;
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

	public CoreUser getUser() {
		return user;
	}
	
}
