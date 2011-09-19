package com.changpeng.operation.action;


import java.io.*;
import java.text.DateFormat;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.TnlwNonlaw;
import com.changpeng.operation.model.*;


/**
 *
 * <p>功能： 批量导入还款记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-08-14</p>
 * @版本： V1.0
 * @修改：
 */

public class RepaylogCreateBatchAction extends AbstractAction {
	
	private File file;
	private String fileName;
	private String contenttype;
	
	

	public File getFile() {
		return file;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
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
	public RepaylogCreateBatchAction() {
           rights="opr2,2";

	}
	private static String getExtention(String fileName){
    	int pos=fileName.lastIndexOf(".");
    	return fileName.substring(pos);
    }
	public String go() throws HibernateException {
		getSession();
		DateFormat df=new java.text.SimpleDateFormat("yyyyMM");
		String nowmonth=df.format(new Date());
		if (fileName != null && !"".equals(fileName)) {
			
//			String extendPath = "/uploads/"+nowmonth;
			String extendPath = "/uploads/"+nowmonth+"/";
			String toPath = ServletActionContext.getServletContext().getRealPath("") + extendPath;
			File dir=new File(toPath);
			if(!dir.exists())
			{
				dir.mkdirs();
			}
			String name="CreditRepaylogCreateBatch"+new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
//			String toPath=ServletActionContext.getServletContext().getRealPath("")+extendPath;
			String ext=getExtention(fileName);		
			String filename=name+ext;		
			try {
				FileUtils.forceMkdir(new File(toPath)); //创建目录
				File dest=new File(toPath+filename);
				FileUtils.copyFile(file, dest); //移动文件
				if(!ext.equalsIgnoreCase(".xls")){
					message = "还款记录必须为.xls格式文件";
					FileUtils.forceDelete(dest);
					return ERROR;
				}else{
					getSession().createSQLQuery("update topr_creditcard set refee='0' where refee is null or refee=''").executeUpdate();		
					String retmsg=com.changpeng.operation.util.CreditcardCreateBatch.saveRepaylog(dest, curuser.getUserid(),getSession());
					message="还款记录导入成功<br>"+retmsg;
				}
			}catch(IOException e){
				message = "上传还款记录错误："+e.getMessage();
				return ERROR;
			}catch(Exception e){
				message = "系统异常："+e.getMessage();
				return ERROR;
			}			
		}else{			
			message="上传文件不能为空";
			return ERROR;
		}	
        nextpage="creditcardList.action";
        //message="保存成功！";
		return SUCCESS;
	}

        public String input() throws Exception {
        	//listBank=com.changpeng.operation.util.OperationUtil.listBank;
            return "input";
    }
}
