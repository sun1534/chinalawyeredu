/**
 * TSysUserAddAction.java
 */

package com.changpeng.lawyers.action;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.Constants;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lawyers;

/**
 * 
 * 律师信息修改
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class LawyersEditSelfAction extends AbstractAction {
	private static Log _LOG = LogFactory.getLog(LawyersEditSelfAction.class);
	private static DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd");
	public LawyersEditSelfAction() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		
		if(lawyers.getProvinceunion()==22){
			
			
			this.message="对不起,广西律协的律师资料来自于律管平台,不能修改";
			
			return "message";
		}
		
		
		com.changpeng.common.BasicService bs = (com.changpeng.common.BasicService) this.getBean("basicService");
		lawyers.setLawyerenname(com.changpeng.common.util.Chinese2Pinyin.to2pinyin(lawyers.getLawyername()));

		if(lawyers.getZhiyedatestr()!=null&&!lawyers.getZhiyedatestr().equals(""))
		{
			try{
				Date date=df.parse(lawyers.getZhiyedatestr());
				lawyers.setZhiyedate(date);
			}catch(Exception e){
				this.message="执业日期输入不对,请重新输入:"+lawyers.getZhiyedatestr();
				return "message";
			}
		}
		
		
		// if (lawyers.getTheoffice() == 0) {
		// this.message = "必须选择所在的事务所,请返回";
		// return "message";
		// }

		if (upload != null && upload.length() != 0) {
			try {

				if (upload.length() > 1000 * 1024) {
					debug("删除上传图片成功否:" + upload.delete());
					this.message = "上传的图片大小超出了规定的最大大小1000K，请重新选择";

					return "message";
				}
				int index = fileName.lastIndexOf(".");
				String name = System.currentTimeMillis() + fileName.substring(index);

				String indexDir = com.changpeng.common.Constants.PHOTO_SAVE_PATH +lawyers.getDirectunion() + "/";
				File filedir = new File(indexDir);

				if (!filedir.exists()) {
					boolean s = filedir.mkdirs();
					debug("文件路径:::" + indexDir + "创建成功...");
				}

				File file = new File(indexDir + System.getProperty("file.separator") + name);
				upload.renameTo(file);

				lawyers.setPhoto(lawyers.getDirectunion()+"/"+name);
				lawyers.setPhotoname(fileName);
				
//				this.getLoginUser()
				
//				Lawyers sysUser = (Lawyers) get();
				this.set(Constants.LOGIN_USER, lawyers);
				

			} catch (Exception e) {
				debug("照片上传失败..." + e);
			}
		}
		//

		bs.update(lawyers);
		this.message = "律师信息修改成功";

		// this.nextPage = "lawyersList.pl";
		this.nextPage = "lawyersEditSelf!input.pl";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

//		BasicService bservice = (BasicService) this.getBean("basicService");
//		lawyers = (Lawyers) bservice.get(Lawyers.class, this.getLoginUser().getLawyerid());
	lawyers=this.getLoginUser();
		if(lawyers.getZhiyedate()!=null)
			lawyers.setZhiyedatestr(df.format(lawyers.getZhiyedate()));
		com.changpeng.common.CommonDatas.getGroups();
//		set("lawyers", lawyers);
if(lawyers.getLawyertype()==-1)
	return "shixi";
		return INPUT;
	}

	private Lawyers lawyers;

	public Lawyers getLawyers() {
		if (lawyers == null)
//			lawyers = (Lawyers) this.get("lawyers");
lawyers=this.getLoginUser();
		return this.lawyers;
	}

	private File upload;
	private String fileName;

	public String getUploadFileName() {
		return fileName;
	}

	public void setUploadFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}
}
