package com.changpeng.lawyers.action.ajax;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lawyers;
import com.changpeng.models.OfficeProperties;

/**
 * 
 * 删除律师的照片
 * 
 * @author 华锋
 * 
 */
public class PhotoDeleteAction extends AbstractAction {

	private static Log _LOG = LogFactory.getLog(PhotoDeleteAction.class);

	private int groupid;

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
	 * @return the groupid
	 */
	public int getGroupid() {
		return groupid;
	}

	/**
	 * @param groupid
	 *            the groupid to set
	 */
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	/**
	 * @param lawyerid
	 *            the lawyerid to set
	 */
	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}

	@Override
	protected String go() throws Exception {
		BasicService service = (BasicService) this.getBean("basicService");

		if (lawyerid != 0) {
			Lawyers lawyers = (Lawyers) service.get(Lawyers.class, lawyerid);

			String photo = lawyers.getPhoto();

			if (photo != null && !"".equals(photo)) {
				try {
					File _file = new File(com.changpeng.common.Constants.PHOTO_SAVE_PATH + photo);
					FileUtils.forceDelete(_file);
				} catch (IOException e) {
					_LOG.error("删除律师照片失败：" + e);
				}
			}
			lawyers.setPhoto(null);
			lawyers.setPhotoname(null);
			service.update(lawyers);
		}
		if (groupid != 0) {
			OfficeProperties properties = (OfficeProperties) service.get(OfficeProperties.class, groupid);

			String photo = properties.getPhoto();

			if (photo != null && !"".equals(photo)) {
				try {
					File _file = new File(com.changpeng.common.Constants.PHOTO_SAVE_PATH + photo);
					FileUtils.forceDelete(_file);
				} catch (IOException e) {
					_LOG.error("删除律师照片失败：" + e);
				}
			}
			properties.setPhoto(null);
			properties.setFilename(null);
			service.update(properties);
		}

		debug("执行成功否....................");
		this.success = "true";
		return SUCCESS;
	}

}
