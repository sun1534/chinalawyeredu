package com.changpeng.nonlaw.action;


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
 * 非诉还款批量导入
 * @author sinhoo
 * 2009-11-24
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
           rights="nlw5,2";

	}
	private static String getExtention(String fileName){
    	int pos=fileName.lastIndexOf(".");
    	return fileName.substring(pos);
    }
	public String go() throws HibernateException {
		getSession();
//		if(fileName!=null&&!"".equals(fileName)){
//			String extendPath="/uploads/";
		DateFormat df=new java.text.SimpleDateFormat("yyyyMM");
		String nowmonth=df.format(new Date());
		if (fileName != null && !"".equals(fileName)) {
			
			String extendPath = "/uploads/"+nowmonth;
			String toPath = ServletActionContext.getServletContext().getRealPath("") + extendPath;
			File dir=new File(toPath);
			if(!dir.exists())
			{
				dir.mkdirs();
			}
			String name="NonlawRepaylogCreateBatch"+new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
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
					getSession().createSQLQuery("update tnlw_nonlaw set refee='0' where refee is null or refee=''").executeUpdate();		
					String retmsg=com.changpeng.nonlaw.util.NonlawCreateBatch.saveRepaylog(dest, curuser.getUserid());
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
        nextpage="nonlawList.action";
        //message="保存成功！";
		return SUCCESS;
	}

        public String input() throws Exception {
        	//listBank=com.changpeng.operation.util.OperationUtil.listBank;
            return "input";
    }
}
