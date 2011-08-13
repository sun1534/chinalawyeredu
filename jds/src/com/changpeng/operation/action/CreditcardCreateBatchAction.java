package com.changpeng.operation.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import com.changpeng.customer.model.TusrCustomerNew;
import com.changpeng.operation.model.ToprCreditcard;
import com.sxit.common.action.AbstractAction;

/**
 * 
 * <p>
 * 功能： 创建催收记录
 * </p>
 * <p>
 * 作者： 刘兴华
 * </p>
 * <p>
 * 公司： 长鹏软件
 * </p>
 * <p>
 * 日期： 2009-06-09
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */

public class CreditcardCreateBatchAction extends AbstractAction {
	private long bankid;
	private String consigndate;
	private File file;
	private String fileName;
	private String contenttype;

	public String getConsigndate() {
		return consigndate;
	}

	public File getFile() {
		return file;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public void setBankid(long bankid) {
		this.bankid = bankid;
	}

	public void setConsigndate(String consigndate) {
		this.consigndate = consigndate;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public CreditcardCreateBatchAction() {
		rights = "opr2,2";

	}

	private static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

	public String go() throws HibernateException {
		getSession();
		if (fileName != null && !"".equals(fileName)) {
			String extendPath = "/uploads/";
			String name = "CreditcardCreateBatch"+new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
			String toPath = ServletActionContext.getServletContext().getRealPath("") + extendPath;
			String ext = getExtention(fileName);
			String filename = name + ext;
			try {
				FileUtils.forceMkdir(new File(toPath)); // 创建目录
				File dest = new File(toPath + filename);
				FileUtils.copyFile(file, dest); // 移动文件
				if (!ext.equalsIgnoreCase(".xls")) {
					message = "委托文件必须为.xls格式文件";
					FileUtils.forceDelete(dest);
					return ERROR;
				} else {

					List existUserList = new ArrayList();
					List<ToprCreditcard> list = com.changpeng.operation.util.CreditcardCreateBatch.save(dest,
							consigndate, bankid, getSession(), existUserList);
					if (list != null && list.size() > 0) {
						message = "以下信用卡账号在系统中已经存在，如需继续，请手动录入：<br>";
						for (ToprCreditcard card : list) {
							message = message + card.getUsername() + "【" + card.getCreditcard() + "】"
									+ "<a href='cardreoper.action?opflag=0&creditcard=" + card.getCreditcard()
									+ "' target='_blank'>" + "<font color='red'>新增</font></a>&nbsp;&nbsp;"
									+ "<a href='cardreoper.action?opflag=1&creditcard=" + card.getCreditcard()
									+ "' target='_blank'>" + "<font color='red'>覆盖</font></a><br>";
						}

						message += "<a href='cardreoper.action?opflag=3' target='_blank'><font color='red'>全部新增</font></a>&nbsp;&nbsp;";
						message += "<a href='cardreoper.action?opflag=4' target='_blank'><font color='red'>全部覆盖</font></a>";
						// 将重复的单放在session中,以便在页面中覆盖或跳过
						set("recards", list);

					} else {
						message = "保存成功！";
					}

					if (existUserList.size() != 0) {
						message += "<br/>以下用户在系统中已经存在,请知悉:<br/>";
						for (int i = 0; i < existUserList.size(); i++) {
							TusrCustomerNew customer = (TusrCustomerNew) existUserList.get(i);
							if (customer.getIdcard() != null && !customer.getIdcard().equals(""))
								message +="<a href='../customer/customer3View.action?customerid="+customer.getCustomerid()+"' target='_blank'>"+ customer.getUsername() + "(" + customer.getIdcard() + ")</a><br/>";
							else {
								message +="<a href='../customer/customer3View.action?customerid="+customer.getCustomerid()+"' target='_blank'>"+ customer.getUsername() + "</a><br/>";
							}
						}
					}

				}
			} catch (IOException e) {
				message = "上传委托文件错误：" + e.getMessage();
				return ERROR;
			}
		} else {
			message = "上传文件不能为空";
			return ERROR;
		}
		nextpage = "creditcardList.action";
		// message="保存成功！";
		return SUCCESS;
	}

	public String input() throws Exception {
		// listBank=com.changpeng.operation.util.OperationUtil.listBank;
		return "input";
	}
}
