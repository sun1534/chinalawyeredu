/**
 * 
 */
package com.changpeng.lawyers.action;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.lawyers.service.LawyersService;
import com.changpeng.models.Lawyers;
import com.changpeng.models.SysUnionparams;

/**
 * 
 * 新增律师。事务所管理员新增、市律协管理员新增、省律协管理员新增 其他人新增
 * 
 * @author 华锋
 * 
 */
public class LawyersCreateEditAction extends AbstractAction {
	private static Log _LOG = LogFactory.getLog(LawyersCreateEditAction.class);
private static DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd");
	public LawyersCreateEditAction() {

		this.datavisible = new DataVisible();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		if(this.datavisible.getOfficeid()==0){
			this.message = "必须选择所在的事务所,请返回";
			return "message";
		}
		
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
		
		
		LawyersService bs = (LawyersService) this.getBean("lawyersService");
		lawyers.setLawyerenname(com.changpeng.common.util.Chinese2Pinyin.to2pinyin(lawyers.getLawyername()));
		lawyers.setDirectunion(this.datavisible.getCityid());
		lawyers.setProvinceunion(this.datavisible.getProvinceid());
		lawyers.setTheoffice(this.datavisible.getOfficeid());
		SysUnionparams params=(SysUnionparams)basicService.get(SysUnionparams.class, lawyers.getDirectunion());
		
		lawyers.setDabiaofen(params.getDabiaofen());


		if (upload != null && upload.length() != 0) {
			try {

				if (upload.length() > 100 * 1024) {
					debug("删除上传图片成功否:" + upload.delete());
					this.message = "上传的图片大小超出了规定的最大大小100K，请重新选择";

					return "message";
				}
				int index = fileName.lastIndexOf(".");
				String name = System.currentTimeMillis() + fileName.substring(index);

				String indexDir = com.changpeng.common.Constants.PHOTO_SAVE_PATH + lawyers.getDirectunion() + "/";
				File filedir = new File(indexDir);

				if (!filedir.exists()) {
					boolean s = filedir.mkdirs();
					debug("文件路径:::" + indexDir + "创建成功..."+s);
				}

				File file = new File(indexDir + System.getProperty("file.separator") + name);
				upload.renameTo(file);

				debug("=================" + indexDir);
				lawyers.setPhoto(lawyers.getDirectunion()+"/"+name);
				lawyers.setPhotoname(fileName);
			} catch (Exception e) {
				debug("照片上传失败..." + e);
			}
		}

		if (isnew) {

			if (bs.isexist(lawyers.getLawyerno(), lawyers.getDirectunion())) {
				this.message = "您所填入的执业证号已重复,请重新填入";
				return "message";
			}

			lawyers.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			lawyers.setCreateuser(this.getLoginUser().getUserid());
			lawyers.setCreateusername(this.getLoginUser().getUsername());

			lawyers.setPasswd(lawyers.getCertno());
			lawyers.setSystemno(System.currentTimeMillis() / 1000 + "");

			BasicService bservice = (BasicService) this.getBean("basicService");
			SysUnionparams union = (SysUnionparams) bservice.get(SysUnionparams.class, lawyers.getDirectunion());
			if (union != null) {
				float dabiaofen = union.getDabiaofen();
				lawyers.setDabiaofen(dabiaofen);
			}

			bs.addLawyer(lawyers);
			this.message = "律师信息新增成功";

		} else {

			lawyers.setLoginname(lawyers.getLawyerno());
			lawyers.setPasswd(lawyers.getCertno());
			bs.updateLawyers(lawyers);
			this.message = "律师信息修改成功";
		}

		this.nextPage = "lawyersList.pl";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		BasicService bservice = (BasicService) this.getBean("basicService");
		lawyers = (Lawyers) bservice.get(Lawyers.class, lawyerid);

		if (lawyers == null) {
			this.datavisible.setCityid(this.getLoginUser().getCityid());
			this.datavisible.setOfficeid(this.getLoginUser().getOfficeid());
			this.datavisible.setProvinceid(this.getLoginUser().getProvinceid());

			isnew = true;
			lawyers = new Lawyers();

		} else {
			isnew = false;
			this.datavisible.setCityid(lawyers.getDirectunion());
			this.datavisible.setOfficeid(lawyers.getTheoffice());
			this.datavisible.setProvinceid(lawyers.getProvinceunion());
			if(lawyers.getZhiyedate()!=null)
			lawyers.setZhiyedatestr(df.format(lawyers.getZhiyedate()));

		}
		this.datavisible.getVisibleDatas(this.getLoginUser(), false);

		set("lawyers", lawyers);

		return INPUT;
	}

	private Lawyers lawyers;

	public Lawyers getLawyers() {
		if (lawyers == null)
			lawyers = (Lawyers) this.get("lawyers");

		return this.lawyers;
	}

	private int lawyerid;

	/**
	 * @return the lawyerid
	 */
	public int getLawyerid() {
		return lawyerid;
	}

	/**
	 * @param lawyerid
	 *            the lawyerid to set
	 */
	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}

	private boolean isnew;

	/**
	 * @return the isnew
	 */
	public boolean isIsnew() {
		return isnew;
	}

	/**
	 * @param isnew
	 *            the isnew to set
	 */
	public void setIsnew(boolean isnew) {
		this.isnew = isnew;
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
