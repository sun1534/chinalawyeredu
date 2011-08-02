/**
 * 
 */
package com.sxit.tongnan.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.sxit.common.Globals;
import com.sxit.common.action.AbstractListAction;
import com.sxit.models.tongnan.StatTongnanpower;
import com.sxit.tongnan.GetParseTongnanData;
import com.sxit.tongnan.service.TongnanPowerService;

/**
 * <pre>
 * 上传数据，然后下载数据，进行匹配并入库 * 
 * </pre>
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class TongnanPowerSetAction extends AbstractListAction {

	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	// 选择月份
	private String uploadmonth;

public TongnanPowerSetAction(){
	this.needsession=false;
}
	/**
	 * @return the upload
	 */
	public File getUpload() {
		return upload;
	}

	/**
	 * @param upload
	 *            the upload to set
	 */
	public void setUpload(File upload) {
		this.upload = upload;
	}

	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName
	 *            the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/*
	 * 得到某一天的各个sgsn的流量 显示柱状图和线图
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		TongnanPowerService service = (TongnanPowerService) Globals.getBean("tongnanPowerService");
		List list = service.getMonthPowers(uploadmonth, null);
		if (list == null || list.size() == 0) {

			if (upload == null) {
				this.message = "请上传电度数据文件,不能为空。文件必须符合模板的格式";
				return "message";
			} else {
				String destfile = null;
				try {
					System.out.println("开始从网管服务器上下载文件");
					destfile = GetParseTongnanData.getftpfile(uploadmonth);
					System.out.println("文件下载后保存地:" + destfile);
					Map<String, String> maps = null;
					if (destfile == null) {

						this.message = "从网管服务器上获取载频数据失败,请重新获取";
						return "message";
					}

					maps = GetParseTongnanData.parseDownloadFile(destfile);
					System.out.println("解析完毕从服务器上获取的数据");

					// 找到上个月的数据
					List<StatTongnanpower> stats = GetParseTongnanData.parseUploadFile(upload.getAbsolutePath());
					System.out.println("解析完毕潼南移动提供的电度数据");
					GetParseTongnanData.saveExcelFile(stats);
					System.out.println("潼南移动提供的电度数据存入数据库完毕");
					if (maps != null)
						GetParseTongnanData.updateDownloadFile(maps);
					System.out.println("根据服务器上下载的文件更新载频数");
					this.message = "电度文件处理成功,请返回查看";
					this.nextPage = "tongnanPower.action?month=" + uploadmonth;
				} catch (Exception e) {
					e.printStackTrace();
					this.message = "电度文件处理失败,请重新上传处理";
					return "message";
				}

			}

		} else {
			this.message = "这个月的数据已经有了,不需要再上传.";

		}
		// 先判断这个月分的数据是否有了

		return SUCCESS;
	}

	/**
	 * @return the uploadmonth
	 */
	public String getUploadmonth() {
		return uploadmonth;
	}

	/**
	 * @param uploadmonth the uploadmonth to set
	 */
	public void setUploadmonth(String uploadmonth) {
		this.uploadmonth = uploadmonth;
	}

}
