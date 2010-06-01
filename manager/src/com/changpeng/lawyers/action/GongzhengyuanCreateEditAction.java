/**
 * 
 */
package com.changpeng.lawyers.action;

import java.io.File;
import java.text.DateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.LawyersGongzheng;
import com.changpeng.models.SysUnionparams;

/**
 * 
 * 新增律师。事务所管理员新增、市律协管理员新增、省律协管理员新增 其他人新增
 * 
 * @author 华锋
 * 
 */
public class GongzhengyuanCreateEditAction extends AbstractAction {
	private static Log _LOG = LogFactory.getLog(GongzhengyuanCreateEditAction.class);
	private static DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	public GongzhengyuanCreateEditAction() {

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
			this.message = "必须选择所在的公证处,请返回";
			return "message";
		}

		BasicService bs = (BasicService) this.getBean("basicService");
		gongzhengyuan.setLawyerenname(com.changpeng.common.util.Chinese2Pinyin.to2pinyin(gongzhengyuan.getLawyername()));
	
//		Random random = new java.util.Random();
//		gongzhengyuan.setLoginname(gongzhengyuan.getZigeno() + "_" + random.nextInt(9999));
		gongzhengyuan.setLoginname(gongzhengyuan.getLawyerno());
		gongzhengyuan.setPasswd(gongzhengyuan.getCertno());
		gongzhengyuan.setStatus(0);//不能登录.要求登录并听课
		if (upload != null && upload.length() != 0) {
			try {

				if (upload.length() > 100 * 1024) {
					debug("删除上传图片成功否:" + upload.delete());
					this.message = "上传的图片大小超出了规定的最大大小100K，请重新选择";

					return "message";
				}
				int index = fileName.lastIndexOf(".");
				String name = System.currentTimeMillis() + fileName.substring(index);

				String indexDir = com.changpeng.common.Constants.PHOTO_SAVE_PATH + gongzhengyuan.getDirectunion() + "/";
				File filedir = new File(indexDir);

				if (!filedir.exists()) {
					boolean s = filedir.mkdirs();
					debug("文件路径:::" + indexDir + "创建成功..." + s);
				}

				File file = new File(indexDir + System.getProperty("file.separator") + name);
				upload.renameTo(file);

				debug("=================" + indexDir);
				gongzhengyuan.setPhoto(gongzhengyuan.getDirectunion() + "/" + name);
				gongzhengyuan.setPhotoname(fileName);
			} catch (Exception e) {
				debug("照片上传失败..." + e);
			}
		}

		if (isnew) {

			// if (bs.isexist(gongzhengyuan.getLawyerno(),
			// gongzhengyuan.getDirectunion())) {
			// this.message = "您所填入的执业证号已重复,请重新填入";
			// return "message";
			// }
			gongzhengyuan.setDirectunion(this.datavisible.getCityid());
			gongzhengyuan.setProvinceunion(this.datavisible.getProvinceid());
			gongzhengyuan.setTheoffice(this.datavisible.getOfficeid());

			gongzhengyuan.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			gongzhengyuan.setCreateuser(this.getLoginUser().getUserid());
			gongzhengyuan.setCreateusername(this.getLoginUser().getUsername());

			gongzhengyuan.setPasswd(gongzhengyuan.getCertno());
			if (gongzhengyuan.getSystemno() == null || gongzhengyuan.getSystemno().equals(""))
				gongzhengyuan.setSystemno(System.currentTimeMillis() / 1000 + "");

		

			BasicService bservice = (BasicService) this.getBean("basicService");
			SysUnionparams union = (SysUnionparams) bservice.get(SysUnionparams.class, gongzhengyuan.getDirectunion());
			if (union != null) {
				float dabiaofen = union.getDabiaofen();
				gongzhengyuan.setDabiaofen(dabiaofen);
			}
			
		

			bs.save(gongzhengyuan);
			this.message = "公证员信息新增成功";
			
			this.opResult="新增公证员信息:"+gongzhengyuan.getLawyername();

		} else {

		
			bs.update(gongzhengyuan);
			this.message = "公证员信息修改成功";
			
			this.opResult="修改公证员信息:"+gongzhengyuan.getLawyername();
		}

		this.nextPage = "gongzhengyuanList.pl";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		BasicService bservice = (BasicService) this.getBean("basicService");
		gongzhengyuan = (LawyersGongzheng) bservice.get(LawyersGongzheng.class, lawyerid);

		if (gongzhengyuan == null) {
			this.datavisible.setCityid(this.getLoginUser().getCityid());
			this.datavisible.setOfficeid(this.getLoginUser().getOfficeid());
			this.datavisible.setProvinceid(this.getLoginUser().getProvinceid());

			isnew = true;
			gongzhengyuan = new LawyersGongzheng();

		} else {
			isnew = false;
			this.datavisible.setCityid(gongzhengyuan.getDirectunion());
			this.datavisible.setOfficeid(gongzhengyuan.getTheoffice());
			this.datavisible.setProvinceid(gongzhengyuan.getProvinceunion());
			if (gongzhengyuan.getZhiyedate() != null)
				gongzhengyuan.setZhiyedatestr(df.format(gongzhengyuan.getZhiyedate()));

		}
		this.datavisible.getVisibleDatas(this.getLoginUser(), false,true);

		set("gongzhengyuan", gongzhengyuan);

		return INPUT;
	}

	private LawyersGongzheng gongzhengyuan;

	public LawyersGongzheng getGongzhengyuan() {
		if (gongzhengyuan == null)
			gongzhengyuan = (LawyersGongzheng) this.get("gongzhengyuan");

		return this.gongzhengyuan;
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