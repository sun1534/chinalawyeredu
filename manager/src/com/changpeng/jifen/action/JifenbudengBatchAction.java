/**
 * 
 */
package com.changpeng.jifen.action;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.jifen.service.JifenbudengService;
import com.changpeng.models.Jifenbudeng;

/**
 * 
 * 新增律师。事务所管理员新增、市律协管理员新增、省律协管理员新增 其他人新增
 * 
 * @author 华锋
 * 
 */
public class JifenbudengBatchAction extends AbstractAction {
	private static Log _LOG = LogFactory.getLog(JifenbudengBatchAction.class);

	public JifenbudengBatchAction() {
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
		if (this.datavisible.getCityid() == 0) {
			this.message = "必须选择所在的市律协,请返回";
			return "message";
		}

		JifenbudengService budengservice = (JifenbudengService) this.getBean("jifenbudengService");
		if (upload != null && upload.length() != 0) {
			try {

				List<JifenbudengBatch> budenglist = com.changpeng.system.util.ExcelUtil.parseBudengjifen(upload);

				List<String> list = budengservice.saveJifenbudeng(budenglist, this.getLoginUser(), datavisible
						.getProvinceid(), datavisible.getCityid());
			
				int success = budenglist.size() - list.size();

				this.message = "批量积分补登处理完成,总计" + budenglist.size() + "个,成功导入" + success + "个";
				if (list.size() > 0) {
					this.message += "<br/>失败情况如下<br/><hr/>";
					for (int i = 0; i < list.size(); i++) {
						this.message +="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ list.get(i) + "<br/>";
					}
				}
				this.opResult = "批量补登积分,总计" + budenglist.size() + "个,成功导入" + success + "个";
			} catch (Exception e) {
				_LOG.error("文件解析失败:", e);
				this.message = "上传文件解析失败,请返回:" + e.getMessage();
				return "message";
			}
		} else {
			this.message = "您上传的文件为空,请返回";
			return "message";
		}

		this.nextPage = "lawyersList.pl";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		this.datavisible.setCityid(this.getLoginUser().getCityid());
		this.datavisible.setOfficeid(this.getLoginUser().getOfficeid());
		this.datavisible.setProvinceid(this.getLoginUser().getProvinceid());

		this.datavisible.getVisibleDatas(this.getLoginUser(), false);

		return INPUT;
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
