package com.changpeng.customer.action;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractAction;


/**
 * 机构客户批量导入
 * @author sinhoo
 * Aug 31, 2009
 */
public class Customer2ExportAction extends AbstractAction {
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
	public Customer2ExportAction() {
           rights="nlw1,2";

	}
	private static String getExtention(String fileName){
    	int pos=fileName.lastIndexOf(".");
    	return fileName.substring(pos);
    }
	public String go() throws HibernateException {
		//getSession();
		if(fileName!=null&&!"".equals(fileName)){
			String extendPath="/uploads/";
			String name=new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
			String toPath=ServletActionContext.getServletContext().getRealPath("")+extendPath;
			String ext=getExtention(fileName);		
			String filename=name+ext;		
			try {
				FileUtils.forceMkdir(new File(toPath)); //创建目录
				File dest=new File(toPath+filename);
				FileUtils.copyFile(file, dest); //移动文件
				if(!ext.equalsIgnoreCase(".xls")){
					message = "客户资料必须为.xls格式文件";
					FileUtils.forceDelete(dest);
					return ERROR;
				}else{
						com.changpeng.customer.util.CustomerCreateBatch.saveCustomer2(dest, getSession(),curuser.getUserid());
						message="保存成功！";
				}
			}catch(IOException e){
				message = "上传客户资料错误："+e.getMessage();
				return ERROR;
			}		
		}else{			
			message="上传文件不能为空";
			return ERROR;
		}	
        nextpage="customer2List.action";
      //  message="保存成功！";
		return SUCCESS;
	}

        public String input() throws Exception {
        	//listBank=com.changpeng.operation.util.OperationUtil.listBank;
            return "input";
    }
}
