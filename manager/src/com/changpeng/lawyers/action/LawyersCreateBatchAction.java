/**
 * 
 */
package com.changpeng.lawyers.action;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.lawyers.service.LawyersService;
import com.changpeng.models.Lawyers;

/**
 * 
 * 新增律师。事务所管理员新增、市律协管理员新增、省律协管理员新增 其他人新增
 * 
 * @author 华锋
 * 
 */
public class LawyersCreateBatchAction extends AbstractAction {
	private static Log _LOG = LogFactory.getLog(LawyersCreateBatchAction.class);

	public LawyersCreateBatchAction() {
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

		LawyersService bs = (LawyersService) this.getBean("lawyersService");
		if (upload != null && upload.length() != 0) {
			try {

				List<Lawyers> lawyerslist = com.changpeng.system.util.ExcelUtil.parseLawyersXls(upload);

				List<String> list = bs.addLawyerBatch(this.getLoginUser(), datavisible.getProvinceid(), datavisible
						.getCityid(), lawyerslist);

				int success = lawyerslist.size() - list.size();

				this.message = "律师数据批量导入处理完成,总计" + lawyerslist.size() + "个,成功导入" + success + "个";
				if (list.size() > 0) {
					this.message += "<br/>失败情况如下<br/><hr/>";
					for (int i = 0; i < list.size(); i++) {
						this.message +=  list.get(i)+"<br/>";
					}
				}

				// int index = fileName.lastIndexOf(".");
				// String name = System.currentTimeMillis() +
				// fileName.substring(index);
				//
				// String indexDir =
				// com.changpeng.common.Constants.PHOTO_SAVE_PATH +
				// lawyers.getDirectunion() + "/";
				// File filedir = new File(indexDir);
				//
				// if (!filedir.exists()) {
				// boolean s = filedir.mkdirs();
				// debug("文件路径:::" + indexDir + "创建成功..." + s);
				// }
				//
				// File file = new File(indexDir +
				// System.getProperty("file.separator") + name);
				// upload.renameTo(file);

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
