/**
 * 
 */
package com.changpeng.lawyers.action;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.lawyers.service.LawyersService;
import com.changpeng.models.LawyersShixi;
import com.changpeng.models.SysUnionparams;

/**
 * 
 * 新增律师。事务所管理员新增、市律协管理员新增、省律协管理员新增 其他人新增
 * 
 * @author 华锋
 * 
 */
public class LawyersShixiCreateEditAction extends AbstractAction {
	private static Log _LOG = LogFactory.getLog(LawyersShixiCreateEditAction.class);
	private static DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	public LawyersShixiCreateEditAction() {

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
		if (this.datavisible.getOfficeid() == 0) {
			this.message = "必须选择所在的事务所,请返回";
			return "message";
		}
		if(lawyersShixi.getZigeno()==null||lawyersShixi.getZigeno().equals(""))
		{
		
				this.message="必须输入资格证号,请返回";
				return "message";
			
		}

		BasicService bs = (BasicService) this.getBean("basicService");
		lawyersShixi.setLawyerenname(com.changpeng.common.util.Chinese2Pinyin.to2pinyin(lawyersShixi.getLawyername()));
		lawyersShixi.setDirectunion(this.datavisible.getCityid());
		lawyersShixi.setProvinceunion(this.datavisible.getProvinceid());
		lawyersShixi.setTheoffice(this.datavisible.getOfficeid());
//		SysUnionparams params = (SysUnionparams) basicService.get(SysUnionparams.class, lawyersShixi.getDirectunion());

//		lawyersShixi.setDabiaofen(params.getDabiaofen());
		Random random = new java.util.Random();
		lawyersShixi.setLoginname(lawyersShixi.getZigeno() + "_" + random.nextInt(9999));
		lawyersShixi.setPasswd(lawyersShixi.getZigeno());
		lawyersShixi.setStatus(0);//不能登录.要求登录并听课
		if (upload != null && upload.length() != 0) {
			try {

				if (upload.length() > 100 * 1024) {
					debug("删除上传图片成功否:" + upload.delete());
					this.message = "上传的图片大小超出了规定的最大大小100K，请重新选择";

					return "message";
				}
				int index = fileName.lastIndexOf(".");
				String name = System.currentTimeMillis() + fileName.substring(index);

				String indexDir = com.changpeng.common.Constants.PHOTO_SAVE_PATH + lawyersShixi.getDirectunion() + "/";
				File filedir = new File(indexDir);

				if (!filedir.exists()) {
					boolean s = filedir.mkdirs();
					debug("文件路径:::" + indexDir + "创建成功..." + s);
				}

				File file = new File(indexDir + System.getProperty("file.separator") + name);
				upload.renameTo(file);

				debug("=================" + indexDir);
				lawyersShixi.setPhoto(lawyersShixi.getDirectunion() + "/" + name);
				lawyersShixi.setPhotoname(fileName);
			} catch (Exception e) {
				debug("照片上传失败..." + e);
			}
		}

		if (isnew) {

			// if (bs.isexist(lawyersShixi.getLawyerno(),
			// lawyersShixi.getDirectunion())) {
			// this.message = "您所填入的执业证号已重复,请重新填入";
			// return "message";
			// }

			lawyersShixi.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			lawyersShixi.setCreateuser(this.getLoginUser().getUserid());
			lawyersShixi.setCreateusername(this.getLoginUser().getUsername());

			lawyersShixi.setPasswd(lawyersShixi.getCertno());
			if (lawyersShixi.getZigeno() == null || lawyersShixi.getZigeno().equals(""))
				lawyersShixi.setZigeno(System.currentTimeMillis() / 1000 + "");

			if (lawyersShixi.getShixino() == null || lawyersShixi.getShixino().equals(""))
				lawyersShixi.setShixino(System.currentTimeMillis() / 1000 + "");

			BasicService bservice = (BasicService) this.getBean("basicService");
			SysUnionparams union = (SysUnionparams) bservice.get(SysUnionparams.class, lawyersShixi.getDirectunion());
			if (union != null) {
				float dabiaofen = union.getDabiaofen();
				lawyersShixi.setDabiaofen(dabiaofen);
			}
			
		

			bs.save(lawyersShixi);
			this.message = "实习律师信息新增成功";
			
			this.opResult="新增实习律师信息:"+lawyersShixi.getLawyername();

		} else {

		
			bs.update(lawyersShixi);
			this.message = "实习律师信息修改成功";
			
			this.opResult="修改实习律师信息:"+lawyersShixi.getLawyername();
		}

		this.nextPage = "lawyersShixiList.pl";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		BasicService bservice = (BasicService) this.getBean("basicService");
		lawyersShixi = (LawyersShixi) bservice.get(LawyersShixi.class, lawyerid);

		if (lawyersShixi == null) {
			this.datavisible.setCityid(this.getLoginUser().getCityid());
			this.datavisible.setOfficeid(this.getLoginUser().getOfficeid());
			this.datavisible.setProvinceid(this.getLoginUser().getProvinceid());

			isnew = true;
			lawyersShixi = new LawyersShixi();

		} else {
			isnew = false;
			this.datavisible.setCityid(lawyersShixi.getDirectunion());
			this.datavisible.setOfficeid(lawyersShixi.getTheoffice());
			this.datavisible.setProvinceid(lawyersShixi.getProvinceunion());
			if (lawyersShixi.getZigedate() != null)
				lawyersShixi.setZhiyedatestr(df.format(lawyersShixi.getZigedate()));

		}
		this.datavisible.getVisibleDatas(this.getLoginUser(), false);

		set("lawyersShixi", lawyersShixi);

		return INPUT;
	}

	private LawyersShixi lawyersShixi;

	public LawyersShixi getLawyersShixi() {
		if (lawyersShixi == null)
			lawyersShixi = (LawyersShixi) this.get("lawyersShixi");

		return this.lawyersShixi;
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
