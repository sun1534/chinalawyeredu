package com.changpeng.system.action.ajax;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysUser;

public class PhotoDeleteAction  extends AbstractAction {
	private int userid;
	public void setUserid(int userid){
		this.userid=userid;
	}
	@Override
	protected String go() throws Exception {
		BasicService service=(BasicService)this.getBean("basicService");
		SysUser user=(SysUser)service.get(SysUser.class, userid);
	
		String photo=user.getPhoto();	
		if(photo!=null&&!"".equals(photo)){
			try{
				File _file=new File(ServletActionContext.getServletContext().getRealPath("/lawyerphotos")+"/"+photo);
				FileUtils.forceDelete(_file);
			}catch(IOException e){
				debug("删除照片失败："+e);
			}
		}
		user.setPhoto(null);
		service.update(user);	
		set("sysUser",user);
		
		return SUCCESS;		
	}
}
