package com.changpeng.system.action.ajax;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lawyers;


/**
 * 
 * 删除律师的照片
 * @author 华锋
 *
 */
public class PhotoDeleteAction  extends AbstractAction {
	
	private static Log _LOG = LogFactory.getLog(PhotoDeleteAction.class);
	
	private int lawyerid;

	private String success;
	/**
	 * @return the success
	 */
	public String getSuccess() {
		return success;
	}

	/**
	 * @return the lawyerid
	 */
	public int getLawyerid() {
		return lawyerid;
	}

	/**
	 * @param lawyerid the lawyerid to set
	 */
	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}

	@Override
	protected String go() throws Exception {
		BasicService service=(BasicService)this.getBean("basicService");
		Lawyers lawyers=(Lawyers)service.get(Lawyers.class, lawyerid);
	
		String photo=lawyers.getPhoto();	
		
	
		if(photo!=null&&!"".equals(photo)){
			try{
				File _file=new File( com.changpeng.common.Constants.PHOTO_SAVE_PATH +photo);
				FileUtils.forceDelete(_file);
			}catch(IOException e){
				_LOG.error("删除律师照片失败："+e);
			}
		}
		lawyers.setPhoto(null);
		lawyers.setPhotoname(null);
		service.update(lawyers);	
//		set("sysUser",user);
		
		debug("执行成功否....................");
		this.success="true";
		return SUCCESS;		
	}

}
