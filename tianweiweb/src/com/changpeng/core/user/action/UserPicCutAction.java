package com.changpeng.core.user.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.changpeng.common.action.AbstractUploadAction;
import com.changpeng.common.sysdata.CommonData;
import com.changpeng.common.util.ImageUtil;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.core.user.model.Userinfo;


public class UserPicCutAction extends AbstractUploadAction{
	private int left;
	private int top;
	private int width;
	private int height;
	private String photoname;
	
	private String rnd;
	@Override
	protected String go() throws Exception {

		UserService userService = (UserService)this.getBean("userService");
		
		Userinfo u=userService.getUserinfoById(currentUserid);
		String lv=this.getLogoVersion(u.getPic());
		
		String newFileName = currentUserid+ "_"
				+ new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())
				+ photoname.substring(photoname.lastIndexOf("."));
		String srcImageFile=ServletActionContext.getServletContext().getRealPath("")+photoname;
		
		String ext=srcImageFile.substring(srcImageFile.lastIndexOf("."));
		
		String logorelativepath=this.getRelativePath(CommonData.DEFAULT_LOGO_URL,currentUserid);
		String logorealpath=this.getRealPath(CommonData.DEFAULT_LOGO_URL,currentUserid);
		String savename96=this.currentUserid+"_96_"+lv+ext;
		
		String imagepath60=logorealpath+this.currentUserid+"_60_"+lv+ext;
		String imagepath96=logorealpath+this.currentUserid+"_96_"+lv+ext;
		
		String realpath=getRealPath("origin",currentUserid);
		String subpath = realpath + newFileName;
		
		ImageUtil.cutimage(left, top, width, height,srcImageFile, subpath);
//		Thread.sleep(500);
		ImageUtil.resize(subpath, imagepath60, 60);
//		Thread.sleep(500);
		ImageUtil.resize(subpath, imagepath96, 96);
		

		CoreUser user=userService.getUserById(currentUserid);
		user.setPic(logorelativepath+savename96);
		userService.update(user);

		rnd=Long.toString(new Date().getTime());
		return SUCCESS;
	
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setPhotoname(String photoname) {
		this.photoname = photoname;
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

	public String getRnd() {
		return rnd;
	}
}
